package pl.sda.auctions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.auctions.model.Auction;
import pl.sda.auctions.model.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Page<Auction> findAll(Pageable pageable);
    Optional<Auction> findByTitle(String title);
    Optional<List<Auction>> findAllByStatus(Status status);
}
