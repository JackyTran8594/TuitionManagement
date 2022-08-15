package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.model.Function;
import com.hta.tuitionmanagement.repo.FunctionRepositoryCustom;
import com.hta.tuitionmanagement.utils.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionRepositoryCustomImpl extends BaseCustomRepository<Function> implements FunctionRepositoryCustom {

    @Override
    public String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        StringBuilder sb = new StringBuilder();
        if (count) {
            sb.append("SELECT COUNT(id) \n")
                    .append("FROM [function] func \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT func.* \n")
                    .append("FROM [function] func \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            if(!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
                sb.append("AND (func.menu_name LIKE :txtSearch) OR (func.menu_code LIKE :txtSearch) OR (func.description) LIKE :txtSearch)");
                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY func.id ASC "));
            } else {
                sb.append(" ORDER BY func.id DESC ");
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

    @Override
    public List search(Map searchParam, Class t) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, Function.class, parameters);
    }

    @Override
    public Long count(Map searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }
}