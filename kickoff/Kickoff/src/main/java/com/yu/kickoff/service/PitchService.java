package com.yu.kickoff.service;

import com.yu.kickoff.model.AddPitchRequest;
import com.yu.kickoff.model.City;
import com.yu.kickoff.model.Pitch;
import com.yu.kickoff.repository.PitchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PitchService {

    PitchRepository pitchRepository;

    @Autowired
    public PitchService(PitchRepository pitchRepository) {
        this.pitchRepository = pitchRepository;
    }

    @Transactional
    public void createPitch(AddPitchRequest request) {
        Pitch pitch = new Pitch(
            request.getPitchName(),
            request.getPrice(),
            request.getOwnershipDocumentation(),
            request.getAddress(),
            request.getAuthor(),
            request.getCity()
        );

        pitchRepository.save(pitch);
    }

    public List<Map<String, Object>> getAllPitches() {
        List<Map<String, Object>> response = new ArrayList<>();
        List<Pitch> pitchList = pitchRepository.findAll();

        for (Pitch pitch : pitchList) {
            Map<String, Object> item = new HashMap<>();

            item.put("id", pitch.getId());
            item.put("city", pitch.getCityId().getName());
            item.put("address", pitch.getAddress());
            item.put("name", pitch.getName());
            item.put("price", pitch.getPrice());
            item.put("ownerId", pitch.getAuthorId().getId());
            item.put("ownerName", pitch.getAuthorId().getUsername());
            item.put("fullName", pitch.getAuthorId().getFirstName() + " " + pitch.getAuthorId().getLastName());
            item.put("state", pitch.getStatus());
            item.put("phoneNumber", pitch.getAuthorId().getPhoneNumber());

            response.add(item);
        }


        return response;
    }

    public Pitch getPitchByPitchId(Long pitchId) {
        return pitchRepository.findById(pitchId).orElseThrow(() -> new IllegalArgumentException("Pitch Id not found !"));
    }
}
