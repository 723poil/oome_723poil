package org.oome.api.qna.services;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.QnaSaveReqDto;
import org.oome.api.qna.dto.res.QnaResDto;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.QnaType;
import org.oome.entity.qna.repository.QnaJpaRepository;
import org.oome.infra.utils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QnaService {

//    private final AnswerJpaRepository answerJpaRepository;
//
//    private final QuestionJpaRepository questionJpaRepository;

    private final MemberJpaRepository memberJpaRepository;

    private final QnaJpaRepository qnaJpaRepository;

    @Transactional
    public Long saveQuestion(@NonNull QnaSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
//        reqDto.setCreater(member);
//        return questionJpaRepository.save(reqDto.toEntity()).getId();
        reqDto.setQnaType(QnaType.Q);
        reqDto.setCreater(member);
        return qnaJpaRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public Long saveAnswer(@NonNull Long parentId, @NonNull QnaSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
//        Question question = questionJpaRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        Qna qna = qnaJpaRepository.findById(parentId).orElseThrow(IllegalArgumentException::new);
        reqDto.setQnaType(QnaType.A);
        reqDto.setParentQna(qna);
        reqDto.setCreater(member);

        return qnaJpaRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public List<QnaResDto> getQnaList(PageRequest pageable) {
        Page<Qna> qnaPageList = qnaJpaRepository.findAllByQnaType(QnaType.Q, pageable);

        return qnaPageList.stream().map(QnaResDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<QnaResDto> getMyAnswerList(PageRequest pageable){
        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
//        return answerJpaRepository.findAllByCreater(member, pageable).stream().map(AnswerResDto::new).collect(Collectors.toList());
        return qnaJpaRepository.findAllByCreater(member, pageable).stream().map(QnaResDto::new).collect(Collectors.toList());
    }
}
