package com.example.webpos.service.member;

import com.example.webpos.auth.AuthServiceImpl;
import com.example.webpos.common.error.ErrorCode;
import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberSignUpReq;
import com.example.webpos.member.repository.MemberRepository;
import com.example.webpos.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("멤버 조회 실패")
    @Test
    void failGetMember() {
        // given
//        doReturn(Optional.empty()).when(memberRepository).findById(1L);
//
//        // when
//        MemberNotFoundException result = Assertions.assertThrows(MemberNotFoundException.class, () -> memberService.get(1L));

        // then
//        assertThat(result.getErrorCode()).isEqualTo(ErrorCode.MEMBER_NOT_FOUND);
    }

    @DisplayName("멤버 등록")
    @Test
    void saveMember() {
        // given
        doReturn(memberSignUpReq().toEntity(passwordEncoder)).when(memberRepository).save(any(Member.class));

        // when
        MemberRes member = authService.signup(memberSignUpReq());

        // then
        assertThat(member.getId()).isNull(); // ID를 DB에서 자동으로 만들어주는데 Mock으로 만들었기때문에 당연히 돌아가지 않는다.
        assertThat(member.getMemberType()).isEqualTo(MemberType.ROLE_NORMAL);
        assertThat(member.getName()).isEqualTo("TEST");
    }

    public MemberSignUpReq memberSignUpReq() {
        return new MemberSignUpReq("TEST", "TEST@TEST", "123", "010-xxxx", MemberType.ROLE_NORMAL);
    }
}