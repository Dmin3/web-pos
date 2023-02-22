package com.example.webpos.auth;

import com.example.webpos.member.dto.MemberLoginReq;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberSignUpReq;

public interface AuthService {
    MemberRes signup(MemberSignUpReq memberSignUpReq);

    TokenDto login(MemberLoginReq memberLoginReq);

    TokenDto reissue(TokenReq tokenReq);
}
