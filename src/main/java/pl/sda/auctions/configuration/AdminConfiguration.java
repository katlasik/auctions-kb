package pl.sda.auctions.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.services.UserService;

import javax.annotation.PostConstruct;

@Configuration
public class AdminConfiguration {

    private final Logger logger = LoggerFactory.getLogger(AdminConfiguration.class);

    private final UserService userService;

    @Value("${admin.password}")
    private String adminPassword;

    public static final String ADMIN_EMAIL = "admin@dummy.pl";

    public AdminConfiguration(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initUsers() {
        if (!userService.checkIfUserExists(ADMIN_EMAIL)) {
            logger.info("Administrator account doesn't exist. Creating new one.");
            userService.createUser(
                    "administrator",
                    ADMIN_EMAIL,
                    adminPassword,
                    Role.ADMIN
            );
        } else {
            logger.info("Administrator account already exists.");
        }
    }
}
