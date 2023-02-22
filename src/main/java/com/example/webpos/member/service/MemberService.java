package com.example.webpos.member.service;


import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberUpdateReq;

public interface MemberService {
    MemberRes get(Long memberId);

    MemberRes update(Long memberId, MemberUpdateReq req);

    Boolean delete(Long memberId);
}
