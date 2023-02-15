package com.example.webpos.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {
    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;

    @Builder
    public TokenDto(String grantType, String accessToken, long accessTokenExpiresIn, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
    }
}
