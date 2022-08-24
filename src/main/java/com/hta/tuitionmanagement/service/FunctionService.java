package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.AuthorizationDTO;
import com.hta.tuitionmanagement.dto.response.FunctionDTO;

import java.util.List;
import java.util.Map;

public interface FunctionService {
    FunctionDTO findById(Long id);

    FunctionDTO save(FunctionDTO item);

    List<FunctionDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

    List<AuthorizationDTO> getAuthorizationList();


}