package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.repo.BaseRepositoryCustom;

import java.util.List;
import java.util.Map;

public class BaseCustomRepositoryImpl<T> extends BaseCustomRespository<T> implements BaseRepositoryCustom<T> {


    @Override
    public List<T> search(Map<String, Object> searchParam, Class<T> clazz) {
        return null;
    }

    @Override
    public Long count(Map<String, Object> searchParam) {
        return null;
    }

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        return null;
    }
}
