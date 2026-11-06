package com.namjb.stagelog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String code;

    private final  String message;

    private final Map<String,String> error;
}
