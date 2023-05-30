package org.oome.api.qna.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.oome.entity.question.Question;
import org.oome.entity.member.Member;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class QuestionSaveReqDto implements Serializable {

    private static final long serialVersionUID = 8009935634530232531L;

    @NotEmpty
    @Schema(description = "질문 제목")
    private String title;

    @NotEmpty
    @Schema(description = "질문 내용")
    private String contents;

    @Schema(description = "작성자")
    private Member creater;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .contents(contents)
                .creater(creater)
                .build();
    }
}
