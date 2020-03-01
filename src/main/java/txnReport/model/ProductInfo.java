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
public class ProductInfo {

    private String productCode;

    private String exchangeCode;

    private String symbol;

    private String expirationDate;

    @Override
    public String toString() {
        return productCode + '-' + exchangeCode + '-' + symbol + '-' + expirationDate + ',';
    }
}
