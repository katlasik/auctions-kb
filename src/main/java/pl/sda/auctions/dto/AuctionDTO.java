package pl.sda.auctions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.sda.auctions.model.Status;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDTO {
    @Size(min = 10, max = 200)
    private String title;

    @Size(min = 10, max = 2000)
    private String description;

    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal primaryPrice;

    private boolean status = false;
}
