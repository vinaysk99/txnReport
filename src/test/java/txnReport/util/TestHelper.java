package txnReport.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import txnReport.model.ReportRecord;
import txnReport.model.TransactionDetails;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.annotation.JsonView;

import static org.apache.commons.io.FileUtils.readFileToString;

public class TestHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    private static <T> T loadFromJson(final String filename, final Class<T> objectClass) {
        try {
            String json = readFileAsString(filename);
            return mapper.readValue(json, objectClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File readFile(final String filePath) throws Exception {
        final File sampleMessage = new ClassPathResource(filePath).getFile();
        return sampleMessage;
    }

    private static String readFileAsString(final String filePath) {
        try {
            final File sampleMessage = readFile(filePath);
            return readFileToString(sampleMessage, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TransactionDetails transactionDetails() {
        return loadFromJson("testdata/transactionDetails.json", TransactionDetails.class);
    }

    public static List<TransactionDetails> transactionDetailsList() {
        return Arrays.asList(loadFromJson("testdata/transactionDetailsList.json", TransactionDetails[].class));
    }

    public static List<ReportRecord> reportRecordList() {
        return Arrays.asList(loadFromJson("testdata/reportRecords.json", ReportRecord[].class));
    }
}
