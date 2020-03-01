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
public class ReportRecord {

    private ClientDetails clientInfo;

    private ProductInfo productInfo;

    private Long totalTransactionAmount;
}
