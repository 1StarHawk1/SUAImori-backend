package com.suaimori.backend.controllers;

import com.suaimori.backend.dto.SignUpRequest;
import com.suaimori.backend.dto.SignInRequest;
import com.suaimori.backend.services.AuthenticationService;
import com.suaimori.backend.dto.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public String auth() {
        return "День добрый!";
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
