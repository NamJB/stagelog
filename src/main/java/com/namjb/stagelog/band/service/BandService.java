package com.namjb.stagelog.band.service;


import com.namjb.stagelog.band.dto.BandCreateRequest;
import com.namjb.stagelog.band.dto.BandCreateResponse;
import com.namjb.stagelog.band.entity.Band;
import com.namjb.stagelog.band.entity.BandMember;
import com.namjb.stagelog.band.repository.BandMemberRepository;
import com.namjb.stagelog.band.repository.BandRepository;
import com.namjb.stagelog.member.entity.Member;
import com.namjb.stagelog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BandService {

    private final MemberRepository memberRepository;
    private final BandRepository bandRepository;
    private final BandMemberRepository bandMemberRepository;

    //밴드 등록
    @Transactional
    public BandCreateResponse create(String loginId, BandCreateRequest request){

        Member leader = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BadCredentialsException("회원정보를 찾을수없습니다"));

        Band band = bandRepository.save(new Band(request.getName(), leader));

        bandMemberRepository.save(new BandMember(band,leader));

        return new BandCreateResponse(band.getId(),band.getName());


    }

}
