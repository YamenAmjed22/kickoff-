package com.yu.kickoff.service;


import com.yu.kickoff.model.AuthenticationResponse;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectService objectService;
    private final CityService cityService;
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 ObjectService objectService,
                                 CityService cityService,
                                 UserService userService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.objectService = objectService;
        this.cityService = cityService;
        this.userService = userService;

    }

    public AuthenticationResponse register(Map<String, Object> requestData) {

        User user = new User(
            objectService.getStringValue(requestData, "username"),
            objectService.getStringValue(requestData, "firstName"),
            objectService.getStringValue(requestData, "midName"),
            objectService.getStringValue(requestData, "lastName"),
            objectService.getLocalDateValue(requestData, "dob", "yyyy-MM-dd"),
            objectService.getStringValue(requestData, "address"),
            passwordEncoder.encode(objectService.getStringValue(requestData, "password")),
            objectService.getStringValue(requestData, "phoneNumber"),
            cityService.getCityByName(
                    objectService.getStringValue(requestData, "city")
            )
        );

        // check if user already exist. if exist than authenticate the user
        if(repository.findByUsername(user.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        user = repository.save(user);

        return new AuthenticationResponse(user, "User registration was successful");

    }

    public Map<String, Object> authenticate(User request) {

        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalStateException("user not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("user", userService.getUserData(user));
        response.put("message", "User login was successful");

        return response;

    }
}
