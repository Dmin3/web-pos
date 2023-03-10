package com.example.webpos.point;

import com.example.webpos.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointDto {
    private Member member;
    private int point;

    public PointDto(Member member, int point) {
        this.member = member;
        this.point = point;
    }
}
