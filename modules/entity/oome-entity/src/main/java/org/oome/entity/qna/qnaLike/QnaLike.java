package org.oome.entity.qna.qnaLike;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.qna.Qna;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QnaLike extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private YN isLike;

    @ManyToOne
    private Qna qna;

    @ManyToOne
    private Member creater;
}
