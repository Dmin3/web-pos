package com.example.webpos.member.repository;

import com.example.webpos.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findMember(Long memberId);


}
