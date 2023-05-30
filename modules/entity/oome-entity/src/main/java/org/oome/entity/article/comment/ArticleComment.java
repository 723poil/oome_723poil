package org.oome.entity.article.comment;

import lombok.*;
import org.oome.entity.article.Article;
import org.oome.entity.member.Member;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ArticleComment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn
    private Member creater;

    @ManyToOne
    @JoinColumn
    private Article article;
}
