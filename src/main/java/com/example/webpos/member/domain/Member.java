package com.example.webpos.member.domain;

import com.example.webpos.common.BaseTimeEntity;
import com.example.webpos.member.dto.MemberUpdateReq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column(nullable = false, unique = true)
    private String phone;

    @Builder
    public Member(String name, String email, String password, String phone, MemberType memberType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
    }

    public Member update(MemberUpdateReq req) {
        this.name = req.getName();
        this.password = req.getPassword();
        this.phone = req.getPhone();
        return this;
    }
}
