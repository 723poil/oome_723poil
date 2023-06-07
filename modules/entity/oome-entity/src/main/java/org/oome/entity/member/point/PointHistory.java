package org.oome.entity.member.point;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.member.Member;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PointHistory extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @ManyToOne
    @JoinColumn
    private Member creater;

    @ManyToOne
    @JoinColumn
    private Member member;

    @Column
    private String remark;
}
