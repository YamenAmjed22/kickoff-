package com.yu.kickoff.controller;

import com.yu.kickoff.model.User;
import com.yu.kickoff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-role-by-username/{username}")
    public Map<String , String> getRoleByUserName(@PathVariable String username) {
        Map<String , String> response = new HashMap<>() ;
        response.put("role" , userService.getRoleByUserName(username)) ;
        return  response ;
    }

    @GetMapping("/get-user-data-by-username/{username}")
    public Map<String, Object> getUserData(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return userService.getUserData(user);
    }

    @PutMapping("/change-user-profile-image/{username}")
    public void changeProfileImage(
            @PathVariable String username,
            @RequestPart("profileImage") MultipartFile profileImage
    ) throws IOException {
        userService.changeProfileImage(username, profileImage);
    }

    @PutMapping("/edit-profile")
    public void editProfile(@RequestBody Map<String, Object> request) {
        userService.editProfile(request);
    }
}