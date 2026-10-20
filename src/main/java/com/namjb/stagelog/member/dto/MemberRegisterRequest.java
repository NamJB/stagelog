package com.namjb.stagelog.member.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberRegisterRequest {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private LocalDate birthDate;

}
