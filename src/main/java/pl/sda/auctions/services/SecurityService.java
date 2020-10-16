package pl.sda.auctions.services;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    public boolean userIsLoggedIn() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null &&
                authentication.isAuthenticated() &&
                !authenticationTrustResolver.isAnonymous(authentication);
    }

}
