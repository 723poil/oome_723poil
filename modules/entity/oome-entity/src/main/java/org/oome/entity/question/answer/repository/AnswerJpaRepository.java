package org.oome.entity.question.answer.repository;

import org.oome.entity.question.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAllByMyAnswer(String username);
}
