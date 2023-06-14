package org.oome.api.qna.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.oome.entity.member.Member;
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

    @Schema(description = "작성자")
    private Member creater;

    @Schema(description = "QA구분")
    private QnaType qnaType;

    @Schema(description = "답변일 경우")
    private Qna parentQna;

    @Schema(description = "태그")
    private List<QnaTagSaveReqDto> tagList;

    public Qna toEntity() {
        if (qnaType.equals(QnaType.A) && ObjectUtils.isEmpty(parentQna)) {
            throw new IllegalArgumentException("부모 QNA가 비어있습니다.");
        }

        return Qna.builder()
                .title(title)
                .contents(contents)
                .creater(creater)
                .qnaType(qnaType)
                .parentQna(parentQna)
                .build();
    }
}
