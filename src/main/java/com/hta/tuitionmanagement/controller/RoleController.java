package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.response.RoleDTO;
import com.hta.tuitionmanagement.service.RoleService;
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
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService service;

    @GetMapping("")
    public Page<RoleDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<RoleDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse<RoleDTO>> addRole(@RequestBody RoleDTO item) {
        RoleDTO dto = service.save(item);
        MessageResponse<RoleDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<RoleDTO>> updateRole(@RequestBody RoleDTO item,@PathVariable final Long id ) {
        item.setId(id);
        RoleDTO dto = service.save(item);
        MessageResponse<RoleDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<RoleDTO>> getById(@PathVariable(value="id") Long id) {
        RoleDTO dto = service.findById(id);
        MessageResponse<RoleDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<RoleDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<RoleDTO> message = new MessageResponse<>();
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
    public ResponseEntity<MessageResponse<String>> deleteById(@RequestBody  List<Long> listId) {
        MessageResponse<String> message = new MessageResponse<>();
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
