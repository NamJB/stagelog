package com.namjb.stagelog.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenInfo {

    private final String grantType;
    private final String accessToken;
}
