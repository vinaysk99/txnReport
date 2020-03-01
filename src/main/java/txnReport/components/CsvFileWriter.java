package txnReport.components;

import org.springframework.stereotype.Component;
import txnReport.model.ReportRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class CsvFileWriter {

    /**
     * Write records to csv file.
     *
     * @param reportRecords
     * @return
     */
    public boolean writeRecordsToCsvFile(
            final List<ReportRecord> reportRecords,
            final String outputFile) {

        StringBuilder sb = new StringBuilder();
        sb.append("Client_Information,Product_Information,Total_Transaction_Amount");
        sb.append('\n');

        try (PrintWriter writer = new PrintWriter(new File(outputFile))) {
            for (ReportRecord reportRecord : reportRecords) {
                sb.append(reportRecord.getClientInfo());
                sb.append(reportRecord.getProductInfo());
                sb.append(reportRecord.getTotalTransactionAmount());
                sb.append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Exception trying to write to csv file");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
