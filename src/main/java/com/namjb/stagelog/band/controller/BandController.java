package com.namjb.stagelog.band.controller;


import com.namjb.stagelog.band.dto.BandCreateRequest;
import com.namjb.stagelog.band.dto.BandCreateResponse;
import com.namjb.stagelog.band.service.BandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bands")
@RequiredArgsConstructor
public class BandController {

    private final BandService bandService;


    @PostMapping
    public ResponseEntity<BandCreateResponse> create(
            @RequestBody @Valid BandCreateRequest request,
            Authentication authentication) {

        String loginId = authentication.getName();

        BandCreateResponse response = bandService.create(loginId, request);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
