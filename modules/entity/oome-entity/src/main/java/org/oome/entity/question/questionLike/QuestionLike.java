package org.oome.entity.question.questionLike;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.common.enums.YN;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YN isLike;

    @ManyToOne
    @JoinColumn
    private Question question;

    @ManyToOne
    @JoinColumn
    private Member creater;
}
