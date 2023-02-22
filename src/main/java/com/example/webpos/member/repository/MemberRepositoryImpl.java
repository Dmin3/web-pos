package com.example.webpos.member.repository;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findMember(Long memberId) {
        QMember member = QMember.member;
        // null 인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성한다.
        return Optional.ofNullable(query.selectFrom(member).fetchOne());
    }
}
