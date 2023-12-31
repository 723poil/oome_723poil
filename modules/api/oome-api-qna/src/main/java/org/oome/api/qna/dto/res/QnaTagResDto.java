package org.oome.api.qna.dto.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.oome.entity.qna.qnaTag.QnaTag;

@ToString
@Data
@Setter
@Getter
public class QnaTagResDto {
    private static final  long serialVersionUID = 1714757760720822235L;

    private String tag;

    public QnaTagResDto(QnaTag qnaTag) {
        this.tag = qnaTag.getTag();
    }
}
