package com.example.webpos.service.member;

import com.example.webpos.domain.member.Member;
import com.example.webpos.dto.member.MemberSignUpReq;

public interface MemberService {
    Member get(String name);

    Member save(MemberSignUpReq req);
}
