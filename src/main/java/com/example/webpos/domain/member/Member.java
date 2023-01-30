package com.example.webpos.domain.member;

import com.example.webpos.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String phone;

    public Member(String name, String email, String password, String phone, MemberType memberType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
    }
}
