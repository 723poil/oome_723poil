package org.oome.entity.member;

import lombok.*;
import org.oome.entity.common.audit.BaseTimeEntity;
import org.oome.entity.common.enums.Gender;
import org.oome.entity.common.enums.YN;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.point.PointHistory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {

    /**
     * 계정 관련 정보
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 아이디 (이메일)

    @Column(nullable = false)
    private String password; // 패스워드

    @Column(nullable = false, unique = true)
    private String nickname; // 닉네임

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Set<MemberRole> roles; // 계정 권한목록

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YN isBlock; // 계정 차단여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YN isLock; // 계정 잠김여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YN isUsernameValid; // 이메일 인증여부

    @Column(nullable = false)
    private Integer failCount; // 로그인 실패횟수

    /**
     * 서비스 관련 정보
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointHistory> pointList = new ArrayList<>(); // 포인트

    /**
     * 개인정보 관련 정보
     */
    @Column
    private String phoneNumber; // 휴대폰 번호

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender; // 성별

    @Column
    private String githubUrl; // 깃허브 url

    @Column
    private String subEmail; // 추가이메일

    @Column
    private LocalDateTime birth; // 생일
}
