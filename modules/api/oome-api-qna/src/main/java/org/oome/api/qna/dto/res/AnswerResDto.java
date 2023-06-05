package org.oome.api.qna.dto.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.oome.entity.question.answer.Answer;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
@Setter
@Getter
public class AnswerResDto implements Serializable {

    private static final long serialVersionUID = 7094577889371001454L;

    private String contents;

    private String createrNickname;

    private LocalDateTime createdDate;

    public AnswerResDto(Answer entity) {
        this.contents = entity.getContents();
        this.createrNickname = entity.getCreater().getNickname();
        this.createdDate = entity.getCreatedDate();
    }
}
