package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.service.TuitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tuition")
public class TuitionController extends BaseController{
    @Autowired
    private TuitionService service;

    @PostMapping("")
    public TuitionDTO addTuition(@RequestBody TuitionRequest item) {
        return service.save(item);
    }

}
