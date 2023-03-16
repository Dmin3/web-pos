package com.example.webpos.member.service;


import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberSignUpReq;
import com.example.webpos.member.dto.MemberUpdateReq;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.support.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@RepositoryTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    private MemberService memberService;

    private Member member = memberSignUpReq().toEntity(new BCryptPasswordEncoder());

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository);
        memberRepository.save(member);
    }

    @Test
    void 멤버_조회_성공() {
        //given
        Long memberId = member.getId();

        //when
        MemberRes findMember = memberService.get(memberId);

        //then
        assertAll(
                () -> assertThat(findMember.getMemberId()).isEqualTo(member.getId()),
                () -> assertThat(findMember.getNickname()).isEqualTo(member.getName())
        );

    }

    @Test
    void 멤버_조회_예외() {
        //given
        Long otherMemberId = member.getId() + 10L;

        //when

        //then
        assertThatThrownBy(() -> memberService.get(otherMemberId)).isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void 멤버_수정_성공(){
        //given
        MemberUpdateReq req = MemberUpdateReq.builder().name("UPDATE_NAME").phone("010-5833").password("1111").build();

        //when
        MemberRes res = memberService.update(member.getId(), req);

        //then
        assertAll(
                () -> assertThat(req.getName()).isEqualTo(res.getNickname()),
                () -> assertThat(req.getPhone()).isEqualTo(res.getPhone())
        );
    }

    @Test
    void 멤버_삭제_성공(){
        //given
        Member saveMember = memberRepository.save(member);

        //when
        memberService.delete(saveMember.getId());

        //then
        assertThat(memberRepository.findById(saveMember.getId())).isEqualTo(Optional.empty());

    }

    private MemberSignUpReq memberSignUpReq() {
        return new MemberSignUpReq("TEST", "TEST@TEST", "123", "010-xxxx", MemberType.ROLE_NORMAL);
    }
}