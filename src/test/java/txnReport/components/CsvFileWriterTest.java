package txnReport.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import txnReport.model.ReportRecord;
import txnReport.util.TestHelper;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileWriterTest {

    private CsvFileWriter csvFileWriter;

    @Before
    public void init() {
        csvFileWriter = new CsvFileWriter();
    }


    @Test
    public void shouldReturnTrueWhenWritingToCsvFileIsSuccessful() {
        List<ReportRecord> reportRecords = TestHelper.reportRecordList();
        boolean success = csvFileWriter.writeRecordsToCsvFile(reportRecords,
                "src/main/resources/outputFiles/output.csv");
        assertTrue(success);
    }

//    @Test
//    public void shouldReturnTrueWhenWritingToCsvFileIsSuccessful2() {
//        List<ReportRecord> reportRecords = TestHelper.reportRecordList();
//        boolean success = csvFileWriter.writeRecordsToCsvFile(reportRecords,
//                "src/main/resources/outputFiles/output.csv");
//        assertTrue(success);
//    }
}