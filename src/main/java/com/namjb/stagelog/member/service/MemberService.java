package com.namjb.stagelog.member.service;

import com.namjb.stagelog.common.authority.JwtTokenProvider;
import com.namjb.stagelog.common.dto.TokenInfo;
import com.namjb.stagelog.common.exception.InvalidInputException;
import com.namjb.stagelog.member.dto.MemberLoginRequest;
import com.namjb.stagelog.member.dto.MemberRegisterRequest;
import com.namjb.stagelog.member.entity.Member;
import com.namjb.stagelog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입

    public String signUp(MemberRegisterRequest request){

        /*Optional<Member> findMember = memberRepository.findByLoginId(request.getLoginId());
        if(findMember.isPresent()) {

            return "중복된 ID 입니다";
        }*/

        if(memberRepository.existsByLoginId(request.getLoginId())) {

            throw new InvalidInputException("loginId", "중복된 ID 입니다.");
        }

        String encodedPassword =  passwordEncoder.encode(request.getPassword());

        Member member = request.toMemberEntity(encodedPassword);

        memberRepository.save(member);
        return "가입완료";
    }

    public TokenInfo login(MemberLoginRequest request) {


        var authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.getLoginId(),
                        request.getPassword()
                );
        Authentication authentication =
                authenticationManager.authenticate(authenticationRequest);



        return jwtTokenProvider.createToken(authentication);
    }

}
