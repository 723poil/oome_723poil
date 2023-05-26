package org.oome.entity.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.oome.entity.member.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static org.oome.entity.member.QMember.member;

@Repository
public class MemberQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory factory;

    public MemberQuerydslRepository (JPAQueryFactory factory) {
        super(Member.class);
        this.factory =factory;
    }
}
