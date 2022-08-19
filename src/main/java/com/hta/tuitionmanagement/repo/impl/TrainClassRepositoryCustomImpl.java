package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.model.TrainClass;
import com.hta.tuitionmanagement.repo.StudentRepositoryCustom;
import com.hta.tuitionmanagement.repo.TrainClassRepositoryCustom;
import com.hta.tuitionmanagement.utils.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainClassRepositoryCustomImpl extends BaseCustomRepository<TrainClass> implements TrainClassRepositoryCustom {

    @Override
    public List search(Map searchParam, Class clazz) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, TrainClass.class, parameters);
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
                    .append("FROM train_class os \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT os.* \n")
                    .append("FROM train_class os \n")
                    .append("WHERE 1=1 ");
        }

//        if (paramsSearch.containsKey("txtSearch")) {
//            if(!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
//                sb.append("AND (UPPER(os.name) LIKE :txtSearch) OR (UPPER(os.code) LIKE :txtSearch) OR (UPPER(os.note) LIKE :txtSearch");
//                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
//            }
//        }

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