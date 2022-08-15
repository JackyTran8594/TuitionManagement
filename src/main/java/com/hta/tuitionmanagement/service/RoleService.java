package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.response.RoleDTO;

import java.util.List;
import java.util.Map;

public interface RoleService {
    RoleDTO findById(Long id);

    RoleDTO save(RoleDTO item);

    List<RoleDTO> findAll();

    List<RoleDTO> search(Map<String, Object> mapParam);

    Long count(Map<String, Object> mapParam);

    RoleDTO findByCode(String code);

    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);

    List<RoleDTO> findRoleByUserId(Long id);

}
