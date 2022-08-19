package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.mapper.CustomMapper;
import com.hta.tuitionmanagement.model.Fee;
import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.model.Tuition;
import com.hta.tuitionmanagement.repo.*;
import com.hta.tuitionmanagement.service.TuitionService;
import com.hta.tuitionmanagement.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class TuitionServiceImpl implements TuitionService {

    private static final BaseMapper<Tuition, TuitionDTO> mapper = new BaseMapper<>(Tuition.class, TuitionDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(TuitionServiceImpl.class);

    @Autowired
    private TuitionRepository tuitionRepo;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private TrainClassRepository trainClassRepo;
    @Autowired
    private ObjectTypeRepository objectTypeRepo;
    @Autowired
    private FeeRepository feeRepo;

    @Override
    public TuitionDTO findById(Long id) {
        Optional<Tuition> entity = tuitionRepo.findById(id);
        if (entity.isPresent()) {
            TuitionDTO dto = CustomMapper.entityToDtoTuition(entity.get());
            return dto;
        }
        return null;
    }
    @Override
    public TuitionDTO save(TuitionRequest item) {
        Tuition entity ;
        if(!DataUtils.isNullOrEmpty(item.getId()))
            entity = tuitionRepo.findById(item.getId()).get();
        else
            entity = new Tuition();
        Student student = studentRepo.findById(item.getStudentId()).get();
        student.setTrainClass(trainClassRepo.findById(item.getTrainClassId()).get());
        student.setObjectType(objectTypeRepo.findById(item.getObjectTypeId()).get());

        if(!DataUtils.isNullOrEmpty(item.getListFeeId())) {
            Set<Fee> list = new HashSet<>();
            item.getListFeeId().forEach(id -> {
                Fee fee = feeRepo.findById(id).get();
                list.add(fee);
//                System.out.println(id);
            });
            student.setFeeList(list);
        }
        item.setCreatedDate(LocalDateTime.now());

        entity.setTimeStamp(item.getCreatedDate());
        entity.setMoney(item.getMoney());
        entity.setStudent(student);

        return CustomMapper.entityToDtoTuition(tuitionRepo.save(entity));
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            tuitionRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }

    }

    @Override
    public Boolean deleteAll(List<Long> listId) {
        try {
            Integer delete = tuitionRepo.deleteAll(listId);
            if(delete > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }

    }
}
