package org.oome.entity.question.answer.repository;

import org.oome.entity.member.Member;
import org.oome.entity.question.answer.Answer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByCreater(Member creater, Pageable pageable);
}
