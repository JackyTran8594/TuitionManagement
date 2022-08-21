package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.service.StudentService;
import com.hta.tuitionmanagement.service.TrainClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseController{

    @Autowired
    private StudentService service;

    @PostMapping("")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO item) {
        StudentDTO dto = service.save(item);
        return ResponseEntity.ok(dto);
    }
}
