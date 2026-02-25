package com.module.memerlogin.mapper;

import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import com.module.memerlogin.dto.login.LoginRowDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * ✅ loginId 존재 체크: 존재하면 1 이상, 없으면 0
     * (기존 userId 기반은 스키마 변경으로 폐기)
     */
    int existsByLoginId(@Param("loginId") String loginId);




    /** loginId 중복 체크: 존재하면 1 이상, 없으면 0 */
    /**상세 회원가입 후 조회용 */
    // [수정] loginId 존재 체크는 existsByLoginId로 통일 (countByLoginId 제거)
    // int countByLoginId(String loginId);




    /**
     * ✅ loginId로 로그인 검증용 row 조회 (password_hash 포함)
     */
    LoginRowDTO selectLoginRowByLoginId(@Param("loginId") String loginId);
//
//    //로그인 조회용
//    UserResponseDTO selectUserById( Long userId);



    //🧠  ME용 조회맵퍼
    UserResponseDTO findMeByUserId(@Param("userId") Long userId);



}