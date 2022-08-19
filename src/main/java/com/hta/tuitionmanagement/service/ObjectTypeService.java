package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.ObjectTypeDTO;

import java.util.List;
import java.util.Map;

public interface ObjectTypeService {
    ObjectTypeDTO findById(Long id);

    ObjectTypeDTO save(ObjectTypeDTO item);

    List<ObjectTypeDTO> findAll();

    List<ObjectTypeDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    ObjectTypeDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);
}
