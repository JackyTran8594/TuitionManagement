package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO findById(Long id);

    StudentDTO save(StudentDTO item);

//    List<StudentDTO> findAll();

//    List<StudentDTO> search(Map<String, Object> mapParam);
//
//    Long count(Map<String, Object> mapParam);

//    StudentDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);
}
