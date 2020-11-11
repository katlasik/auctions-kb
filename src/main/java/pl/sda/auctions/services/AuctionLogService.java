package pl.sda.auctions.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.auctions.dto.AuctionLogDTO;
import pl.sda.auctions.model.Auction;
import pl.sda.auctions.model.AuctionLog;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.model.User;
import pl.sda.auctions.repository.AuctionLogRepository;
import pl.sda.auctions.repository.AuctionRepository;
import pl.sda.auctions.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuctionLogService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final AuctionLogRepository auctionLogRepository;

    public boolean checkIfUserCanChangePrice(Long id){
        log.info("Entering checkIfUserCanChangePrice(id = {})", id);
        var result = false;
        List<AuctionLog> auctionLogs = auctionLogRepository
                .findAllByAuctionId(id, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Optional<User> user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Auction auction = auctionRepository.findById(id).get();

        if(user.isPresent()){
            if(auction.getUser().equals(user.get())){
                result = true;
            }
            if(user.get().getRole().equals(Role.ADMIN)){
                result = true;
            }
            if(!auctionLogs.isEmpty()){
                if(auctionLogs.get(0).getUser().equals(user.get())){
                    result = true;
                }
            }
        }

        return result;
    }

    public void createAuctionLog(AuctionLogDTO auctionLogDTO){
        log.info("Entering createAuctionLog(user={}, auctionId={})", auctionLogDTO.getEmail(), auctionLogDTO.getAuctionId());

        User user = userRepository.findByEmail(auctionLogDTO.getEmail()).get();
        Auction auction = auctionRepository.findById(auctionLogDTO.getAuctionId()).get();
        AuctionLog auctionLog = new AuctionLog(null, auctionLogDTO.getOldPrice(), auctionLogDTO.getNewPrice(),
                user, auction);
        auctionLogRepository.save(auctionLog);
    }
}
