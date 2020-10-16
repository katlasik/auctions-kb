package pl.sda.auctions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.auctions.services.SecurityService;

@Controller
public class HomeController {

    private final SecurityService securityService;

    public HomeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @RequestParam(defaultValue = "false") boolean error) {
        if (securityService.userIsLoggedIn()) {
            return "redirect:";
        } else {
			model.addAttribute("error", error);
			return "login";
		}
    }

}
