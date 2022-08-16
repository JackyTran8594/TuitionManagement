package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Role;
import com.hta.tuitionmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom{
    Optional<Student> findById(Long id);

    void deleteById(Long id);

    @Query(value="DELETE FROM student as s WHERE 1=1 AND s.id IN :listId", nativeQuery=true)
    Integer deleteAll(List<Long> listId);
}
