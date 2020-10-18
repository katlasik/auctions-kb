package pl.sda.auctions.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.auctions.dto.UserRegistrationDTO;
import pl.sda.auctions.services.RegistrationService;
import pl.sda.auctions.services.UserService;

import javax.validation.Valid;

@Slf4j
@Controller(value = "registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/registration")
    public String getRegistration(Model model){
        UserRegistrationDTO registrationUser = new UserRegistrationDTO();
        model.addAttribute("registrationUser", registrationUser);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("registrationUser") @Valid UserRegistrationDTO registrationUser, BindingResult bindingResult){
        if(userService.checkIfUserExistsEmail(registrationUser.getEmail())) {
            bindingResult.rejectValue("email", "registration.emailExists");
        }else if(userService.checkIfUserExistsName(registrationUser.getName())){
            bindingResult.rejectValue("name", "registration.nameExists");
        } else if(!registrationUser.getPassword().equals(registrationUser.getRepeatPassword())){
           bindingResult.rejectValue("password", "registration.differentPasswords");
        } else if(!bindingResult.hasErrors()){
            registrationService.registerUser(registrationUser);
            return "login";
        }
        return "registration";
    }

}
