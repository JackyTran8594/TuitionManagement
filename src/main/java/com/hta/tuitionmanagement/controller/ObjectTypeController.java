package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.response.ObjectTypeDTO;
import com.hta.tuitionmanagement.service.ObjectTypeService;
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
@RequestMapping("/api/objectType")
public class ObjectTypeController extends BaseController{
    @Autowired
    private ObjectTypeService service;

    @GetMapping("")
    public Page<ObjectTypeDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<ObjectTypeDTO> listData = service.search(params);
        Long totalElement = service.count(params);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("")
    public ResponseEntity<MessageResponse<ObjectTypeDTO>> addTrainClass(@RequestBody ObjectTypeDTO item) {
        ObjectTypeDTO dto = service.save(item);
        MessageResponse<ObjectTypeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<ObjectTypeDTO>> updateTrainClass(@RequestBody ObjectTypeDTO item,@PathVariable final Long id) {
        item.setId(id);
        ObjectTypeDTO dto = service.save(item);
        MessageResponse<ObjectTypeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<ObjectTypeDTO>> getById(@PathVariable(value="id") Long id) {
        ObjectTypeDTO dto = service.findById(id);
        MessageResponse<ObjectTypeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<ObjectTypeDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<ObjectTypeDTO> message = new MessageResponse<>();
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
