package com.example.webpos.member.dto;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MemberRes(Member member){
        memberId = member.getId();
        nickname= member.getName();
        email = member.getEmail();
        memberType = member.getMemberType();
        phone = member.getPhone();
    }

    @QueryProjection
    public MemberRes(Long id, String name, String email) {
        this.memberId = id;
        this.nickname = name;
        this.email = email;
    }
}
