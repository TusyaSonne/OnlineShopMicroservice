package ru.dzhenbaz.AuthService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dzhenbaz.AuthService.dto.AuthResponse;
import ru.dzhenbaz.AuthService.dto.RegisterRequest;
import ru.dzhenbaz.AuthService.model.User;
import ru.dzhenbaz.AuthService.repository.UserRepository;
import ru.dzhenbaz.AuthService.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository repository, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        repository.save(user);

        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));
    }

    public AuthResponse login(RegisterRequest request) {
        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException(("User not found")));
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        return new AuthResponse((jwtUtil.generateToken(user.getUsername())));
    }
}
