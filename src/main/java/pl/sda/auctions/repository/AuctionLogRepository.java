package pl.sda.auctions.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.auctions.model.AuctionLog;

import java.util.List;

@Repository
public interface AuctionLogRepository  extends JpaRepository<AuctionLog, Long> {
    List<AuctionLog> findAllByAuctionId(Long id, Sort sort);
}
