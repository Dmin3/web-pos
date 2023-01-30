package com.example.webpos.service.member;

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
    public Boolean save(MemberSignUpReq req) {
        memberRepository.save(req.toEntity());
        return true;
    }
}
