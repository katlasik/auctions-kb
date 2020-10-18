package pl.sda.auctions.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.model.User;
import pl.sda.auctions.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean checkIfUserExistsEmail(String email) {
        boolean existsEmail = userRepository.findByEmail(email).isPresent();
        return existsEmail;
    }

    public boolean checkIfUserExistsName(String name) {
        boolean existsName = userRepository.findByName(name).isPresent();
        return existsName;
    }

    public void createUser(String name, String email, String password, Role role) {
        log.info("entering createUser...{}", email);
        var user = new User(
                null,
                email,
                passwordEncoder.encode(password),
                name,
                true,
                role

        );
        log.info("user {}", user);
        userRepository.save(user);
    }
}

