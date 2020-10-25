package pl.sda.auctions.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.auctions.dto.AuctionDTO;
import pl.sda.auctions.model.Status;
import pl.sda.auctions.services.AuctionService;
import pl.sda.auctions.services.SecurityService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;
    private final SecurityService securityService;

    @GetMapping("/create_auction")
    public String getCreateAuction(Model model){
        if(securityService.userIsLoggedIn()){
            AuctionDTO auction = new AuctionDTO();
            model.addAttribute("auction", auction);
            return "create_auction";
        } else {
            return "redirect:login";
        }
    }

    @PostMapping("/create_auction")
    public String postCreateAuction(@ModelAttribute("auction") @Valid AuctionDTO auction, BindingResult bindingResult) throws Exception {
        var price = new BigDecimal(auction.getPrimaryPrice()).setScale(2, RoundingMode.HALF_UP);
        if(!bindingResult.hasErrors()){
            var status = auction.isStatus() ? Status.OPENED : Status.CREATED;
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            auctionService.createAuction(auction.getTitle(), auction.getDescription(), price, status, email);
            return "redirect:";
        }
        return "create_auction";
    }
}
