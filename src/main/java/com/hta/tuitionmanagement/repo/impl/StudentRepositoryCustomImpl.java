package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.repo.StudentRepositoryCustom;

import java.util.List;
import java.util.Map;

public class StudentRepositoryCustomImpl  extends BaseCustomRepository<Student> implements StudentRepositoryCustom {

    @Override
    public List search(Map searchParam, Class clazz) {
        return null;
    }

    @Override
    public Long count(Map searchParam) {
        return null;
    }

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }
}
