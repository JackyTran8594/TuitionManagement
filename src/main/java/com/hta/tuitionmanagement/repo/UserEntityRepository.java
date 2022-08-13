package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, UserEntityRepositoryCustom {

    UserEntity findByUsername(String username);
}
