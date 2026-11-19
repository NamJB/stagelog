package com.namjb.stagelog.band.repository;

import com.namjb.stagelog.band.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band,Long> {
}
