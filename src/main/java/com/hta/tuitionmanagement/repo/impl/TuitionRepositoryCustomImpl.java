package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.model.Tuition;
import com.hta.tuitionmanagement.repo.TuitionRepositoryCustom;
import com.hta.tuitionmanagement.utils.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuitionRepositoryCustomImpl extends BaseCustomRepository<Tuition> implements TuitionRepositoryCustom {
    @Override
    public List search(Map searchParam, Class clazz) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, Tuition.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(id) \n")
                    .append("FROM tuition os \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT os.* \n")
                    .append("FROM tuition os \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("param")) {
            if(!DataUtils.isNullOrEmpty(paramsSearch.get("param"))) {
                sb.append("AND os.student_id = :param");
                params.put("param", DataUtils.parseToInt(paramsSearch.get("param")));
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY os.id ASC "));
            } else {
                sb.append(" ORDER BY os.id DESC ");
            }
        }
        if(!count && paramNotNullOrEmpty(paramsSearch, "pageSize") && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS");
            sb.append(" FETCH NEXT :limit ROWS ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")), DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            Integer limit = !DataUtils.isNullOrEmpty(paramsSearch.get("limit")) ? DataUtils.parseToInt(paramsSearch.get("limit")) : DataUtils.parseToInt(paramsSearch.get("pageSize"));
            params.put("limit", limit);
        }
        return sb.toString();
    }
}
