package pl.sda.auctions.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import pl.sda.auctions.dto.AuctionDTO;
import pl.sda.auctions.dto.AuctionLogDTO;
import pl.sda.auctions.model.Auction;
import pl.sda.auctions.model.AuctionLog;
import pl.sda.auctions.model.Status;
import pl.sda.auctions.model.User;
import pl.sda.auctions.services.AuctionLogService;
import pl.sda.auctions.services.AuctionService;
import pl.sda.auctions.services.SecurityService;
import pl.sda.auctions.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;
    private final AuctionLogService auctionLogService;

    @GetMapping("/create_auction")
    public String getCreateAuction(Model model){
        AuctionDTO auction = new AuctionDTO();
        model.addAttribute("auction", auction);
        return "create_auction";
    }

    @GetMapping("/auctions")
    public String getAuctions(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Auction> page = auctionService.getAllAuctions(pageable);
        model.addAttribute("auctions", page);
        return "auctions";
    }

    @GetMapping("/auctions/{id}")
    public String getAuction(@PathVariable("id") Long id, Model model){
        var result = auctionLogService.checkIfUserCanChangePrice(id);
        model.addAttribute("disablePriceChange", result);

        Auction auction = auctionService.getAuction(id);
        model.addAttribute("auction", auction);

        AuctionLogDTO auctionLogDTO = new AuctionLogDTO(auction.getPrice(), auction.getPrice().add(BigDecimal.ONE),
                SecurityContextHolder.getContext().getAuthentication().getName(), id);

        model.addAttribute("auctionLogDTO", auctionLogDTO);
        return "auction";
    }

    @PostMapping("/create_auction")
    public String postCreateAuction(@ModelAttribute("auction") @Valid AuctionDTO auction, BindingResult bindingResult) throws Exception {
        if(!bindingResult.hasErrors()){
            var status = auction.isStatus() ? Status.OPENED : Status.CREATED;
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            auctionService.createAuction(auction.getTitle(), auction.getDescription(), auction.getPrimaryPrice(), status, email);
            return "redirect:";
        }
        return "create_auction";
    }

    @PutMapping("/auctions")
    @ResponseBody
    public String putPriceOffer(@RequestBody @Valid AuctionLogDTO auctionLogDTO,
                                BindingResult bindingResult) throws Exception{
        Auction auction = auctionService.getAuction(auctionLogDTO.getAuctionId());
        auctionLogDTO.setAuctionId(auction.getId());
        auctionLogDTO.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        auctionLogDTO.setOldPrice(auction.getPrice());

        if(!bindingResult.hasErrors()){
            auctionService.updatePrice(auctionLogDTO.getAuctionId(), auctionLogDTO);
            auctionLogService.createAuctionLog(auctionLogDTO);
            return "Sukces!!!";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Zła cena");
        }
    }
}
