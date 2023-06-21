package org.oome.api.qna.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.QnaType;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class QnaSaveReqDto implements Serializable {

    private static final long serialVersionUID = 9197904673941443999L;

    @Schema(description = "질문 제목")
    private String title;

    @NotEmpty(message = "질문내용 {invalid.NotEmpty}")
    @Schema(description = "질문 내용")
    private String contents;

    @Schema(description = "QA구분")
    private QnaType qnaType;

    @Schema(description = "태그")
    private List<QnaTagSaveReqDto> tagList;

    public Qna toEntity() {

        return Qna.builder()
                .title(title)
                .contents(contents)
                .qnaType(qnaType)
                .build();
    }
}
