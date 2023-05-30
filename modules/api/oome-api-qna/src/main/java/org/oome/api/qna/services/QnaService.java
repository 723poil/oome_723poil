package org.oome.api.qna.services;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.AnswerSaveReqDto;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.entity.question.Question;
import org.oome.entity.question.answer.repository.AnswerJpaRepository;
import org.oome.entity.question.repository.QuestionJpaRepository;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final AnswerJpaRepository answerJpaRepository;

    private final QuestionJpaRepository questionJpaRepository;

    private final MemberJpaRepository memberJpaRepository;

    public Long saveArticle(QuestionSaveReqDto reqDto) {
        // 로그인 기능 구현 전 임시
        Member member = memberJpaRepository.findByUsername("admin").orElseThrow(IllegalArgumentException::new);
        reqDto.setCreater(member);
        return questionJpaRepository.save(reqDto.toEntity()).getId();
    }

    public Long saveComment(Long questionId, AnswerSaveReqDto reqDto) {
        // 로그인 기능 구현 전 임시
        Member member = memberJpaRepository.findByUsername("admin").orElseThrow(IllegalArgumentException::new);
        Question question = questionJpaRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);

        reqDto.setQuestion(question);
        reqDto.setCreater(member);

        return answerJpaRepository.save(reqDto.toEntity()).getId();
    }
}
