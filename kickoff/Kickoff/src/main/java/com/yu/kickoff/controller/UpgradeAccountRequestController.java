package com.yu.kickoff.controller;

import com.yu.kickoff.model.UpgradeAccountRequest;
import com.yu.kickoff.model.UpgradeAccountRequestDTO;
import com.yu.kickoff.service.UpgradeAccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class UpgradeAccountRequestController {

    private final UpgradeAccountRequestService upgradeAccountRequestService;

    @Autowired
    public UpgradeAccountRequestController(UpgradeAccountRequestService upgradeAccountRequestService) {
        this.upgradeAccountRequestService = upgradeAccountRequestService;
    }

    @PostMapping(path = "/upgrade-account-requests")
    public void createUpgradeAccountRequest(
            @RequestPart("username") String username,
            @RequestPart("idCardFace1") MultipartFile idCardFace1,
            @RequestPart("idCardFace2") MultipartFile idCardFace2
    ) throws IOException {
        UpgradeAccountRequestDTO request = new UpgradeAccountRequestDTO(username, idCardFace1.getBytes(), idCardFace2.getBytes());
        upgradeAccountRequestService.saveRequest(request);
    }

    @GetMapping(path = "/get-upgrade-account-requests")
    public List<Map<String, Object>> getUpgradeAccountRequest () {
        return upgradeAccountRequestService.getUpgradeAccountRequest();
    }

    @DeleteMapping(path = "/reject-upgrade-account-requests/{username}")
    public void rejectRequest (@PathVariable String username) {
        upgradeAccountRequestService.rejectRequest(username);
    }

    @PutMapping(path = "/accept-upgrade-account-requests/{username}")
    public void acceptRequest (@PathVariable String username) {
        upgradeAccountRequestService.acceptRequest(username);
    }
}
