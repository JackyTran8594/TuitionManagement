package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.response.ObjectTypeDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.model.ObjectType;
import com.hta.tuitionmanagement.repo.ObjectTypeRepository;
import com.hta.tuitionmanagement.service.ObjectTypeService;
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
public class ObjectTypeServiceImpl implements ObjectTypeService {

    private static final BaseMapper<ObjectType, ObjectTypeDTO> mapper = new BaseMapper<>(ObjectType.class, ObjectTypeDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(ObjectTypeServiceImpl.class);

    @Autowired
    private ObjectTypeRepository repository;

    @Override
    public ObjectTypeDTO findById(Long id) {
        Optional<ObjectType> entity = repository.findById(id);
        if (entity.isPresent()) {
            ObjectTypeDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public ObjectTypeDTO save(ObjectTypeDTO dto) {
        ObjectType entity;
        if (!DataUtils.isNullOrEmpty(dto.getId())) {
//            dto.setLastModifiedDate(LocalDateTime.now());
//            entity = mapper.toPersistenceBean(dto);
            entity = repository.getById(dto.getId());
            entity.setLastModifiedDate(LocalDateTime.now());
            entity.updateInfo(dto);
        } else {
            entity = mapper.toPersistenceBean(dto);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean((repository.save(entity)));
    }

    @Override
    public List<ObjectTypeDTO> findAll() {
        List<ObjectType> entities = repository.findAll();
        List<ObjectTypeDTO> listDTO = mapper.toDtoBean(entities);
        return listDTO;
    }

    @Override
    public List<ObjectTypeDTO> search(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        List<ObjectType> listEntity = repository.search(mapParam, ObjectType.class);
        List<ObjectTypeDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public ObjectTypeDTO findByCode(String code) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            repository.deleteAll(listId);
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
