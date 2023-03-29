package com.example.webpos.repository.member;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RepositoryTest
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
    
    private void hello() {
        System.out.println("존재하면 프린트");
    }

    private Member hello1(Member member) {
        System.out.println("존재하지 않으면 나를 실행해!");
        return member;
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