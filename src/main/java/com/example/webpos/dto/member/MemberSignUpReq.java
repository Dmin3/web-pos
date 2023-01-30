package com.example.webpos.dto.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.domain.member.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpReq {
    private String name;
    private String email;
    private String password;
    private String phone;
    private MemberType memberType;

    public Member toEntity() {
        return new Member(
                name,
                email,
                password,
                phone,
                memberType
        );
    }
}
