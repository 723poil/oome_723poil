package org.oome.entity.qna.qnaTag.repository;

import org.oome.entity.qna.qnaTag.QnaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaTagJpaRepository extends JpaRepository<QnaTag, Long> {

}
