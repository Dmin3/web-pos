package com.example.webpos.repository.member;

import com.example.webpos.config.TestConfig;
import com.example.webpos.domain.member.Member;
import com.example.webpos.domain.member.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
@Import(TestConfig.class)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("멤버 등록")
    @Test
    void saveTest() {
        // given
        Member member = new Member("TEST", "test@test", "123", "010-5833-3287", MemberType.ROLE_NORMAL);

        // when
        Member saveMember = memberRepository.saveAndFlush(member);

        // then
        assertThat(member.getId()).isEqualTo(saveMember.getId());
        assertThat(member.getName()).isEqualTo(saveMember.getName());
        assertThat(member.getEmail()).isEqualTo(saveMember.getEmail());
    }

    @DisplayName("멤버 조회")
    @Test
    void getMember() {
        // given
        Member member = new Member("TEST", "test@test", "123", "010-5833-3287", MemberType.ROLE_NORMAL);
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
    }

    @DisplayName("멤버 수정 (더티 체킹)")
    @Test
    @Rollback(value = false)
    void updateMember() {
        // given
        Member member = new Member("TEST", "test@test", "123", "010-5833-3287", MemberType.ROLE_NORMAL);
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // when
        findMember.setEmail("update@test");
        findMember.setName("UPDATE_TEST");
        findMember.setPhone("00");
        findMember.setPassword("0101010");

        // then
        assertThat(findMember.getEmail()).isEqualTo("update@test");
        assertThat(findMember.getName()).isEqualTo("UPDATE_TEST");
        assertThat(findMember.getPhone()).isEqualTo("00");
        assertThat(findMember.getPassword()).isEqualTo("0101010");
    }

    @DisplayName("멤버 삭제")
    @Test
    void deleteMember() {
        // given
        Member member = new Member("TEST", "test@test", "123", "010-5833-3287", MemberType.ROLE_NORMAL);
        memberRepository.saveAndFlush(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // when
        memberRepository.delete(findMember);
        memberRepository.flush();

        // then
        assertThatThrownBy(() -> memberRepository.findById(findMember.getId()).get()).isInstanceOf(NoSuchElementException.class);
    }
}