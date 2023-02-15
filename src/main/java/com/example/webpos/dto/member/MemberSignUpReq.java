package com.example.webpos.dto.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.domain.member.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberSignUpReq {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;
    private String phone;
    private MemberType memberType;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return new Member(
                name,
                email,
                passwordEncoder.encode(password),
                phone,
                memberType
        );
    }

    public MemberSignUpReq(String name, String email, String password, String phone, MemberType memberType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
    }
}
