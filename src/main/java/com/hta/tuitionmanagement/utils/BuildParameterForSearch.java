package com.hta.tuitionmanagement.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public final class BuildParameterForSearch<T> {
    public Map<String, Object> buildParams(Map<String, Object> params, Class<T> t, String name) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> parameters = new HashedMap<>();
            // check basic search object by pojo class
//            if (!DataUtils.isNullOrEmpty(params.get(name))) {
//                String json = params.get(name).toString();
//                if(!DataUtils.isNullOrEmpty(json)) {
//                    T pojo = mapper.convertValue(json, t);
//                    parameters = mapper.convertValue(pojo, new TypeReference<Map<String, Object>>() {
//                    });
//                }
//            }
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if(!entry.getKey().equals(name)) {
                    parameters.put(entry.getKey(), entry.getValue());
                }
            }
            return parameters;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
