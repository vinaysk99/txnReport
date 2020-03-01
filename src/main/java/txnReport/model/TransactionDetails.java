package txnReport.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {

    private String recordCode;

    private String clientType;

    private String clientNumber;

    private String accountNumber;

    private String subAccountNumber;

    private String productCode;

    private String exchangeCode;

    private String symbol;

    private String expirationDate;

    private String currencyCode;

    private long quantityLong;

    private long quantityShort;

    private long transactionAmount;
}