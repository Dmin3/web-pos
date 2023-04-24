package com.example.webpos.auth;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberSignUpReq;
import com.example.webpos.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TokenProvider jwtTokenProvider;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void 회원_가입() {
        MemberSignUpReq signUpReq = new MemberSignUpReq("TEST", "TEST@TEST", "12", "010-111-1111", MemberType.ROLE_NORMAL);

        MemberRes result = authService.signup(signUpReq);

        assertAll(
                () -> assertThat(result.getNickname()).isEqualTo(signUpReq.getName()),
                () -> assertThat(result.getEmail()).isEqualTo(signUpReq.getEmail())
        );
    }

    @Test
    void 회원_가입_실패() {
        //given
        String email = "Test@Test";
        memberRepository.save(new Member("TEST", email, "", "010-111-111", MemberType.ROLE_NORMAL));

        //when
        MemberSignUpReq signUpReq = new MemberSignUpReq("TEST", email, "12", "010-111-1111", MemberType.ROLE_NORMAL);
        assertThatThrownBy(() -> authService.signup(signUpReq)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void 토근_재발급() {
        String testUser = "user";
        String role = MemberType.ROLE_ADMIN.name();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(testUser, "", List.of(new SimpleGrantedAuthority((role))));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);
        RefreshToken refreshToken = RefreshToken.builder().id(authenticationToken.getName()).refreshToken(tokenDto.getRefreshToken()).build();
        refreshTokenRepository.save(refreshToken);

        System.out.println("tokenDto.getAccessToken() = " + tokenDto.getAccessToken());
        System.out.println("tokenDto.getRefreshToken() = " + tokenDto.getRefreshToken());

        TokenReq tokenReq = new TokenReq(tokenDto.getAccessToken(), tokenDto.getRefreshToken());

        TokenDto reissue = authService.reissue(tokenReq);

        System.out.println("reissue.getAccessToken() = " + reissue.getAccessToken());
        System.out.println("reissue.getRefreshToken() = " + reissue.getRefreshToken());
    }


}
