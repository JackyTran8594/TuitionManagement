package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.model.UserEntity;
import com.hta.tuitionmanagement.repo.UserEntityRepositoryCustom;

import java.util.List;
import java.util.Map;

public class UserEntityRepositoryCustomImpl extends BaseCustomRepository<UserEntity> implements UserEntityRepositoryCustom {
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
