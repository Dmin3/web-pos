package com.example.webpos.domain.member;

import com.example.webpos.domain.BaseTimeEntity;
import com.example.webpos.dto.member.MemberUpdateReq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void update(MemberUpdateReq req) {
        this.name = req.getName();
        this.password = req.getPassword();
        this.phone = req.getPhone();

    }
}
