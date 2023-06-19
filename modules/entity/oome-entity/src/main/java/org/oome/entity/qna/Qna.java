package org.oome.entity.qna;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.member.Member;
import org.oome.entity.qna.qnaLike.QnaLike;
import org.oome.entity.qna.qnaTag.QnaTag;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Qna extends BaseTimeEntity {

    // 불변요소의 경우 Setter의 AccessLevel로 메소드 접근제어한다.
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaType qnaType;

    @Column
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    private Qna parentQna;

    // fetchType == EAGER일 경우 JPA default이기때문에 따로 명시하지 않는다.
    @OneToMany(mappedBy = "parentQna", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qna> answerList;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QnaLike> likeList;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QnaTag> tagList;

    @ManyToOne
    private Member creater;

}
