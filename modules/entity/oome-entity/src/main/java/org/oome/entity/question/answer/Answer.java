package org.oome.entity.question.answer;

import lombok.*;
import org.oome.entity.question.Question;
import org.oome.entity.member.Member;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Answer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn
    private Member creater;

    @ManyToOne
    @JoinColumn
    private Question question;
}