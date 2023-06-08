package org.oome.entity.qna.repository;

import org.oome.entity.member.Member;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.QnaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaJpaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findAllByCreaterAndQnaType(Member creater, QnaType qnaType, PageRequest pageable);

    Page<Qna> findAllByQnaType(QnaType qnaType, Pageable pageable);
}
