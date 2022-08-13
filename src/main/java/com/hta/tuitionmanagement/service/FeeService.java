package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.FeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface FeeService {
    FeeDTO findById(Long id);

    FeeDTO save(FeeDTO item);

    List<FeeDTO> findAll();

    List<FeeDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    FeeDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

}
