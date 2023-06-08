package org.oome.entity.qna;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.member.Member;
import org.oome.entity.qna.qnaLike.QnaLike;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Qna extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaType qnaType;

    @Column
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    private Qna parentQna;

    @OneToMany(mappedBy = "parentQna", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qna> answerList;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QnaLike> likeList = new ArrayList<>();

    @ManyToOne
    private Member creater;

}
