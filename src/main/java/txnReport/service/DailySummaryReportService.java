package txnReport.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import txnReport.components.CsvFileWriter;
import txnReport.components.InputTransactionFileParser;
import txnReport.components.TransactionDetailsMapper;
import txnReport.model.ReportRecord;
import txnReport.model.TransactionDetails;

import java.util.List;

@Service
@AllArgsConstructor
public class DailySummaryReportService {

    private final InputTransactionFileParser inputTransactionFileParser;

    private final TransactionDetailsMapper transactionDetailsMapper;

    private final CsvFileWriter csvFileWriter;

    /**
     * Generate daily report of transactions.
     *
     * @param inputFilePath
     * @param outputFilePath
     * @return
     */
    public Boolean generateReport(final String inputFilePath, final String outputFilePath) {
        List<TransactionDetails> transactionDetails = inputTransactionFileParser.parseTransactionsFile(inputFilePath);
        if (transactionDetails == null || transactionDetails.isEmpty()) {
            System.err.println(String.format("No transaction details parsed from inputFile [%s]", inputFilePath));
            return Boolean.FALSE;
        }

        List<ReportRecord> reportRecordsForTxns = transactionDetailsMapper
                .getReportRecordsForTxns(transactionDetails);
        if (reportRecordsForTxns == null || reportRecordsForTxns.isEmpty()) {
            System.err.println("Couldn't generate ReportRecords for the transactions parsed");
            return Boolean.FALSE;
        }

        boolean success = csvFileWriter.writeRecordsToCsvFile(reportRecordsForTxns, outputFilePath);
        if (success) {
            System.out.println(String.format("Parsed file %s, generated %s", inputFilePath, outputFilePath));
        } else {
            System.err.println(String.format("Couldn't parse %s", inputFilePath));
        }
        return success;
    }
}
