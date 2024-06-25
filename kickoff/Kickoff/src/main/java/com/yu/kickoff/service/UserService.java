package com.yu.kickoff.service;

import com.yu.kickoff.model.City;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final ObjectService objectService;
    private final UserRepository userRepository;
    private final CityService cityService;

    @Autowired
    public UserService(ObjectService objectService,
                       UserRepository userRepository,
                       CityService cityService) {
        this.objectService = objectService;
        this.userRepository = userRepository;
        this.cityService = cityService;
    }

    public String getRoleByUserName(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalAccessError("the user not found "));
        return user.getRole().name() ;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalAccessError("the user not found "));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> getUserData(User user) {
        Map<String, Object> response = new HashMap<>();

        String profileImage = "";
        if (user.getProfileImage() != null) {
            profileImage = Base64.getEncoder().encodeToString(user.getProfileImage());
        }

        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("firstName", user.getFirstName());
        response.put("midName", user.getMidName());
        response.put("lastName", user.getLastName());
        response.put("role", user.getRole().name());
        response.put("address", user.getAddress());
        response.put("dob", user.getDob());
        response.put("city", user.getCity().getName());
        response.put("phoneNumber", user.getPhoneNumber());
        response.put("status", user.getStatus().name());
        response.put("joinDate", user.getTimestamp().toLocalDateTime().toLocalDate());
        response.put("profileImage", profileImage);
        response.put("login", true);

        return response;
    }

    public void changeProfileImage(String username, MultipartFile profileImage) throws IOException {
        User user = getUserByUsername(username);
        user.setProfileImage(profileImage.getBytes());
        userRepository.save(user);
    }

    public void editProfile(Map<String, Object> request) {

        City city = cityService.getCityByName(objectService.getStringValue(request, "city"));
        User user = getUserByUsername(objectService.getStringValue(request, "username"));

        String firstName = objectService.getStringValue(request, "firstName");
        String midName = objectService.getStringValue(request, "midName");
        String lastName = objectService.getStringValue(request, "lastName");
        String address = objectService.getStringValue(request, "address");
        String phoneNumber = objectService.getStringValue(request, "phoneNumber");
        LocalDate dob = objectService.getLocalDateValue(request, "dob", "yyyy-MM-dd");

        user.setFirstName(firstName);
        user.setMidName(midName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setDob(dob);
        user.setCity(city);

        userRepository.save(user);
    }
}