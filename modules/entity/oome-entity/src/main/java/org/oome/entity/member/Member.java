package org.oome.entity.member;

import lombok.Getter;
import lombok.Setter;
import org.oome.entity.common.BaseTimeEntity;
import org.oome.entity.enums.MemberRole;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Column(nullable = false)
    private Set<MemberRole> memberRole;
}
