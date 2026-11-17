package com.namjb.stagelog.member.controller;

import com.namjb.stagelog.common.dto.TokenInfo;
import com.namjb.stagelog.member.dto.MemberLoginRequest;
import com.namjb.stagelog.member.dto.MemberRegisterRequest;
import com.namjb.stagelog.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    // GET /api/member/me 요청을 처리해요.
    // SecurityConfig의 authenticated() 규칙 때문에 인증이 필요해요.
    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication) {

        // Spring이 SecurityContext에 있는 인증 객체를 매개변수로 전달해요.
        // getName()으로 JWT에 담았던 로그인 아이디를 가져와 응답해요.
        return ResponseEntity.ok(authentication.getName());
    }






}
