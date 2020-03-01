package txnReport.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import txnReport.model.TransactionDetails;
import txnReport.util.TestHelper;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InputTransactionFileParserTest {

    private InputTransactionFileParser inputTransactionFileParser;

    @Before
    public void init() {
        inputTransactionFileParser = new InputTransactionFileParser();
    }

    @Test
    public void shouldParseLineAndGetTransactionDetailsObject() throws Exception {
        String line = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
        TransactionDetails transactionDetails = inputTransactionFileParser.parseLineAndGetTransactionObject(line);
        TransactionDetails transactionDetailsExpected = TestHelper.transactionDetails();
        assertThat(transactionDetails, is(transactionDetailsExpected));
    }

    @Test
    public void shouldThrowExceptionWhenInvalidDataTryingToParseLine() {
        String line = "fdsf";
        try {
            inputTransactionFileParser.parseLineAndGetTransactionObject(line);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void shouldParseFileAndGetTransactionDetailsObject() {
        List<TransactionDetails> transactionDetailsList = inputTransactionFileParser.parseTransactionsFile(
                "src/main/resources/inputFiles/inputFile.txt");
        List<TransactionDetails> transactionDetailsExpected = TestHelper.transactionDetailsList();
        assertThat(transactionDetailsList, is(transactionDetailsExpected));
    }
}