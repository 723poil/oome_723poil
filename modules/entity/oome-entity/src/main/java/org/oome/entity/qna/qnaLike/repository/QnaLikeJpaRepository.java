package org.oome.entity.qna.qnaLike.repository;

import org.oome.entity.member.Member;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.qnaLike.QnaLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QnaLikeJpaRepository extends JpaRepository<QnaLike, Long> {
    public Optional<QnaLike> findQnaLikeByCreaterAndQna(Member creater, Qna qna);

}
