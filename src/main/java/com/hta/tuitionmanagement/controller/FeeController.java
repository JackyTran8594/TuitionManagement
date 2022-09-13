package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.service.FeeService;
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
@RequestMapping("/api/fee")
public class FeeController extends BaseController {
    @Autowired
    private FeeService feeService;

    @GetMapping("")
    public Page<FeeDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<FeeDTO> listData = feeService.search(params);
        Long totalElement = feeService.count(params);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("")
    public ResponseEntity<MessageResponse<FeeDTO>> addFee(@RequestBody FeeDTO item) {
        FeeDTO dto = feeService.save(item);
        MessageResponse<FeeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<FeeDTO>>  updateFee(@RequestBody FeeDTO item,@PathVariable final Long id) {
        item.setId(id);
        FeeDTO dto = feeService.save(item);
        MessageResponse<FeeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/getAll")
    public ResponseEntity<MessageResponse<FeeDTO>>  getAll() {
        List<FeeDTO> dto = feeService.findAll();
        MessageResponse<FeeDTO> message = new MessageResponse<>();
        message.setListData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<FeeDTO>>  getById(@PathVariable(value="id") Long id) {
        FeeDTO dto = feeService.findById(id);
        MessageResponse<FeeDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<FeeDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<FeeDTO> message = new MessageResponse<>();
        try {
            Boolean del = feeService.deleteById(id);
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
            Boolean del = feeService.deleteAll(listId);
            message.success();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message.error(e.getMessage());
            return ResponseEntity.internalServerError().body(message);
        }
    }
}
