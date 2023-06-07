package org.oome.entity.member;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.common.enums.YN;
import org.oome.entity.enums.MemberRole;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<MemberRole> roles;

    @Enumerated(EnumType.STRING)
    @Column
    private YN isBlock;

    @Enumerated(EnumType.STRING)
    @Column
    private YN isLock;

    @Enumerated(EnumType.STRING)
    @Column
    private YN isUsernameValid;

    @Column
    private Integer failCount;
}
