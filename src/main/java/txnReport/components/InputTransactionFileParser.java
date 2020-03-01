package txnReport.components;

import org.springframework.stereotype.Component;
import txnReport.model.TransactionDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class InputTransactionFileParser {

    /**
     * Parse a line and return transaction details
     * @param line
     * @return
     */
    public TransactionDetails parseLineAndGetTransactionObject(final String line) {
        TransactionDetails transactionDetails = null;
        try {
            String recordCode = line.substring(0, 3).trim();
            String clientType = line.substring(3, 6).trim();
            String clientNumber = line.substring(7, 11).trim();
            String accountNumber = line.substring(11, 15).trim();
            String subAccountNumber = line.substring(15, 19).trim();
            String productGroupCode = line.substring(25, 27).trim();
            String exchangeCode = line.substring(27, 31).trim();
            String symbol = line.substring(31, 37).trim();
            String expirationDate = line.substring(37, 45).trim();
            String currencyCode = line.substring(45, 48).trim();

            char quantityLongSign = line.charAt(51);
            String quantityLongStr = line.substring(52, 62).trim();
            long quantityLong = Long.parseLong(quantityLongStr);

            char quantityShortSign = line.charAt(62);
            String quantityShortStr = line.substring(63, 73).trim();
            long quantityShort = Long.parseLong(quantityShortStr);

            if (quantityLongSign == '-') {
                quantityLong = -quantityLong;
            }

            if (quantityShortSign == '-') {
                quantityShort = -quantityShort;
            }

            Long transactionAmount = quantityLong - quantityShort;

            transactionDetails = TransactionDetails.builder()
                    .recordCode(recordCode)
                    .clientType(clientType)
                    .clientNumber(clientNumber)
                    .accountNumber(accountNumber)
                    .subAccountNumber(subAccountNumber)
                    .productCode(productGroupCode)
                    .exchangeCode(exchangeCode)
                    .symbol(symbol)
                    .expirationDate(expirationDate)
                    .currencyCode(currencyCode)
                    .quantityLong(quantityLong)
                    .quantityShort(quantityShort)
                    .transactionAmount(transactionAmount)
                    .build();
        } catch (Exception e) {
            System.err.println(String.format("Exception trying to parse a line [%s] from input file", line));
        }

        return transactionDetails;
    }

    /**
     * Parse the transaction file and return the list of TransactionDetails objects.
     * @param inputFilePath
     * @return
     */
    public List<TransactionDetails> parseTransactionsFile(final String inputFilePath) {
        File inputFile = new File(inputFilePath);

        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TransactionDetails transactionDetails = parseLineAndGetTransactionObject(line);
                if (transactionDetails != null) {
                    transactionDetailsList.add(transactionDetails);
                }
            }
        } catch (Exception e) {
            System.err.println(String.format("Exception trying to parse input file [%s]", inputFilePath));
        }

        return transactionDetailsList;
    }
}
