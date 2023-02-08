package com.example.webpos.service.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.dto.member.MemberRes;
import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.dto.member.MemberUpdateReq;

public interface MemberService {
    MemberRes get(String name);

    MemberRes save(MemberSignUpReq req);

    MemberRes update(Long memberId, MemberUpdateReq req);

    Boolean delete(Long memberId);
}
