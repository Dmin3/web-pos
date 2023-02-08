package com.example.webpos.dto.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.domain.member.MemberType;
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
