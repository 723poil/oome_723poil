package org.oome.entity.question.answer.answerLike.repository;

import org.oome.entity.question.Question;
import org.oome.entity.question.answer.answerLike.AnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerLikeJpaRepository extends JpaRepository<AnswerLike, Long> {
}
