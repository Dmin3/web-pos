package com.example.webpos.member.repository;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.exception.MemberNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    default Member getById(final Long id) {
        return findById(id).orElseThrow(MemberNotFoundException::new);
    }
}
