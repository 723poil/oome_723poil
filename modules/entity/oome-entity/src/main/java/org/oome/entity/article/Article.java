package org.oome.entity.article;

import lombok.Getter;
import lombok.Setter;
import org.oome.entity.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Article extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;
}
