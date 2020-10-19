package pl.sda.auctions.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.auctions.dto.UserRegistrationDTO;
import pl.sda.auctions.model.Role;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;

    public void registerUser(UserRegistrationDTO userRegistrationDTO){
        userService.createUser(userRegistrationDTO.getName(), userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword(), Role.USER);
    }

}
