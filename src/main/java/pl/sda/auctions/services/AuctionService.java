package pl.sda.auctions.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.auctions.model.Auction;
import pl.sda.auctions.model.Status;
import pl.sda.auctions.repository.AuctionRepository;
import pl.sda.auctions.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    public Optional<Auction> findByTitle(String title){
        return auctionRepository.findByTitle(title);
    }

    public void createAuction(String title, String description, BigDecimal primaryPrice, Status status, String email){
        log.info("Entering createAuction(title = {})", title);

        var user = userRepository.findByEmail(email).get();
        var auction = new Auction(null, title, description, primaryPrice, status, user);

        log.info("Creating auction: {}", auction);
        auctionRepository.save(auction);
    }
}
