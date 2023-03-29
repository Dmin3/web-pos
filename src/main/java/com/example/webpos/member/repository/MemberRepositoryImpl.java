package com.example.webpos.member.repository;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.dto.MemberRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.webpos.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findMember(Long memberId) {
        // null 인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성한다.
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member).where(member.id.eq(memberId)).fetchOne());
    }




}
