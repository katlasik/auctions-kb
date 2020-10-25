package pl.sda.auctions.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 200)
    @NotEmpty
    @NotNull
    private String title;

    @Size(min = 10, max = 2000)
    @NotEmpty
    @NotNull
    private String description;

    @DecimalMin("0.00")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal primaryPrice;

    @DecimalMin("0.00")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @NotNull
    private User user;

    protected Auction() {}

    public Auction(
            Long id,
            @Size(min = 10, max = 200) @NotEmpty @NotNull String title,
            @Size(min = 10, max = 2000) @NotEmpty @NotNull String description,
            @NotEmpty @NotNull BigDecimal primaryPrice,
            @NotNull Status status, @NotNull User user
    ) {
        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
        this.primaryPrice = Objects.requireNonNull(primaryPrice);
        this.price = Objects.requireNonNull(primaryPrice);
        this.status = Objects.requireNonNull(status);
        this.user = Objects.requireNonNull(user);
    }

    public Long getId() {
        return id;
    }

    public Auction setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Auction setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Auction setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrimaryPrice() {
        return primaryPrice;
    }

    public Auction setPrimaryPrice(BigDecimal primaryPrice) {
        this.primaryPrice = primaryPrice;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Auction setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Auction setStatus(Status status) {
        this.status = status;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", primaryPrice=" + primaryPrice +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return  Objects.equals(title, auction.title) &&
                Objects.equals(description, auction.description) &&
                Objects.equals(primaryPrice, auction.primaryPrice) &&
                Objects.equals(price, auction.price) &&
                Objects.equals(user, auction.user) &&
                status == auction.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, primaryPrice, price, status, user);
    }
}
