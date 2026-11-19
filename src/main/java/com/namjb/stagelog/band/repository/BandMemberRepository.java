package com.namjb.stagelog.band.repository;

import com.namjb.stagelog.band.entity.Band;
import com.namjb.stagelog.band.entity.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandMemberRepository extends JpaRepository<BandMember,Long> {
}
