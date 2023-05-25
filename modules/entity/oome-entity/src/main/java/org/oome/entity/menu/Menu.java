package org.oome.entity.menu;

import lombok.Getter;
import lombok.Setter;
import org.oome.entity.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Menu extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    private String menuCode;

    private String menuLink;

    @ManyToOne
    private Menu parent;
}
