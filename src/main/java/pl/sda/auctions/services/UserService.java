package pl.sda.auctions.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.model.User;
import pl.sda.auctions.repository.UserRepository;

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

    public boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void createUser(String name, String email, String password, Role role) {
        var user = new User(
                null,
                email,
                passwordEncoder.encode(password),
                name,
                true,
                role

        );
        userRepository.save(user);
    }
}

