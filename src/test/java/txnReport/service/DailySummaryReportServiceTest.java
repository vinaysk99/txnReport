package txnReport.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import txnReport.components.CsvFileWriter;
import txnReport.components.InputTransactionFileParser;
import txnReport.components.TransactionDetailsMapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DailySummaryReportServiceTest {

    private DailySummaryReportService dailySummaryReportService;

    @Before
    public void setUp() {
        InputTransactionFileParser inputTransactionFileParser = new InputTransactionFileParser();
        TransactionDetailsMapper transactionDetailsMapper = new TransactionDetailsMapper();
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        dailySummaryReportService = new DailySummaryReportService(
                inputTransactionFileParser, transactionDetailsMapper, csvFileWriter);
    }

    @Test
    public void shouldGenerateReportForValidFilesSample() {
        Boolean reportGenerated = dailySummaryReportService.generateReport(
                "src/main/resources/inputFiles/inputFileSample.txt",
                "src/main/resources/outputFiles/outputSample.csv");

        assertTrue(reportGenerated);
    }

    @Test
    public void shouldGenerateReportForValidFiles() {
        Boolean reportGenerated = dailySummaryReportService.generateReport(
                "src/main/resources/inputFiles/inputFile.txt",
                "src/main/resources/outputFiles/output.csv");

        assertTrue(reportGenerated);
    }

    @Test
    public void shouldNotGenerateReportForInvalidFiles() {
        Boolean reportGenerated = dailySummaryReportService.generateReport(
                "src/main/resources/inputFiles/abc.txt",
                "src/main/resources/outputFiles/output.csv");

        assertFalse(reportGenerated);
    }
}