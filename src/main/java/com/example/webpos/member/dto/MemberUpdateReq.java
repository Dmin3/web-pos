package com.example.webpos.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateReq {
    private String name;
    private String password;
    private String phone;

    @Builder
    public MemberUpdateReq(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
}
