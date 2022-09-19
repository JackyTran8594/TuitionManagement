package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.ReportDTO;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<ReportDTO> search(Map<String, Object> params);

    Long count(Map<String, Object> params);

    byte[] export(Map<String, Object> params, String type) throws Exception;

}
