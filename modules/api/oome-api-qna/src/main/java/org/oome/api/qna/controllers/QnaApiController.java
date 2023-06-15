package org.oome.api.qna.controllers;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.QnaSaveReqDto;
import org.oome.api.qna.dto.res.QnaResDto;
import org.oome.api.qna.services.QnaService;
import org.oome.core.api.http.OomeResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Qna와 관련된 ApiController
 * @author hjhearts
 */
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.qna-url}")
@RestController
public class QnaApiController {

    private final QnaService qnaService;

    /**
     * 질문을 저장한다.
     * @param reqDto 질문 정보
     * @return 저장된 시퀀스(id)
     */
    //@Secured("ROLE_MEMBER")
    @PostMapping(value = "/question", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OomeResponse<Long> saveQuestion(@Valid @RequestBody QnaSaveReqDto reqDto) {
        return new OomeResponse<>(qnaService.saveQuestion(reqDto), HttpStatus.OK);
    }

    /**
     * 답변을 저장한다.
     * @param questionId 질문 시퀀스(id)
     * @param reqDto 답변 정보
     * @return 저장된 시퀀스(id)
     */
    //@Secured("ROLE_MEMBER")
    @PostMapping(value = "/question/{questionId}/answer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OomeResponse<Long> saveAnswer(
            @PathVariable("questionId") Long questionId,
            @Valid @RequestBody QnaSaveReqDto reqDto) {
        return new OomeResponse<>(qnaService.saveAnswer(questionId, reqDto), HttpStatus.OK);
    }

    /**
     * 질문 리스트를 가져온다.
     * @param pageIndex 페이지 인덱스
     * @param pageSize 페이지 사이즈
     * @return 질문 리스트(Page)
     */
    @GetMapping("/list")
    public OomeResponse<List<QnaResDto>> getQuestionList(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        return new OomeResponse<>(qnaService.getQnaList(pageable), HttpStatus.OK);
    }

    /**
     * 내가 쓴 답변 리스트
     * @param pageIndex 페이지 인덱스
     * @param pageSize 페이지 사이즈
     * @return 내가 쓴 답변(page)
     */
    @GetMapping("/creater/answer")
    public OomeResponse<List<QnaResDto>> getMyAnswerList(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        return new OomeResponse<>(qnaService.getMyAnswerList(pageable), HttpStatus.OK);
    }

    /**
     * 내가 쓴 질문 리스트 안에 답변 LIST도 있음
     * @param pageIndex 페이지 인덱스
     * @param pageSize 페이지 사이즈
     * @return 내가 쓴 질문(page)
     */
    @GetMapping("/creater/question")
    public OomeResponse<List<QnaResDto>> getMyQuestionList(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        return new OomeResponse<>(qnaService.getMyQuestionList(pageable), HttpStatus.OK);
    }

    /**
     * 좋아요 누르면 DB에 Y로 저장되고, 다시누르면 DB에서 제거
     * @param questionId 좋아요 누를 질문
     * @return 0 true 1 false or save성공시 getId().
     */
    @PostMapping("/question/{questionId}/like")
    public OomeResponse<Long> saveQuestionLike(
            @PathVariable("questionId") Long questionId
            ){
        return new OomeResponse<>(qnaService.saveQuestionLike(questionId), HttpStatus.OK);
    }
    
}
