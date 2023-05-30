package org.oome.api.article.services;

import lombok.RequiredArgsConstructor;
import org.oome.api.article.dto.req.ArticleSaveReqDto;
import org.oome.entity.article.comment.repository.ArticleCommentJpaRepository;
import org.oome.entity.article.repository.ArticleJpaRepository;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleCommentJpaRepository articleCommentJpaRepository;

    private final ArticleJpaRepository articleJpaRepository;

    private final MemberJpaRepository memberJpaRepository;

    public void saveArticle() {
        Member member = memberJpaRepository.findByUsername("admin").orElseThrow(IllegalArgumentException::new);
        ArticleSaveReqDto saveReqDto = ArticleSaveReqDto
                .builder()
                .title("질문합니다")
                .contents("질문내용")
                .creater(null)
                .build();


    }

    public void saveComment() {

    }
}
