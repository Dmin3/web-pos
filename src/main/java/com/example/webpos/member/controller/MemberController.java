package com.example.webpos.member.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.service.MemberService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/test")
    public List<MemberRes> findAll() {
        return memberService.findAllMember();
    }

    @GetMapping("/me")
    public MemberRes me() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return memberService.get(memberId);
    }
}
