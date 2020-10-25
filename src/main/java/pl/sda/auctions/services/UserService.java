package pl.sda.auctions.services;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.model.User;
import pl.sda.auctions.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean checkIfUserExistsEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean checkIfUserExistsName(String name) {
        return userRepository.findByName(name).isPresent();
    }

    public void createUser(String name, String email, String password, Role role) {
        log.info("Entering createUser(email = {})", email);
        var user = new User(
                null,
                email,
                passwordEncoder.encode(password),
                name,
                true,
                role

        );
        log.info("Creating user: {}", user);
        userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String mail){
        return userRepository.findByEmail(mail);
    }
}

