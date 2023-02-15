package com.example.webpos.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReq {
    private String accessToken;
    private String refreshToken;
}
