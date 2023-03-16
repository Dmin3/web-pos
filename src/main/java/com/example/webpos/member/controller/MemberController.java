package com.example.webpos.member.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberUpdateReq;
import com.example.webpos.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public MemberRes me() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return memberService.get(memberId);
    }

    @PatchMapping()
    public MemberRes update(
            @RequestBody MemberUpdateReq memberUpdateReq
    ) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return memberService.update(memberId, memberUpdateReq);
    }

    @DeleteMapping
    public Boolean delete() {
        return memberService.delete(SecurityUtil.getCurrentMemberId());
    }
}
