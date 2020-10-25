package pl.sda.auctions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.auctions.model.Status;

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

    private String primaryPrice;

    private boolean status = false;
}
