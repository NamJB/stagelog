package com.namjb.stagelog.band.repository;

import com.namjb.stagelog.band.entity.Band;
import com.namjb.stagelog.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BandMemberRepository extends JpaRepository<BandMember,Long> {

    List<BandMember> findByMember_LoginIdAndStatus(
            String loginId,
            BandMember.BandMemberStatus status
    );
}
