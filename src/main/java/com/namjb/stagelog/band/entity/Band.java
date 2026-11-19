package com.namjb.stagelog.band.entity;

import com.namjb.stagelog.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "band")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "leader_id",nullable = false)
    private Member leader;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    public Band(String name, Member leader){

        this.name = name;
        this.leader = leader;

        this.createdAt = Instant.now();
    }


}
