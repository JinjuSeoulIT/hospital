package com.module.memerlogin.entity;


import com.module.authentication.entity.AuthenticationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ✅ MEMBER_LOGIN(계정/로그인)
 *
 * - PK: USER_ID (내부 식별자)
 * - LOGIN_ID: 사용자 입력 아이디(UNIQUE)
 * - ROLE_CODE: DOCTOR/NURSE/ADMIN/COMMON
 */
@Entity
@Table(name = "MEMBER_LOGIN", schema = "MEMBER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_USER_ID")
    @SequenceGenerator(
            name = "SEQ_MEMBER_USER_ID",
            sequenceName = "MEMBER.SEQ_MEMBER_USER_ID",
            allocationSize = 1

    )
    @Column(name = "USER_ID", nullable = false)
    private Long   userId;

    @Column(name = "LOGIN_ID", nullable = false, unique = true)
    private String loginId;

    @Column(name = "LOGIN_TYPE", nullable = false)
    private String loginType = "LOCAL";

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "ROLE_CODE", nullable = false)
    private String roleCode;

    /**
     * ✅ 1:1 상세정보(MEMBER_PROFILE)
     * - FK 주인은 AuthenticationEntity(user) 쪽
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private AuthenticationEntity profile;
}
