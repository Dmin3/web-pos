package com.example.webpos.member.service;


import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberUpdateReq;
import com.querydsl.core.Tuple;

import java.util.List;

public interface MemberService {
    List<MemberRes> findAllMember();

    MemberRes get(Long memberId);

    MemberRes update(Long memberId, MemberUpdateReq req);

    Boolean delete(Long memberId);
}
