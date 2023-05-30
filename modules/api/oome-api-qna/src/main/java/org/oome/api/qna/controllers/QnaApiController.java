package org.oome.api.qna.controllers;

import lombok.RequiredArgsConstructor;
import org.oome.api.qna.dto.req.AnswerSaveReqDto;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.api.qna.services.QnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @PostMapping(value = "/question", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> saveQuestion(@Valid @RequestBody QuestionSaveReqDto reqDto) {
        return ResponseEntity.ok(qnaService.saveArticle(reqDto));
    }

    @PostMapping(value = "/question/{questionId}/answer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> saveAnswer(@PathVariable("questionId") Long questionId,
                                           @Valid @RequestBody AnswerSaveReqDto reqDto) {
        return ResponseEntity.ok(qnaService.saveComment(questionId, reqDto));
    }
}
