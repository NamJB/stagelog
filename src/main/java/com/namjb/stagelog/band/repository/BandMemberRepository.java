package com.namjb.stagelog.band.repository;

import com.namjb.stagelog.band.entity.Band;
import com.namjb.stagelog.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BandMemberRepository extends JpaRepository<BandMember,Long> {

    List<BandMember> findByMember_LoginIdAndStatus(
            String loginId,
            BandMember.BandMemberStatus status
    );
    //지정한 밴드에서 회원의 소속과 상태 찾기
    Optional<BandMember> findByBand_IdAndMember_LoginIdAndStatus(
            Long BandId,
            String LoginId,
            BandMember.BandMemberStatus status
    );
}
