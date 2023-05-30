package org.oome.entity.question.answer.repository;

import org.oome.entity.question.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
}
