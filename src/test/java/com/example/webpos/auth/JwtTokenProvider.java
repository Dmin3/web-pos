package com.example.webpos.auth;


import com.example.webpos.member.domain.MemberType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class JwtTokenProvider {
    /**
     * JWT 토큰을 검사
     * 1. 토큰 생성
     * 2. 잘못된 토큰 검사
     * 3. 만료된 토큰 검사
     */
    private static final long EXPIRE_TIME = 86400000L; // 1일
    private static final String SECRET_KEY = "c3ByaW5nLXNlY3VyaXR5LXdlYi1wb3MtYnktZG1pbjMtc2RhZGFkYWpkbmFsZG5sYWRubGFzZG5sYWRubGFzbmRsZHNsYWRubAo=";

    private final TokenProvider jwtTokenProvider = new TokenProvider(SECRET_KEY);

    @BeforeEach
    void setUp() {
        String testUser = "user";
        String role = MemberType.ROLE_ADMIN.name();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(testUser, "", List.of(new SimpleGrantedAuthority((role))));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    void 토큰_생성() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        assertThat(tokenDto).isNotNull();
    }

    @Test
    void 잘못된_토큰_검사() {
        assertThat(jwtTokenProvider.validateToken("InvalidToken")).isFalse();
    }

    @Test
    void 만료된_액세스_토큰_검사() {
        // 테스트 authentication 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        // SECRET KEY 복사
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        // 현재 시간 보다 하루 전
        Date now = new Date(new Date().getTime() - EXPIRE_TIME);

        // 만료된 JWT 토큰 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                // 인증 정보
                .claim("auth", authorities)
                .setExpiration(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // 검증
        assertThat(jwtTokenProvider.validateToken(accessToken)).isFalse();
    }
}
