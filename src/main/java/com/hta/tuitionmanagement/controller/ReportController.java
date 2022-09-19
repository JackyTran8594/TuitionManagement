package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.response.ReportDTO;
import com.hta.tuitionmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService service;

    @GetMapping("")
    public Page<ReportDTO> paging(@RequestParam int pageSize, @RequestParam int pageNumber,
            @RequestParam Map<String, Object> mapParam) {
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

    @PostMapping("/export")
    public ResponseEntity<InputStreamResource> export(@RequestParam Map<String, Object> mapParam,
            @RequestParam String type) throws FileNotFoundException, IOException, Exception {
        byte[] byteArray = service.export(mapParam, type);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "Báo cáo" + "_" + Timestamp.valueOf(LocalDateTime.now()).getTime());
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArray));
        return ResponseEntity.ok()
                .headers(respHeaders)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(byteArray.length)
                .body(inputStreamResource);
    }

}
