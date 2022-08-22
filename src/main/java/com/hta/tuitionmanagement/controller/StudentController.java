package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.RoleDTO;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.service.StudentService;
import com.hta.tuitionmanagement.service.TrainClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService service;

    @GetMapping("")
    public Page<StudentDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if (pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<StudentDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData, page, totalElement);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse<StudentDTO>> addStudent(@RequestBody StudentDTO item) {
        StudentDTO dto = service.save(item);
        MessageResponse<StudentDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<StudentDTO>> updateRole(@RequestBody StudentDTO item, @PathVariable final Long id) {
        item.setId(id);
        StudentDTO dto = service.save(item);
        MessageResponse<StudentDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<StudentDTO>> getById(@PathVariable(value = "id") Long id) {
        StudentDTO dto = service.findById(id);
        MessageResponse<StudentDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<StudentDTO>> deleteById(@PathVariable(value = "id") Long id) {
        MessageResponse<StudentDTO> message = new MessageResponse<>();
        try {
            Boolean del = service.deleteById(id);
            message.success();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message.error(e.getMessage());
            return ResponseEntity.internalServerError().body(message);
        }

    }

    @PostMapping("/deleteAll")
    public ResponseEntity<MessageResponse<StudentDTO>> deleteById(@RequestBody List<Long> listId) {
        MessageResponse<StudentDTO> message = new MessageResponse<>();
        try {
            Boolean del = service.deleteAll(listId);
            message.success();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message.error(e.getMessage());
            return ResponseEntity.internalServerError().body(message);
        }
    }
}
