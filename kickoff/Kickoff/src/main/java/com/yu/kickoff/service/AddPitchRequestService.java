package com.yu.kickoff.service;

import com.yu.kickoff.model.*;
import com.yu.kickoff.repository.AddPitchRequestRepository;
import com.yu.kickoff.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddPitchRequestService {

    private final AddPitchRequestRepository addPitchRequestRepository;
    private final UserService userService;
    private final CityService cityService;
    private final UserRepository userRepository;
    private final PitchService pitchService;

    @Autowired
    public AddPitchRequestService(AddPitchRequestRepository addPitchRequestRepository,
                                        UserService userService,
                                        CityService cityService,
                                        UserRepository userRepository,
                                        PitchService pitchService) {
        this.addPitchRequestRepository = addPitchRequestRepository;
        this.userService = userService;
        this.cityService = cityService;
        this.userRepository = userRepository;
        this.pitchService = pitchService;
    }

    public void saveRequest(AddPitchRequestDTO request) {
        User user = userService.getUserByUsername(request.getUsername());
        City city = cityService.getCityByName(request.getCityName());

        AddPitchRequest addPitchRequest = new AddPitchRequest(
                user,
                request.getPitchName(),
                request.getPrice(),
                request.getAddress(),
                request.getOwnershipDocumentation(),
                city
        );

        addPitchRequestRepository.save(addPitchRequest);
    }

    public List<Map<String, Object>> getAddPitchRequest() {
        List<AddPitchRequest> addPitchRequests = addPitchRequestRepository.findAll();

        List<Map<String, Object>> response = new ArrayList<>();

        for (AddPitchRequest item : addPitchRequests) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("username", item.getAuthor().getUsername());
            map.put("city", item.getCity().getName());
            map.put("pitchName", item.getPitchName());
            map.put("address", item.getAddress());
            map.put("price", item.getPrice());
            map.put("ownershipDocumentation", Base64.getEncoder().encodeToString(item.getOwnershipDocumentation()));

            response.add(map);
        }

        return response;
    }

    @Transactional
    public void rejectRequest(Long id) {
        addPitchRequestRepository.deleteById(id);
    }

    @Transactional
    public void acceptRequest(Long id) {
        AddPitchRequest addPitchRequest = addPitchRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("add pitch request with given id doesn't exist !"));

        pitchService.createPitch(addPitchRequest);

        addPitchRequestRepository.deleteById(id);
    }

}
