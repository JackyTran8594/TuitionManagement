package com.hta.tuitionmanagement.repo;

import java.util.List;
import java.util.Map;

import com.hta.tuitionmanagement.dto.response.ReportDTO;

public interface StudentRepositoryCustom extends BaseRepositoryCustom{

    List<ReportDTO> reportSearch(Map<String, Object> searchParam);

    Long countReport(Map<String, Object> searchParam);

}
