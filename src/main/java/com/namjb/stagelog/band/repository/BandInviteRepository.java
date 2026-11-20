package com.namjb.stagelog.band.repository;

import com.namjb.stagelog.band.entity.BandInvite;
import com.namjb.stagelog.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BandInviteRepository extends JpaRepository<BandInvite,Long> {

    //기존 초대코드 찾기
    Optional<BandInvite> findByBand_Id(Long bandId);

}
