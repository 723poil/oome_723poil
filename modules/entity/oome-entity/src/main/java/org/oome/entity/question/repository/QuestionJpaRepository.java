package org.oome.entity.question.repository;

import org.oome.entity.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface QuestionJpaRepository extends JpaRepository<Question, Long> {
}
