package ru.dzhenbaz.AuthService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenbaz.AuthService.dto.AuthResponse;
import ru.dzhenbaz.AuthService.dto.RegisterRequest;
import ru.dzhenbaz.AuthService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }



}
