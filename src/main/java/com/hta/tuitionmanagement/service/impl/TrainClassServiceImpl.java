package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.model.Fee;
import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.repo.FeeRepository;
import com.hta.tuitionmanagement.repo.TrainClassRepository;
import com.hta.tuitionmanagement.service.TrainClassService;
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
public class TrainClassServiceImpl implements TrainClassService {

    private static final BaseMapper<TrainClass, TrainClassDTO> mapper = new BaseMapper<>(TrainClass.class, TrainClassDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(TrainClassServiceImpl.class);

    @Autowired
    private TrainClassRepository repository;

    @Override
    public TrainClassDTO findById(Long id) {
        Optional<TrainClass> entity = repository.findById(id);
        if (entity.isPresent()) {
            TrainClassDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public TrainClassDTO save(TrainClassDTO dto) {
        TrainClass entity;
        if (!DataUtils.isNullOrEmpty(dto.getId())) {
//            entity = repository.getById(dto.getId());
//            entity.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(dto);
            entity.updateInfo(dto);

        } else {
            entity = mapper.toPersistenceBean(dto);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean((repository.save(entity)));
    }

    @Override
    public List<TrainClassDTO> findAll() {
        List<TrainClass> entities = repository.findAll();
        List<TrainClassDTO> listDTO = mapper.toDtoBean(entities);
        return listDTO;
    }

    @Override
    public List<TrainClassDTO> search(Map<String, Object> mapParam) {
        Map<String, Object> parameters = new HashMap<>();
        List<TrainClass> listEntity = repository.search(mapParam, TrainClass.class);
        List<TrainClassDTO> listData = mapper.toDtoBean(listEntity);
        return listData;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
    }

    @Override
    public TrainClassDTO findByCode(String code) {
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
