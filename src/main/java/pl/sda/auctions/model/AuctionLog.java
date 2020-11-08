package pl.sda.auctions.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AuctionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("0.00")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal oldPrice;

    @DecimalMin("0.00")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal newPrice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime updatedAt;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Auction auction;

    protected AuctionLog() {}

    public AuctionLog(
            Long id,
            @NotEmpty @NotNull BigDecimal oldPrice,
            @NotEmpty @NotNull BigDecimal newPrice,
            @NotNull User user, @NotNull Auction auction
    ) {
        this.id = id;
        this.oldPrice = Objects.requireNonNull(oldPrice);
        this.newPrice = Objects.requireNonNull(newPrice);
        this.auction = Objects.requireNonNull(auction);
        this.user = Objects.requireNonNull(user);
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionLog that = (AuctionLog) o;
        return oldPrice.equals(that.oldPrice) &&
                newPrice.equals(that.newPrice) &&
                updatedAt.equals(that.updatedAt) &&
                user.equals(that.user) &&
                auction.equals(that.auction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oldPrice, newPrice, updatedAt, user, auction);
    }

    @Override
    public String toString() {
        return "AuctionLog{" +
                "id=" + id +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", auction=" + auction +
                '}';
    }
}
