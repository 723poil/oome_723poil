package org.oome.entity.member;

import lombok.Getter;
import lombok.Setter;
import org.oome.entity.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
