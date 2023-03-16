package com.example.webpos.member.service;

import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.member.domain.Member;
import com.example.webpos.member.dto.MemberRes;
import com.example.webpos.member.dto.MemberUpdateReq;
import com.example.webpos.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<MemberRes> findAllMember() {
        return memberRepository.findAllMember();
    }

    @Override
    public MemberRes get(Long memberId) {
        return MemberRes.of(memberRepository.findMember(memberId).orElseThrow(MemberNotFoundException::new));
    }

    @Override
    @Transactional
    public MemberRes update(Long memberId, MemberUpdateReq req) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        Member updateMember = member.update(req);

        return MemberRes.of(updateMember);
    }

    @Override
    @Transactional
    public Boolean delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
        return true;
    }
}
