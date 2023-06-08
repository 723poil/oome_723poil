package org.oome.api.qna.dto.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.oome.entity.qna.Qna;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Data
@Setter
@Getter
public class QnaResDto {
    private static final long serialVersionUID = 8226972483370313001L;

    private String title;

    private String contents;

    private List<QnaResDto> answerList;

    private String createrNickname;

    private LocalDateTime createdDate;

    public QnaResDto(Qna entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.answerList = entity.getAnswerList()
                .stream()
                .map(QnaResDto::new)
                .collect(Collectors.toList());
        this.createrNickname = entity.getCreater().getNickname();
        this.createdDate = entity.getCreatedDate();
    }
}
