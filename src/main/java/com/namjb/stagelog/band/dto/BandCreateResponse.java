package com.namjb.stagelog.band.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BandCreateResponse {

    private final Long id;
    private final String name;


}
