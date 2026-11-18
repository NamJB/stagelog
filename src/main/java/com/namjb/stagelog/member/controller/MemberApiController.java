package com.namjb.stagelog.member.controller;

import com.namjb.stagelog.common.dto.TokenInfo;
import com.namjb.stagelog.member.dto.MemberLoginRequest;
import com.namjb.stagelog.member.dto.MemberRegisterRequest;
import com.namjb.stagelog.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid MemberRegisterRequest request){



        return ResponseEntity.ok(memberService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody @Valid MemberLoginRequest request){

        return ResponseEntity.ok(memberService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String,String>> me (Authentication authentication){

        return ResponseEntity.of(memberService.findMemberName(authentication.getName())
                .map(name -> Map.of("name",name))
        );
    }






}
