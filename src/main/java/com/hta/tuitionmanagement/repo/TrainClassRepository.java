package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.model.TrainClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TrainClassRepository extends JpaRepository<TrainClass, Long>, TrainClassRepositoryCustom {


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM train_class WHERE 1=1 AND id IN :listId", nativeQuery = true)
    int deleteAll(List<Long> listId);
}
