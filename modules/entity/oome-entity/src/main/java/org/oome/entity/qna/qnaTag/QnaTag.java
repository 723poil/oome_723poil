package org.oome.entity.qna.qnaTag;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.qna.Qna;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QnaTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Qna qna;

}
