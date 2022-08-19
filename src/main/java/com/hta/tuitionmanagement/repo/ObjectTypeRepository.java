package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjectTypeRepository extends JpaRepository<ObjectType, Long>, ObjectTypeRepositoryCustom {

@Query(value="DELETE FROM object_type WHERE id IN :listId", nativeQuery=true)
    Integer deleteAll(@Param("listId") List<Long> listId);

}
