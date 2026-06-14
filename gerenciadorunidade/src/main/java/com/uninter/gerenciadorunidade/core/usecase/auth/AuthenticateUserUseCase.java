package com.uninter.gerenciadorunidade.core.usecase.auth;

import com.uninter.gerenciadorunidade.core.exception.DomainException;
import com.uninter.gerenciadorunidade.core.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final UserGateway userGateway;
    private final AuthenticationManager authenticationManager;

    public String execute(final String email, final String password,
                          final java.util.function.Function<org.springframework.security.core.userdetails.UserDetails, String> tokenGenerator) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var user = userGateway.findByEmail(email)
            .orElseThrow(() -> new DomainException("User not found: " + email));
        return tokenGenerator.apply(buildUserDetails(user));
    }

    private org.springframework.security.core.userdetails.UserDetails buildUserDetails(
        com.uninter.gerenciadorunidade.core.domain.UserDomain user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities("ROLE_" + user.getRole().name())
            .build();
    }

}
