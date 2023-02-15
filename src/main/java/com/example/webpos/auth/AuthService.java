package com.example.webpos.auth;

import com.example.webpos.dto.member.MemberLoginReq;
import com.example.webpos.dto.member.MemberRes;
import com.example.webpos.dto.member.MemberSignUpReq;

public interface AuthService {
    MemberRes signup(MemberSignUpReq memberSignUpReq);

    TokenDto login(MemberLoginReq memberLoginReq);

    TokenDto reissue(TokenReq tokenReq);
}
