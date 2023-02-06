package com.example.webpos.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateReq {
    private String name;
    private String password;
    private String phone;
}
