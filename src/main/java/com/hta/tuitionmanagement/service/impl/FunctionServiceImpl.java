package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.response.FunctionDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.model.Function;
import com.hta.tuitionmanagement.repo.FunctionRepository;
import com.hta.tuitionmanagement.service.FunctionService;
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
public class FunctionServiceImpl implements FunctionService {

    private static final Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);
    private static final BaseMapper<Function, FunctionDTO> mapper = new BaseMapper<>(Function.class, FunctionDTO.class);
    @Autowired
    private FunctionRepository repository;


    @Override
    public FunctionDTO findById(Long id) {
        Optional<Function> func = repository.findById(id);
        if (func.isPresent()) {
            FunctionDTO dto = mapper.toDtoBean(func.get());
            return dto;
        }
        return null;
    }

    @Override
    public FunctionDTO save(FunctionDTO item) {
        Function entity;
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
    public List<FunctionDTO> search(Map<String, Object> mapParam) {
        List<Function> listEntity = repository.search(mapParam, Function.class);
        List<FunctionDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        Long count = repository.count(mapParam);
        return count;
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


}
