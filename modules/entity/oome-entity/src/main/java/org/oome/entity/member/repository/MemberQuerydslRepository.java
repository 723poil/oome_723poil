package org.oome.entity.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.oome.entity.member.Member;
import org.oome.entity.member.QMember;
import org.oome.entity.member.dto.MemberTestDto;
import org.oome.entity.member.dto.QMemberTestDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory factory;
    private final QMember member;

    public MemberQuerydslRepository (JPAQueryFactory factory) {
        super(Member.class);
        this.factory =factory;
        this.member = QMember.member;
    }

    public List<MemberTestDto> queryProjectionSample(Member findMember) {
        return factory.select(new QMemberTestDto(
                    member.username,
                    member.nickname
                )).from(member)
                .fetch();
    }
}
