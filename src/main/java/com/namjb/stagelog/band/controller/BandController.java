package com.namjb.stagelog.band.controller;


import com.namjb.stagelog.band.dto.BandCreateRequest;
import com.namjb.stagelog.band.dto.BandCreateResponse;
import com.namjb.stagelog.band.dto.MyBandListItemResponse;
import com.namjb.stagelog.band.service.BandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/my")
    public List<MyBandListItemResponse> findMyBands(Authentication authentication){

        String loginId = authentication.getName();

        return bandService.findMyBands(loginId);
    }


}
