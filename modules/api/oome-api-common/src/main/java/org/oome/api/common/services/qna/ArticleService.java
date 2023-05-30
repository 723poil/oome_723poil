package org.oome.api.common.services.qna;

import lombok.AllArgsConstructor;
import org.oome.entity.article.repository.ArticleJpaRepository;
import org.oome.entity.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticleJpaRepository articleJpaRepository;

    public ArticleService(ArticleJpaRepository articleJpaRepository) {
        this.articleJpaRepository = articleJpaRepository;
    }


}
