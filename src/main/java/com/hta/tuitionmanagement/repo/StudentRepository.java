package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Role;
import com.hta.tuitionmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {
    Optional<Student> findById(Long id);

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM student  WHERE 1=1 AND id IN :listId", nativeQuery = true)
    void deleteAll(List<Long> listId);
}
