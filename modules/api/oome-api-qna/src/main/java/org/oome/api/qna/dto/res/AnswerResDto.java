package org.oome.api.qna.dto.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.oome.entity.question.answer.Answer;

import java.io.Serializable;

@ToString
@Data
@Setter
@Getter
public class AnswerResDto implements Serializable {

    private static final long serialVersionUID = 7094577889371001454L;

    private String contents;

    private String createrUsername;

    public AnswerResDto(Answer entity) {
        this.contents = entity.getContents();
        this.createrUsername = entity.getCreater().getUsername();
    }
}
