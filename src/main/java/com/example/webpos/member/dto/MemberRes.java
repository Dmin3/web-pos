package com.example.webpos.member.dto;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRes {
    private Long memberId;
    private String nickname;
    private String email;
    private MemberType memberType;
    private String phone;

    public static MemberRes of(Member member) {
        return new MemberRes(member);
    }

    public MemberRes(Member member){
        memberId = member.getId();
        nickname= member.getName();
        email = member.getEmail();
        memberType = member.getMemberType();
        phone = member.getPhone();
    }
}
