package com.example.webpos.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "Token")
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String id;

    private String refreshToken;

//    @TimeToLive(unit = TimeUnit.MILLISECONDS)
//    private long refreshTokenTTL;

    @Builder
    public RefreshToken(String id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public RefreshToken updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
