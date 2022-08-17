package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;

import java.util.List;
import java.util.Map;

public interface TrainClassService {
    TrainClassDTO findById(Long id);

    TrainClassDTO save(TrainClassDTO item);

    List<TrainClassDTO> findAll();

    List<TrainClassDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    TrainClassDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);
}
