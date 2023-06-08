package org.oome.entity.qna.qnaLike.repository;

import org.oome.entity.qna.qnaLike.QnaLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaLikeJpaRepository extends JpaRepository<QnaLike, Long> {
}
