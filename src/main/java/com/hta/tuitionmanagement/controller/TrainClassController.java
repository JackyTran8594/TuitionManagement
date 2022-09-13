package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
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
@RequestMapping("/api/trainClass")
public class TrainClassController extends BaseController{
    @Autowired
    private TrainClassService service;

    @GetMapping("")
    public Page<TrainClassDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<TrainClassDTO> listData = service.search(params);
        Long totalElement = service.count(params);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("")
    public ResponseEntity<MessageResponse<TrainClassDTO>> addTrainClass(@RequestBody TrainClassDTO item) {
        TrainClassDTO dto = service.save(item);
        MessageResponse<TrainClassDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<TrainClassDTO>> updateTrainClass(@RequestBody TrainClassDTO item,@PathVariable final Long id) {
        item.setId(id);
        TrainClassDTO dto = service.save(item);
        MessageResponse<TrainClassDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<TrainClassDTO>> getById(@PathVariable(value="id") Long id) {
        TrainClassDTO dto = service.findById(id);
        MessageResponse<TrainClassDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/getAll")
    public ResponseEntity<MessageResponse<TrainClassDTO>>  getAll() {
        List<TrainClassDTO> dto = service.findAll();
        MessageResponse<TrainClassDTO> message = new MessageResponse<>();
        message.setListData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<TrainClassDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<TrainClassDTO> message = new MessageResponse<>();
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
    public ResponseEntity<MessageResponse<String>> deleteByAll(@RequestBody  List<Long> listId) {
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

