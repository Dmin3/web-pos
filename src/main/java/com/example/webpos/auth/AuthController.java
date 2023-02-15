package com.example.webpos.auth;

import com.example.webpos.dto.member.MemberLoginReq;
import com.example.webpos.dto.member.MemberRes;
import com.example.webpos.dto.member.MemberSignUpReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberRes> signup(@RequestBody MemberSignUpReq memberSignUpReq) {
        return ResponseEntity.ok(authService.signup(memberSignUpReq));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginReq memberLoginReq) {
        return ResponseEntity.ok(authService.login(memberLoginReq));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenReq tokenReq) {
        return ResponseEntity.ok(authService.reissue(tokenReq));
    }
}
