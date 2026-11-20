package com.namjb.stagelog.band.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class BandInviteResponse {

    //초대코드
    private final String code;
    //만료시각
    private final Instant expiresAt;

}
