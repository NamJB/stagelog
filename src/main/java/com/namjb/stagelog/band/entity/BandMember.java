package com.namjb.stagelog.band.entity;

import com.namjb.stagelog.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "band_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BandMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "band_id" ,  nullable = false)
    private Band band;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "member_id" , nullable = false)
    private Member member;

    public BandMember(Band band, Member member){

        this.band = band;
        this.member = member;
    }

}
