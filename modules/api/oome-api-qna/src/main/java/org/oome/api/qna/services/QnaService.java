package org.oome.api.qna.services;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.AnswerSaveReqDto;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.api.qna.dto.res.QuestionResDto;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.entity.question.Question;
import org.oome.entity.question.answer.repository.AnswerJpaRepository;
import org.oome.entity.question.repository.QuestionJpaRepository;
import org.oome.infra.utils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QnaService {

    private final AnswerJpaRepository answerJpaRepository;

    private final QuestionJpaRepository questionJpaRepository;

    private final MemberJpaRepository memberJpaRepository;


    @Transactional
    public Long saveQuestion(@NonNull QuestionSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
        reqDto.setCreater(member);
        return questionJpaRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public Long saveAnswer(Long questionId, @NonNull AnswerSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
        Question question = questionJpaRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);

        reqDto.setQuestion(question);
        reqDto.setCreater(member);

        return answerJpaRepository.save(reqDto.toEntity()).getId();
    }

    @Transactional
    public Page<QuestionResDto> getQuestionList(PageRequest pageable) {
        Page<Question> questionPage = questionJpaRepository.findAll(pageable);

        List<QuestionResDto> dtoList = questionPage.stream().map(QuestionResDto::new).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, questionPage.getTotalElements());
    };
}
