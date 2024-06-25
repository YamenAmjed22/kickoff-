package com.yu.kickoff.controller;

import com.yu.kickoff.model.AuthenticationResponse;
import com.yu.kickoff.model.User;
import com.yu.kickoff.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Map<String, Object> user
            ) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody User request
    ) {
        return authService.authenticate(request);
    }
}
