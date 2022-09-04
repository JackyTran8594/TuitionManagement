package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.MessageResponse;
import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.service.TuitionService;
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
@RequestMapping("/api/tuition")
public class TuitionController extends BaseController{
    @Autowired
    private TuitionService service;

    @GetMapping("")
    public Page<TuitionDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<TuitionDTO> listData = service.search(params);
        Long totalElement = service.count(params);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("")
    public ResponseEntity<MessageResponse<TuitionDTO>> addTuition(@RequestBody TuitionDTO item) {
        TuitionDTO dto = service.save(item);
        MessageResponse<TuitionDTO> message = new MessageResponse<>();
        message.setData(dto);
        message.success();
        return ResponseEntity.ok().body(message);
    }



}
