package com.example.webpos.member.repository;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.dto.MemberRes;
import com.querydsl.core.Tuple;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findMember(Long memberId);

    List<MemberRes> findAllMember();
}
