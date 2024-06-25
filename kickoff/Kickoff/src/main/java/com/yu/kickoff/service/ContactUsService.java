package com.yu.kickoff.service;

import com.yu.kickoff.model.ContactUs;
import com.yu.kickoff.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ContactUsService {

    private ContactsRepository contactsRepository;
    private CityService cityService;
    private ObjectService objectService;

    @Autowired
    public ContactUsService(ContactsRepository contactsRepository, CityService cityService, ObjectService objectService) {
        this.contactsRepository = contactsRepository;
        this.cityService = cityService;
        this.objectService = objectService;
    }

    public List<ContactUs> getAllContact() {
        return contactsRepository.findAll();
    }

    public void createContact(Map<String, Object> requestData) {

        ContactUs contact = new ContactUs(
                objectService.getStringValue(requestData, "firstName"),
                objectService.getStringValue(requestData, "midName"),
                objectService.getStringValue(requestData, "lastName"),
                objectService.getStringValue(requestData, "address1"),
                objectService.getStringValue(requestData, "address2"),
                objectService.getStringValue(requestData, "message"),
                cityService.getCityByName(
                        objectService.getStringValue(requestData, "city")
                )
        );

        contact.setTimestamp(Timestamp.from(Instant.now()));

        contactsRepository.save(contact);
    }
}
