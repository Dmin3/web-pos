package com.example.webpos.controller;

import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public Boolean signUp(@RequestBody MemberSignUpReq req) {
        return memberService.save(req);
    }
}