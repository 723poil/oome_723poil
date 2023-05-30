package org.oome.api.article.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.oome.entity.article.Article;
import org.oome.entity.member.Member;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ArticleSaveReqDto implements Serializable {

    private static final long serialVersionUID = 8009935634530232531L;

    private String title;

    private String contents;

    private Member creater;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .contents(contents)
                .creater(creater)
                .build();
    }
}
