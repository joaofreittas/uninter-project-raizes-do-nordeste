package com.uninter.gerenciadorunidade.service.auth;

import com.uninter.gerenciadorunidade.controller.auth.dto.AuthRequest;
import com.uninter.gerenciadorunidade.controller.auth.dto.AuthResponse;
import com.uninter.gerenciadorunidade.controller.auth.dto.RegisterRequest;
import com.uninter.gerenciadorunidade.model.auth.User;
import com.uninter.gerenciadorunidade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request) {
        var user = User.from(request, passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authenticationManager.authenticate(authToken);

        var user = userRepository.findByEmail(request.email()).orElseThrow();
        return new AuthResponse(jwtService.generateToken(user));
    }

}
