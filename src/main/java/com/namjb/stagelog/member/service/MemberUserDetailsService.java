package com.namjb.stagelog.member.service;

import com.namjb.stagelog.member.entity.Member;
import com.namjb.stagelog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) {

        //아이디로 조회,회원이 없으면 예외 발생
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("회원을 찾을수 없습니다"));

        return User.withUsername(member.getLoginId())
                .password(member.getPassword())
                .roles("USER")
                .build();
    }
}
