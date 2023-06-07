package org.oome.api.qna.controllers;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.AnswerSaveReqDto;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.api.qna.dto.res.AnswerResDto;
import org.oome.api.qna.dto.res.QuestionResDto;
import org.oome.api.qna.services.QnaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public ResponseEntity<Long> saveQuestion(@Valid @RequestBody QuestionSaveReqDto reqDto) {
        return ResponseEntity.ok(qnaService.saveQuestion(reqDto));
    }

    /**
     * 답변을 저장한다.
     * @param questionId 질문 시퀀스(id)
     * @param reqDto 답변 정보
     * @return 저장된 시퀀스(id)
     */
    //@Secured("ROLE_MEMBER")
    @PostMapping(value = "/question/{questionId}/answer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> saveAnswer(
            @PathVariable("questionId") Long questionId,
            @Valid @RequestBody AnswerSaveReqDto reqDto) {
        return ResponseEntity.ok(qnaService.saveAnswer(questionId, reqDto));
    }

    /**
     * 질문 리스트를 가져온다.
     * @param pageIndex 페이지 인덱스
     * @param pageSize 페이지 사이즈
     * @return 질문 리스트(Page)
     */
    @GetMapping("/list")
    public ResponseEntity<List<QuestionResDto>> getQuestionList(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        return ResponseEntity.ok(qnaService.getQuestionList(pageable));
    }

    @GetMapping("/my/answer")
    public ResponseEntity<List<AnswerResDto>> getMyAnswerList(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        return ResponseEntity.ok(qnaService.getMyAnswerList(pageable));

    }
}
