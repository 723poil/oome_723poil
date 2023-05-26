package org.oome.entity.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.oome.entity.article.Article;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static org.oome.entity.article.QArticle.article;

@Repository
public class ArticleQuerydslRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory factory;

    public ArticleQuerydslRepository (JPAQueryFactory factory) {
        super(Article.class);
        this.factory = factory;
    }


}
