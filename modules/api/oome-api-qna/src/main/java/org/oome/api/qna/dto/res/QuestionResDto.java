package org.oome.api.qna.dto.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.oome.entity.question.Question;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Data
@Setter
@Getter
public class QuestionResDto implements Serializable {

    private static final long serialVersionUID = 8226972483370313001L;

    private String title;

    private String contents;

    private List<AnswerResDto> answerList;

    private String createrNickname;

    private LocalDateTime createdDate;

    public QuestionResDto(Question entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.answerList = entity.getAnswers()
                .stream()
                .map(AnswerResDto::new)
                .collect(Collectors.toList());
        this.createrNickname = entity.getCreater().getNickname();
        this.createdDate = entity.getCreatedDate();
    }
}
