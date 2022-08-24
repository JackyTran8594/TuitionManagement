package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.response.AuthorizationDTO;
import com.hta.tuitionmanagement.dto.response.FunctionDTO;
import com.hta.tuitionmanagement.service.FunctionService;
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
@RequestMapping("/api/function")
public class FunctionController extends BaseController {

    @Autowired
    private FunctionService service;

    @GetMapping("")
    public Page<FunctionDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<FunctionDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse<FunctionDTO>> add(@RequestBody FunctionDTO item) {
        FunctionDTO dto = service.save(item);
        MessageResponse<FunctionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<FunctionDTO>> update(@RequestBody FunctionDTO item,@PathVariable final Long id) {
        item.setId(id);
        FunctionDTO dto = service.save(item);
        MessageResponse<FunctionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<FunctionDTO>> getById(@PathVariable(value="id") Long id) {
        FunctionDTO dto = service.findById(id);
        MessageResponse<FunctionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<FunctionDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<FunctionDTO> message = new MessageResponse<>();
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

    @GetMapping("/authorization")
    public ResponseEntity<MessageResponse<AuthorizationDTO>> getAuthorizationList(){
        List<AuthorizationDTO> list = service.getAuthorizationList();
        MessageResponse<AuthorizationDTO> message  = new MessageResponse<>();
        message.setListData(list);
        message.success();
        return ResponseEntity.ok().body(message);
    }

}
