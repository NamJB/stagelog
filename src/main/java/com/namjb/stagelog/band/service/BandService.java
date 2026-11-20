package com.namjb.stagelog.band.service;


import com.namjb.stagelog.band.dto.BandCreateRequest;
import com.namjb.stagelog.band.dto.BandCreateResponse;
import com.namjb.stagelog.band.dto.MyBandListItemResponse;
import com.namjb.stagelog.band.entity.Band;
import com.namjb.stagelog.band.entity.BandInvite;
import com.namjb.stagelog.band.entity.BandMember;
import com.namjb.stagelog.band.repository.BandInviteRepository;
import com.namjb.stagelog.band.repository.BandMemberRepository;
import com.namjb.stagelog.band.repository.BandRepository;
import com.namjb.stagelog.member.entity.Member;
import com.namjb.stagelog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BandService {

    private final MemberRepository memberRepository;
    private final BandRepository bandRepository;
    private final BandMemberRepository bandMemberRepository;
    private final BandInviteRepository bandInviteRepository;

    //밴드 등록
    @Transactional
    public BandCreateResponse create(String loginId, BandCreateRequest request){

        Member leader = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BadCredentialsException("회원정보를 찾을수없습니다"));

        Band band = bandRepository.save(new Band(request.getName(), leader));

        bandMemberRepository.save(new BandMember(band,leader));

        return new BandCreateResponse(band.getId(),band.getName());


    }

    //my밴드 조회
    @Transactional(readOnly = true)
    public List<MyBandListItemResponse> findMyBands(String loginId){

        List<BandMember> memberships = bandMemberRepository.findByMember_LoginIdAndStatus(
                loginId,BandMember.BandMemberStatus.ACTIVE
        );

        return memberships.stream()
                .map(membership -> {
                    Band band = membership.getBand();
                    return new MyBandListItemResponse(band.getName(),band.getId());
        }).toList();
    }

    //초대코드확인및 생성
    @Transactional
    public BandInvite getInvite(long bandId,String loginId) {

        BandMember membership = bandMemberRepository
                .findByBand_IdAndMember_LoginIdAndStatus(
                        bandId,loginId, BandMember.BandMemberStatus.ACTIVE
                ).orElseThrow(() -> new AccessDeniedException("밴드 멤버만 초대할수있습니다"));

        Instant now = Instant.now();
        BandInvite invite = bandInviteRepository.findByBand_Id(bandId).orElse(null);

        //만료가 아니라면 기존 초대코드 사용
        if(invite != null && invite.getExpiresAt().isAfter(now)){
            return invite;
        }

        String code = UUID.randomUUID().toString();
        Instant expiresAt = now.plus(3, ChronoUnit.DAYS);

        if(invite == null) {
            return bandInviteRepository.save(
                    new BandInvite(membership.getBand(),code,expiresAt)
            );
        }

        invite.renew(code,expiresAt);
        return invite;
    }

}
