package com.example.webpos.service.member;


import com.example.webpos.dto.member.MemberRes;
import com.example.webpos.dto.member.MemberUpdateReq;

public interface MemberService {
    MemberRes get(Long memberId);

    MemberRes update(Long memberId, MemberUpdateReq req);

    Boolean delete(Long memberId);
}
