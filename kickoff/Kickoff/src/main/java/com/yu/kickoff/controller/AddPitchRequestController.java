package com.yu.kickoff.controller;

import com.yu.kickoff.model.AddPitchRequestDTO;
import com.yu.kickoff.service.AddPitchRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class AddPitchRequestController {
    private final AddPitchRequestService addPitchRequestService;

    @Autowired
    public AddPitchRequestController(AddPitchRequestService addPitchRequestService) {
        this.addPitchRequestService = addPitchRequestService;
    }

    @PostMapping(path = "/add-pitch-requests", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createAddPitchRequest(
            @RequestParam Map<String, String> params,
            @RequestPart("ownershipDocumentation") MultipartFile ownershipDocumentation
    ) throws IOException {
        String username = params.get("username");
        String cityName = params.get("cityName");
        String pitchName = params.get("pitchName");
        Double price = Double.valueOf(params.get("price"));
        String address = params.get("address");

        AddPitchRequestDTO request = new AddPitchRequestDTO(username, cityName, pitchName, price, address, ownershipDocumentation.getBytes());
        addPitchRequestService.saveRequest(request);
    }



    @GetMapping(path = "/get-add-pitch-requests")
    public List<Map<String, Object>> getAddPitchRequest () {
        return addPitchRequestService.getAddPitchRequest();
    }

    @DeleteMapping(path = "/reject-add-pitch-requests/{id}")
    public void rejectRequest (@PathVariable Long id) {
        addPitchRequestService.rejectRequest(id);
    }

    @PutMapping(path = "/accept-add-pitch-requests/{id}")
    public void acceptRequest (@PathVariable Long id) {
        addPitchRequestService.acceptRequest(id);
    }
}
