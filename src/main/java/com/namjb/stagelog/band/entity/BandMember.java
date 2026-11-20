package com.namjb.stagelog.band.entity;

import com.namjb.stagelog.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "band_member",
        uniqueConstraints = @UniqueConstraint(columnNames = {"band_id","member_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BandMember {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //밴드 id
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "band_id" ,  nullable = false)
    private Band band;

    //사용자id
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "member_id" , nullable = false)
    private Member member;

    //가입날짜
    @Column(name ="join_at",nullable = false)
    private Instant joinedAt;

    //탈퇴날짜
    @Column(name ="left_at")
    private Instant leftAt;

    //상태값
    public enum BandMemberStatus {
        ACTIVE,//활동중
        LEFT,  //자진탈퇴
        KICKED //강퇴
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BandMemberStatus status;

    //소속 맴버의 기본역할 리더는 leader_id로 판단
    public enum BandMemberRole {
        MEMBER, //기본역할
        MANAGER //운영진
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private BandMemberRole role;

    public BandMember(Band band, Member member){

        this.band = band;
        this.member = member;
        this.joinedAt = Instant.now();
        this.status = BandMemberStatus.ACTIVE;
        this.role = BandMemberRole.MEMBER;
    }

}
