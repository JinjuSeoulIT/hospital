package com.module.memerlogin.repository;


//JPA Repository (엔티티 기반)
import com.module.memerlogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//DB랑 직접 대화하는 담당(DAO)
// [수정] PK가 loginId(String)로 변경되어 Repository ID 타입을 String으로 변경
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLoginId(String loginId);
//    boolean existsByLoginId(String loginId);
//
//    UserEntity findByLoginId(String loginId);

    //    save(entity) : INSERT/UPDATE
    //
    //    findById(id) : PK로 조회
    //
    //    findAll() : 전체 조회
    //
    //    deleteById(id) : 삭제
    //
    //    count() : 전체 개수
    //상속


}