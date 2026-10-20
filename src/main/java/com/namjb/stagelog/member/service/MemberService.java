package com.namjb.stagelog.member.service;

import com.namjb.stagelog.member.dto.MemberRegisterRequest;
import com.namjb.stagelog.member.entity.Member;
import com.namjb.stagelog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    public String signUp(MemberRegisterRequest request){

        Optional<Member> findMember = memberRepository.findByLoginId(request.getLoginId());
        if(findMember.isPresent()) {

            return "중복된 ID 입니다";
        }

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .build();

        memberRepository.save(member);
        return "가입완료";


    }

}
