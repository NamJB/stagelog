package com.namjb.stagelog.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Temporal;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 30,unique = true,updatable = false)
    private String loginId;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false , length = 10)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 50)
    private String email;

    @Builder
    private Member(Long id,String loginId,String password,String name,LocalDate birthDate,String email) {

        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }




}
