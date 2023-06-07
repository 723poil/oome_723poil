package org.oome.entity.menu;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;

import javax.persistence.*;

/**
 * 메뉴 Entity
 * TODO : Redis 도입되면 RedisHash로 CrudRepository에서 관리 예정
 * @author hjhearts
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Menu extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false, unique = true)
    private String menuCode;

    @Column(nullable = true)
    private String menuLink;

    @ManyToOne
    private Menu parent;
}
