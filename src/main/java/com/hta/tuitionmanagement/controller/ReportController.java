package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.response.ReportDTO;
import com.hta.tuitionmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService service;

    @GetMapping("")
    public Page<ReportDTO> paging(@RequestParam int pageSize, @RequestParam int pageNumber,@RequestParam Map<String, Object> mapParam) {
        if (pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageSize, pageNumber);
        List<ReportDTO> data = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(data, page, totalElement);
    }
}
