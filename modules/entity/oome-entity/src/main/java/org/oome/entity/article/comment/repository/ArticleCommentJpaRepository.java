package org.oome.entity.article.comment.repository;

import org.oome.entity.article.comment.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentJpaRepository extends JpaRepository<ArticleComment, Long> {
}
