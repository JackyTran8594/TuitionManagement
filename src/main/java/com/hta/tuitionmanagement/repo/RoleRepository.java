package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

    Optional<Role> findById(Long id);

    @Query(value = "SELECT f.id, f.action_code FROM role_function AS rf, function AS f WHERE rf.role_id = f.id AND rf.role_id IN :listId", nativeQuery = true)
    List<String> getRoleWithList(@Param("listId") List<Long> listId);

//    void deleteById(Long listId);

    @Query(value="DELETE FROM role as r WHERE 1=1 AND r.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);
    @Query(value = "SELECT * FROM role AS r  WHERE r.code = :code", nativeQuery = true)
    Role findByCode(@Param("code") String code);

    @Query(value = "SELECT r.* FROM role AS r LEFT JOIN user_entity AS u ON r.id = u.role_id WHERE u.id = :userId", nativeQuery = true)
    List<Role> findRoleByUserId(@Param("userId") Long userId);







}
