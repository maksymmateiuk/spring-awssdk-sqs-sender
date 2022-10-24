package com.mmateiuk.springawssdksqssender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mmateiuk.springawssdksqssender.model.common.Event;
import com.mmateiuk.springawssdksqssender.model.common.TnDetail;
import com.mmateiuk.springawssdksqssender.service.ProvisionTnService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tn")
public class DemoController {

    private final ProvisionTnService provisionTnService;

    public DemoController(ProvisionTnService provisionTnService) {
        this.provisionTnService = provisionTnService;
    }

    @PostMapping
    public ResponseEntity<TnDetail> provisionTn(@RequestBody TnDetail tnDetail) throws JsonProcessingException {
        TnDetail provisionedTnDetails = provisionTnService.provision(tnDetail);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(provisionedTnDetails);
    }

    @GetMapping
    public ResponseEntity<List<Event>> receivedMessages() {
        List<Event> provisionedTns = provisionTnService.getProvisionedTns();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(provisionedTns);
    }
}
