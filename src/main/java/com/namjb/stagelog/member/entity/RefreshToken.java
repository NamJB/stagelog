package com.namjb.stagelog.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Column(name = "token_hash", nullable = false, unique = true,length = 64)
    private String tokenHash;

    //재발급시 갱신하는 만료시각
    @Column(name = "expires_at" ,nullable = false)
    private Instant expiresAt;

    //최초로그인부터 30일로 고정하는 최대 만료시각
    @Column(name = "absolute_expires_at", nullable = false)
    private Instant absoluteExpiresAt;


    @Builder
    private RefreshToken(
            Member member,
            String tokenHash,
            Instant expiresAt,
            Instant absoluteExpiresAt
    ){
        this.member = member;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.absoluteExpiresAt = absoluteExpiresAt;
    }

    public void rotate(String newTokenHash, Instant newExpiresAt){

        this.tokenHash = newTokenHash;
        this.expiresAt = newExpiresAt;
    }
    public boolean isExpired(Instant now) {

        return !now.isBefore(expiresAt)
                || !now.isBefore(absoluteExpiresAt);
    }



}
