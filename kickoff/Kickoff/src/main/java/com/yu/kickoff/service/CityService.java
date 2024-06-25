package com.yu.kickoff.service;

import com.yu.kickoff.model.City;
import com.yu.kickoff.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getCityByName(String name) {
        City city = cityRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Wrong city name"));
        return city;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


}
