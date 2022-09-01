package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>, FeeRepositoryCustom {

    @Transactional
    @Modifying
    @Query(value="DELETE FROM fee WHERE id IN :listId", nativeQuery=true)
    void deleteAll(@Param("listId") List<Long> listId);
}
