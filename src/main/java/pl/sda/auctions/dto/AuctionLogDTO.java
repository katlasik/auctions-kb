package pl.sda.auctions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionLogDTO {
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal oldPrice;

    @DecimalMin("0.00")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal newPrice;

    @Email
    private String email;

    @PositiveOrZero
    private Long auctionId;
}
