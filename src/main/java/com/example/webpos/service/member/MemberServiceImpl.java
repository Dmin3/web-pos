package com.example.webpos.service.member;

import com.example.webpos.common.error.exception.MemberNotFoundException;
import com.example.webpos.domain.member.Member;
import com.example.webpos.dto.member.MemberSignUpReq;
import com.example.webpos.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member get(String name) {
        Member member = memberRepository.findByName(name);

        if (member != null) {
            throw new MemberNotFoundException();
        }

        return null;
    }

    @Override
    public Member save(MemberSignUpReq req) {
        return memberRepository.save(req.toEntity());
    }
}
