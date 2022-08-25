package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentDTO findById(Long id);

    StudentDTO save(StudentDTO item);
    List<StudentDTO> search(Map<String, Object> mapParam);
    Long count(Map<String, Object> mapParam);
    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

    Boolean saveListFromXmlFile(List<Student> student);
}
