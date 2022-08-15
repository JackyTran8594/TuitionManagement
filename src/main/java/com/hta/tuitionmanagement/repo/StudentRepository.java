package com.hta.tuitionmanagement.repo;

import com.hta.tuitionmanagement.model.Role;
import com.hta.tuitionmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom{
}
