package com.namjb.stagelog.band.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "band_invite")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BandInvite {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //밴드당 초대코드 행 하나만 생기게하기위해 band_id 유일제약
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "band_id",nullable = false)
    private Band band;

    //코드 발급 코드로 밴드찾기 떄문에 유니크
    @Column(nullable = false,unique = true)
    private String code;

    //만료날짜
    @Column(name = "expires_at",nullable = false)
    private Instant expiresAt;

    public BandInvite(Band band,String code,Instant expiresAt){

        this.band = band;
        this.code = code;
        this.expiresAt = expiresAt;
    }

    public void renew(String code,Instant expiresAt) {

        this.code = code;
        this.expiresAt = expiresAt;

    }


}
