package com.example.webpos.repository.member;

import com.example.webpos.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    Member findByName(String name);
}
