package org.oome.entity.question.questionLike;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.member.Member;
import org.oome.entity.question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QuestionLike extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private boolean like;

    @ManyToOne
    @JoinColumn
    private Question question;

    @ManyToOne
    @JoinColumn
    private Member creater;
}
