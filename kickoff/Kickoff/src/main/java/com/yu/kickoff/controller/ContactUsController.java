package com.yu.kickoff.controller;

import com.yu.kickoff.model.ContactUs;
import com.yu.kickoff.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class ContactUsController {

    @Autowired
    private ContactUsService contactsService;

    @GetMapping("/get-contact-us")
    public List<ContactUs> getAllContactUs() {
        List<ContactUs> contactUs = contactsService.getAllContact();
        return contactUs;
    }

    @PostMapping("/create-contact-us")
    public void createContact(@RequestBody Map<String, Object> requestData) {
        contactsService.createContact(requestData);
    }
}
