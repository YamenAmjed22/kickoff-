package com.yu.kickoff.controller;

import com.yu.kickoff.model.City;
import com.yu.kickoff.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")

public class CityController {

    public CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/get-all-cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
}
