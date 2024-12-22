package porunit.comp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import porunit.comp.data.domain.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    double price;
    Status status;
    String date;
}
