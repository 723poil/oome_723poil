package org.oome.entity.question;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.member.Member;
import org.oome.entity.question.answer.Answer;
import org.oome.entity.question.questionLike.QuestionLike;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn
    private Member creater;

    @OneToMany(mappedBy = "isLike", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QuestionLike> questionLikes = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Answer> answers = new ArrayList<>();
}
