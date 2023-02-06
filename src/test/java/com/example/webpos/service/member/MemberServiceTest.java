package com.example.webpos.service.member;

import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.domain.member.Member;
import com.example.webpos.domain.member.MemberType;
import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("멤버 조회 실패")
    @Test
    void failGetMember() {
        // given
        doReturn(new Member()).when(memberRepository).findByName("test");

        // when
        MemberNotFoundException result = Assertions.assertThrows(MemberNotFoundException.class, () -> memberService.get("test"));

        // then
        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.MEMBER_NOT_FOUND);
    }

    @DisplayName("멤버 등록")
    @Test
    void saveMember() {
        // given
        doReturn(memberSignUpReq().toEntity()).when(memberRepository).save(any(Member.class));

        // when
        Member member = memberService.save(memberSignUpReq());

        // then
        assertThat(member.getId()).isNotNull(); // ID를 DB에서 자동으로 만들어주는데 Mock으로 만들었기때문에 당연히 돌아가지 않는다.
        assertThat(member.getMemberType()).isEqualTo(MemberType.ROLE_NORMAL);
        assertThat(member.getName()).isEqualTo("TEST");
        assertThat(member.getPassword()).isEqualTo(memberSignUpReq().getPassword());
    }

    public MemberSignUpReq memberSignUpReq() {
        return new MemberSignUpReq("TEST", "TEST@TEST", "123", "010-xxxx", MemberType.ROLE_NORMAL);
    }
}