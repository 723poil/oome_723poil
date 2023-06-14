package org.oome.api.qna.dto.req;

import lombok.*;
import org.oome.entity.qna.Qna;
import org.oome.entity.qna.qnaTag.QnaTag;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class QnaTagSaveReqDto {

    private String tag;

    public QnaTag toEntity(Qna parent) {
        return QnaTag.builder()
                .tag(tag)
                .qna(parent)
                .build();
    }
}
