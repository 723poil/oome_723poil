package org.oome.entity.question.questionLike.repository;

import org.oome.entity.question.questionLike.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLikeJpaRepository extends JpaRepository<QuestionLike, Long> {
}
