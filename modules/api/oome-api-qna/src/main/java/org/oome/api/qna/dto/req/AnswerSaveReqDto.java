package org.oome.api.qna.dto.req;

import lombok.*;
import org.oome.entity.member.Member;
import org.oome.entity.question.Question;
import org.oome.entity.question.answer.Answer;

import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
@Deprecated
public class AnswerSaveReqDto implements Serializable {

    private static final long serialVersionUID = 7168241735155120172L;

    private Question question;

    private String content;

    private Member creater;

    public Answer toEntity() {
        return Answer
                .builder()
                .question(question)
                .contents(content)
                .creater(creater)
                .build();
    }
}