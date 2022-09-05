package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.model.Tuition;
import com.hta.tuitionmanagement.service.TuitionService;
import com.hta.tuitionmanagement.utils.BuildParameterForSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tuition")
public class TuitionController extends BaseController{
    @Autowired
    private TuitionService service;
    private BuildParameterForSearch<Tuition> buildParams;

    @GetMapping("")
    public Page<TuitionDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        buildParams = new BuildParameterForSearch<Tuition>();
        parameters = buildParams.buildParams(params, Tuition.class, "params");
        parameters.put("pageSize", pageSize);
        parameters.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<TuitionDTO> listData = service.search(parameters);
        Long totalElement = service.count(parameters);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("/addTuition")
    public ResponseEntity<MessageResponse<TuitionDTO>> addTuitionRequest(@RequestBody TuitionRequest item) {
        TuitionDTO dto = service.save(item);
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("")
    public ResponseEntity<MessageResponse<TuitionDTO>> addTuition(@RequestBody TuitionDTO item) {
        TuitionDTO dto = service.saveTuition(item);
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<TuitionDTO>> updateTrainClass(@RequestBody TuitionDTO item,@PathVariable final Long id) {
        item.setId(id);
        TuitionDTO dto = service.saveTuition(item);
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<TuitionDTO>> getById(@PathVariable(value="id") Long id) {
        TuitionDTO dto = service.findById(id);
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/getAllWithId")
    public ResponseEntity<MessageResponse<TuitionDTO>> getAllWithId(@RequestParam int id) {
        List<TuitionDTO> dto = service.getAllWithId(Long.valueOf(id));
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setListData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<TuitionDTO>> deleteById(@PathVariable(value="id") Long id) {
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
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
