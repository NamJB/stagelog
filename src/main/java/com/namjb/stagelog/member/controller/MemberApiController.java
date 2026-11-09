package com.namjb.stagelog.member.controller;

import com.namjb.stagelog.member.dto.MemberRegisterRequest;
import com.namjb.stagelog.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberServie;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid MemberRegisterRequest request){



        return ResponseEntity.ok(memberServie.signUp(request));
    }





}
