package com.example.webpos.controller;

import com.example.webpos.dto.member.MemberRes;
import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberRes> signUp(@RequestBody @Valid MemberSignUpReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(req));
    }
}
