package com.example.webpos.auth;

import com.example.webpos.member.domain.Member;
import com.example.webpos.member.dto.MemberLoginReq;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberSignUpReq;
import com.example.webpos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional
    public MemberRes signup(MemberSignUpReq memberSignUpReq) {
        if (memberRepository.existsByEmail(memberSignUpReq.getEmail())) {
            throw new RuntimeException("이미 가입 되어 있는 유저입니다.");
        }

        Member member = memberSignUpReq.toEntity(passwordEncoder);

        memberRepository.save(member);


        return MemberRes.of(member);
    }

    @Override
    @Transactional
    public TokenDto login(MemberLoginReq memberLoginReq) {
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginReq.toAuthentication();

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authenticate);

        RefreshToken refreshToken = RefreshToken.builder().id(authenticate.getName()).refreshToken(tokenDto.getRefreshToken()).build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Override
    @Transactional
    public TokenDto reissue(TokenReq tokenReq) {
        if (!tokenProvider.validateToken(tokenReq.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenReq.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName()).orElseThrow(() -> new RuntimeException("로그아웃한 유저입니다."));

        if (!refreshToken.getRefreshToken().equals(tokenReq.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateRefreshToken(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return tokenDto;
    }
}
