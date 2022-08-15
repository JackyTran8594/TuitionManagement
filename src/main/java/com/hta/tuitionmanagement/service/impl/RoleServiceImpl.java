package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.response.RoleDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.model.Role;
import com.hta.tuitionmanagement.repo.RoleRepository;
import com.hta.tuitionmanagement.service.RoleService;
import com.hta.tuitionmanagement.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hta.tuitionmanagement.constants.Constant.ACTIVE;
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static final BaseMapper<Role, RoleDTO> mapper = new BaseMapper<>(Role.class, RoleDTO.class);
    @Autowired
    private RoleRepository repository;


    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent()) {
            RoleDTO dto = mapper.toDtoBean(role.get());
            return dto;
        }
        return null;
    }

    @Override
    public RoleDTO save(RoleDTO item) {
        Role entity;
        if (!DataUtils.isNullOrEmpty(item.getId())) {
            item.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(item);
        } else {
            entity = mapper.toPersistenceBean(item);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean(repository.save(entity));

    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> listEntity = repository.findAll();
        List<RoleDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public List<RoleDTO> search(Map<String, Object> mapParam) {
        List<Role> listEntity = repository.search(mapParam, Role.class);
        List<RoleDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public RoleDTO findByCode(String code) {
        Role entity = repository.findByCode(code);
        if (!DataUtils.isNullOrEmpty(entity)) {
            RoleDTO dto = mapper.toDtoBean(entity);
            return dto;
        }
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            Integer delete = repository.deleteAll(listId);
            if(delete > 0) {
                return true;
            }
            return false;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<RoleDTO> findRoleByUserId(Long id) {
        try {
            List<Role> listEntity = repository.findRoleByUserId(id);
            List<RoleDTO> listData = mapper.toDtoBean(listEntity);
            return listData;
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

}
