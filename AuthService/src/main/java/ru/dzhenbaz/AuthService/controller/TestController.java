package ru.dzhenbaz.AuthService.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dzhenbaz.AuthService.security.ClientDetails;

@RestController
public class TestController {

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails clientDetails = (ClientDetails) authentication.getPrincipal();

        return clientDetails.getUsername();
    }
}
