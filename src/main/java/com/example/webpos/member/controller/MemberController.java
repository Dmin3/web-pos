package com.example.webpos.member.controller;

import com.example.webpos.auth.SecurityUtil;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberUpdateReq;
import com.example.webpos.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberRes> me() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(memberService.get(memberId));
    }

    @PatchMapping()
    public ResponseEntity<MemberRes> update(
            @RequestBody MemberUpdateReq memberUpdateReq
    ) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(memberService.update(memberId, memberUpdateReq));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        return ResponseEntity.ok(memberService.delete(SecurityUtil.getCurrentMemberId()));
    }
}
