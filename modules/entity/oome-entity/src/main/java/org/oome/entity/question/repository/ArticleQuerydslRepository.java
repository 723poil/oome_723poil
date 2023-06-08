package org.oome.entity.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.oome.entity.question.Question;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public class ArticleQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory factory;

    public ArticleQuerydslRepository (JPAQueryFactory factory) {
        super(Question.class);
        this.factory = factory;
    }


}
