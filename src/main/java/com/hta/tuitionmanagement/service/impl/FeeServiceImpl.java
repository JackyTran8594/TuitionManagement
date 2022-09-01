package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.model.Fee;
import com.hta.tuitionmanagement.repo.FeeRepository;
import com.hta.tuitionmanagement.service.FeeService;
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
public class FeeServiceImpl implements FeeService {
    private static final BaseMapper<Fee, FeeDTO> mapper = new BaseMapper<>(Fee.class, FeeDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(FeeServiceImpl.class);

    @Autowired
    private FeeRepository feeRepository;

    @Override
    public FeeDTO findById(Long id) {
        Optional<Fee> entity = feeRepository.findById(id);
        if (entity.isPresent()) {
            FeeDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public FeeDTO save(FeeDTO dto) {
        Fee entity;
        if (!DataUtils.isNullOrEmpty(dto.getId())) {
//            dto.setLastModifiedDate(LocalDateTime.now());
//            entity = mapper.toPersistenceBean(dto);
            entity = feeRepository.getById(dto.getId());
            entity.setLastModifiedDate(LocalDateTime.now());
            entity.updateInfo(dto);
        } else {
            entity = mapper.toPersistenceBean(dto);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean((feeRepository.save(entity)));
    }

    @Override
    public List<FeeDTO> findAll() {
        List<Fee> entities = feeRepository.findAll();
        List<FeeDTO> listDTO = mapper.toDtoBean(entities);
        return listDTO;
    }

    @Override
    public List<FeeDTO> search(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        List<Fee> listEntity = feeRepository.search(mapParam, Fee.class);
        List<FeeDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = feeRepository.count(mapParam);
        return count;
    }

    @Override
    public FeeDTO findByCode(String code) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            feeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            feeRepository.deleteAll(listId);
            return true;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
