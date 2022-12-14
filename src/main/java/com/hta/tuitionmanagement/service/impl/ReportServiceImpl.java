package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.constants.Constant;
import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.ReportDTO;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.mapper.BaseMapper;
import com.hta.tuitionmanagement.mapper.CustomMapper;
import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.model.Tuition;
import com.hta.tuitionmanagement.repo.StudentRepository;
import com.hta.tuitionmanagement.service.ReportService;
import com.hta.tuitionmanagement.service.StudentService;
import com.hta.tuitionmanagement.utils.DataUtils;
import com.hta.tuitionmanagement.utils.excel.ExcelWriter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private StudentRepository repository;

    @Override
    public List<ReportDTO> search(Map<String, Object> mapParam) {
        List<ReportDTO> students = repository.reportSearch(mapParam);
        return students;
    }

    @Override
    public Long count(Map<String, Object> mapParam) {
        Long count = repository.countReport(mapParam);
        return count;
    }

    @Override
    public byte[] export(Map<String, Object> params, String type) throws Exception {
        byte[] bytes = null;
        List<ReportDTO> data = search(params);
        List<String> header = Arrays.asList("id", "Mã học viên", "Mã khóa học", "CMT/CCCD", "Tên đệm", "Họ", "Tên",
                "Họ và tên", "Giáo viên hướng dẫn", "Header", "Học phí đã nộp", "Học phí");
        List<String> fields = DataUtils.getNameOfProperty(ReportDTO.class);
        if (type.equals(Constant.EXPORT_TYPE_EXCEL)) {
            bytes = ExcelWriter.writeToExcel(header, fields, data);
        } else {

        }
        return bytes;
    }

}
