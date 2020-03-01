package txnReport.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import txnReport.model.ReportRecord;
import txnReport.model.TransactionDetails;
import txnReport.util.TestHelper;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDetailsMapperTest {

    private TransactionDetailsMapper transactionDetailsMapper;

    @Before
    public void init() {
        transactionDetailsMapper = new TransactionDetailsMapper();
    }

    @Test
    public void shouldGetClientDetailsProductInfoTxnsMap() {
        List<TransactionDetails> transactionDetails = TestHelper.transactionDetailsList();
        List<ReportRecord> reportRecordsForTxns = transactionDetailsMapper
                .getReportRecordsForTxns(transactionDetails);

        List<ReportRecord> reportRecordsExpected = TestHelper.reportRecordList();
        assertThat(reportRecordsForTxns, is(reportRecordsExpected));
    }
}