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
public class ClientDetails {

    private String clientType;

    private String clientNumber;

    private String accountNumber;

    private String subAccountNumber;

    @Override
    public String toString() {
        return clientType + '-' + clientNumber + '-' + accountNumber + '-' + subAccountNumber + ',';
    }
}
