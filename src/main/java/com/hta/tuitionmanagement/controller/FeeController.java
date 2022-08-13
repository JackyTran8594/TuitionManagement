package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
