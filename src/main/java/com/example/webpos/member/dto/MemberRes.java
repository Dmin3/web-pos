package com.example.webpos.member.dto;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRes {
    private Long id;
    private String name;
    private String email;
    private MemberType memberType;
    private String phone;

    public static MemberRes of(Member member) {
        return new MemberRes(member);
    }
    
    public MemberRes(Member member){
        id = member.getId();
        name= member.getName();
        email = member.getEmail();
        memberType = member.getMemberType();
        phone = member.getPhone();
    }
}
