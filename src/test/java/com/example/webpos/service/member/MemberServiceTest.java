package com.example.webpos.service.member;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.domain.member.Member;
import com.example.webpos.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("멤버 조회 실패")
    @Test
    void failGetMember(){
        // given
        doReturn(new Member()).when(memberRepository).findByName("test");

        // when
        MemberNotFoundException result = Assertions.assertThrows(MemberNotFoundException.class, () -> memberService.get("test"));

        // then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.MEMBER_NOT_FOUND);

    }





}