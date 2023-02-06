package com.example.webpos.service.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.dto.member.MemberUpdateReq;

public interface MemberService {
    Member get(String name);

    Member save(MemberSignUpReq req);

    Member update(Long memberId, MemberUpdateReq req);

    Boolean delete(Long memberId);
}
