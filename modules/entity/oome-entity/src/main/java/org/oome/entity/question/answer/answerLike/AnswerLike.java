package org.oome.entity.question.answer.answerLike;

import lombok.*;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.question.Question;
import org.oome.entity.question.answer.Answer;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnswerLike {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YN isLike;

    @ManyToOne
    @JoinColumn
    private Question question;

    @ManyToOne
    @JoinColumn
    private Answer answer;

    @ManyToOne
    @JoinColumn
    private Member creater;
}
