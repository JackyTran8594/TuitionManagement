package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.mapper.CustomMapper;
import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.model.Tuition;
import com.hta.tuitionmanagement.repo.StudentRepository;
import com.hta.tuitionmanagement.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    private static final BaseMapper<Student, StudentDTO> mapper = new BaseMapper<>(Student.class, StudentDTO.class);
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository repository;
    @Override
    public StudentDTO findById(Long id) {
        Optional<Student> entity = repository.findById(id);
        if (entity.isPresent()) {
            StudentDTO dto = mapper.toDtoBean(entity.get());
            return dto;
        }
        return null;
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student entity;
        if (!DataUtils.isNullOrEmpty(dto.getId())) {
            dto.setLastModifiedDate(LocalDateTime.now());
            entity = mapper.toPersistenceBean(dto);
            entity.setLastModifiedDate(LocalDateTime.now());
        } else {
            entity = mapper.toPersistenceBean(dto);
            entity.setStatus(ACTIVE);
        }
        return mapper.toDtoBean((repository.save(entity)));
    }

    @Override
    public List<StudentDTO> search(Map<String, Object> mapParam) {
        List<Student> students = repository.search(mapParam, Student.class);
        List<StudentDTO> listDto = mapper.toDtoBean(students);
        return listDto;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.count(mapParam);
        return count;
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
            Integer delete = repository.deleteAll(listId);
            if(delete > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean saveListFromXmlFile(List<Student> student) {
        try {
            repository.saveAll(student);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
}
