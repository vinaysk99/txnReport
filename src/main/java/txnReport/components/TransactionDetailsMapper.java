package txnReport.components;

import org.springframework.stereotype.Component;
import txnReport.model.ClientDetails;
import txnReport.model.ProductInfo;
import txnReport.model.ReportRecord;
import txnReport.model.TransactionDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionDetailsMapper {


    private Map<ClientDetails, Map<ProductInfo, Long>> getClientDetailsProductInfoTxnsMap(
            final List<TransactionDetails> transactionDetailsList) {
        Map<ClientDetails, Map<ProductInfo, Long>> map = new HashMap<>();
        for (TransactionDetails transactionDetails : transactionDetailsList) {
            ClientDetails clientDetails = ClientDetails.builder()
                    .clientNumber(transactionDetails.getClientNumber())
                    .clientType(transactionDetails.getClientType())
                    .accountNumber(transactionDetails.getAccountNumber())
                    .subAccountNumber(transactionDetails.getSubAccountNumber())
                    .build();

            Map<ProductInfo, Long> productInfoLongMap = map.computeIfAbsent(clientDetails, k -> new HashMap<>());


            ProductInfo productInfo = ProductInfo.builder()
                    .exchangeCode(transactionDetails.getExchangeCode())
                    .expirationDate(transactionDetails.getExpirationDate())
                    .productCode(transactionDetails.getProductCode())
                    .symbol(transactionDetails.getSymbol())
                    .build();

            Long aLong = productInfoLongMap.computeIfAbsent(productInfo, k -> 0L);

            aLong += transactionDetails.getTransactionAmount();

            productInfoLongMap.put(productInfo, aLong);
        }

        return map;
    }

    /**
     * Get records to add to report.
     *
     * @param transactionDetailsList
     * @return
     */
    public List<ReportRecord> getReportRecordsForTxns(final List<TransactionDetails> transactionDetailsList) {
        Map<ClientDetails, Map<ProductInfo, Long>> clientProductInfoTxnMap = getClientDetailsProductInfoTxnsMap(
                transactionDetailsList);

        List<ReportRecord> reportRecords = new ArrayList<>();
        for (Map.Entry<ClientDetails, Map<ProductInfo, Long>> entry : clientProductInfoTxnMap.entrySet()) {
            ClientDetails key = entry.getKey();
            Map<ProductInfo, Long> value = entry.getValue();
            for (Map.Entry<ProductInfo, Long> productInfoLongEntry : value.entrySet()) {
                ReportRecord reportRecord = ReportRecord.builder()
                        .clientInfo(key)
                        .productInfo(productInfoLongEntry.getKey())
                        .totalTransactionAmount(productInfoLongEntry.getValue())
                        .build();
                reportRecords.add(reportRecord);
            }
        }

        System.out.println(String.format("Parsed transactionDetails and generated %d records", reportRecords.size()));
        return reportRecords;
    }
}
