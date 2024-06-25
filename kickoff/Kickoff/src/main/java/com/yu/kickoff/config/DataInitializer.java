package com.yu.kickoff.config;

import com.yu.kickoff.model.City;
import com.yu.kickoff.model.Score;
import com.yu.kickoff.repository.CityRepository;
import com.yu.kickoff.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CityRepository cityRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public DataInitializer(CityRepository cityRepository,
                           ScoreRepository scoreRepository) {
        this.cityRepository = cityRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void run(String... args) {
        if (cityRepository.count() == 0) {
            List<String> cityNames = Arrays.asList(
                    "Amman", "Irbid", "Zarqa", "Mafraq", "Ajloun", "Jerash",
                    "Madaba", "Balqa", "Karak", "Tafileh", "Maan", "Aqaba"
            );

            for (String cityName : cityNames) {
                City city = new City(cityName);
                cityRepository.save(city);
            }
        }

        if (scoreRepository.count() == 0) {
            scoreRepository.save(new Score("goals", 10L));
            scoreRepository.save(new Score("yellowCard", -5L));
            scoreRepository.save(new Score("redCard", -10L));
            scoreRepository.save(new Score("motm", 30L));
            scoreRepository.save(new Score("fouls", -2L));
        }
    }
}
