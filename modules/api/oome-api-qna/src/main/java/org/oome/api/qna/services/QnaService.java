package org.oome.api.qna.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.api.qna.dto.req.QnaSaveReqDto;
import org.oome.api.qna.dto.res.QnaResDto;
import org.oome.core.api.exception.runtime.SecurityContextAuthenticationEmptyException;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.QnaType;
import org.oome.entity.qna.qnaLike.QnaLike;
import org.oome.entity.qna.qnaLike.repository.QnaLikeJpaRepository;
import org.oome.entity.qna.qnaTag.repository.QnaTagJpaRepository;
import org.oome.entity.qna.repository.QnaJpaRepository;
import org.oome.infra.utils.SecurityUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class QnaService {
    private final MemberJpaRepository memberJpaRepository;

    private final QnaJpaRepository qnaJpaRepository;

    private final QnaLikeJpaRepository qnaLikeJpaRepository;

    private final QnaTagJpaRepository qnaTagJpaRepository;

    @Transactional
    @ExceptionHandler(NullPointerException.class)
    public Long saveQuestion(@NonNull QnaSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(IllegalArgumentException::new);
//        reqDto.setCreater(member);
//        return questionJpaRepository.save(reqDto.toEntity()).getId();
        reqDto.setQnaType(QnaType.Q);
//        reqDto.setCreater(member);
        Qna saveQna = reqDto.toEntity();
        saveQna.setCreater(member);
        Qna qna = qnaJpaRepository.save(saveQna);

        log.info("[QnA] 질문 저장 완료");
        if (reqDto.getTagList() != null){
            log.info("[QnA] 질문 태그 O");
            qnaTagJpaRepository.saveAll(reqDto.getTagList().stream().map(e -> e.toEntity(qna)).collect(Collectors.toList()));
        }
        return qna.getId();
    }

    @Transactional
    public Long saveAnswer(@NonNull Long parentId, @NonNull QnaSaveReqDto reqDto) {

        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(SecurityContextAuthenticationEmptyException::new);
//        Question question = questionJpaRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        Qna qna = qnaJpaRepository.findById(parentId).orElseThrow(IllegalArgumentException::new);
        reqDto.setQnaType(QnaType.A);
//        reqDto.setParentQna(qna);
//        reqDto.setCreater(member);
        Qna saveQna = reqDto.toEntity();
        saveQna.setParentQna(qna);
        saveQna.setCreater(member);

        return qnaJpaRepository.save(saveQna).getId();
    }

    @Transactional
    public List<QnaResDto> getQnaList(PageRequest pageable) {
        Page<Qna> qnaPageList = qnaJpaRepository.findAllByQnaType(QnaType.Q, pageable);

        return qnaPageList.stream().map(QnaResDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<QnaResDto> getMyAnswerList(PageRequest pageable){
        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(SecurityContextAuthenticationEmptyException::new);
//        return answerJpaRepository.findAllByCreater(member, pageable).stream().map(AnswerResDto::new).collect(Collectors.toList());
        return qnaJpaRepository.findAllByCreaterAndQnaType(member, QnaType.A, pageable).stream().map(QnaResDto::new).collect(Collectors.toList());
    }

    @Cacheable(
            value = "myQuestions",
            cacheManager = "cacheManagerServer2",
            keyGenerator = "customKeyGenerator"
    )
    @Transactional
    public List<QnaResDto> getMyQuestionList(PageRequest pageable){
        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(SecurityContextAuthenticationEmptyException::new);
        return qnaJpaRepository.findAllByCreaterAndQnaType(member, QnaType.Q, pageable).stream().map(QnaResDto::new).collect(Collectors.toList());
    }


    @Transactional
    public Long saveQuestionLike(@NonNull Long questionId) {

        Qna qna = qnaJpaRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
        Member member = memberJpaRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(SecurityContextAuthenticationEmptyException::new);
        QnaLike like;

        //Select 유저ID에 해당하는 Question번호 존재하는가? 존재가 한다면 Y 인가 N 인가??  Y일 경우 N으로 수정 or Delete, N일 경우 Y로 isLike update.
        //TODO 화면을 어떻게 기획할건지에 따라 로직 수정 필요.
        try {
            like = qnaLikeJpaRepository.findQnaLikeByCreaterAndQna(member, qna).orElseThrow(IllegalArgumentException::new);
            like.setQna(qna);
            like.setCreater(member);
        } catch (IllegalArgumentException e) {
            like = new QnaLike();
            like.setQna(qna);
            like.setCreater(member);
        } catch (Exception e){
            e.printStackTrace();
            return 1L;
        }

        if (like.getIsLike() == null || like.getIsLike().getValue().equals("N")) {
            like.setIsLike(YN.Y);
            return qnaLikeJpaRepository.save(like).getId();
        } else {
            qnaLikeJpaRepository.delete(like);
            return 0L;
        }
    }

//    @Transactional
//    public List<QnaResDto> getQnaTagList(@NonNull Long parentId){
//        Qna qna = qnaJpaRepository.findById(parentId).orElseThrow(IllegalArgumentException::new);
//        //return qnaTagJpaRepository.findAllById(parentId).stream().map(QnaResDto::new).collect(Collectors.toList());
//        return qna.getTagList().stream().map(QnaResDto::new).collect(Collectors.toList());
//    }

//    @Transactional
//    public void saveQnaTag(@NonNull ){
//        qnaTagJpaRepository.save();
//    }
}
