package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.model.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TuitionRepository extends JpaRepository<Tuition, Long>, TuitionRepositoryCustom {


    @Query(value = "DELETE FROM tuition as s WHERE 1=1 AND s.id IN :listId", nativeQuery = true)
    Integer deleteAll(List<Long> listId);
}