package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.dto.response.ReportDTO;
import com.hta.tuitionmanagement.model.Student;
import com.hta.tuitionmanagement.repo.StudentRepositoryCustom;
import com.hta.tuitionmanagement.utils.DataUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryCustomImpl extends BaseCustomRepository<Student> implements StudentRepositoryCustom {

    @Override
    public List search(Map searchParam, Class clazz) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = buildQuery(searchParam, parameters, false);
        return getResultList(sql, Student.class, parameters);
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
                    .append("FROM student os \n")
                    .append("WHERE 1=1 ");
        } else {
            sb.append("SELECT os.* \n")
                    .append("FROM student os \n")
                    .append("WHERE 1=1 ");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
                sb.append(
                        "AND (UPPER(os.full_name) LIKE :txtSearch) OR (UPPER(os.registration_id) LIKE :txtSearch) OR (UPPER(os.course_id) LIKE :txtSearch OR (UPPER(os.citizen_id) LIKE :txtSearch)");
                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
            }
        }

        if (paramsSearch.containsKey("fromDate")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("fromDate"))) {
                sb.append("AND os.created_date >= :fromDate");
                params.put("fromDate", paramsSearch.get("fromDate").toString());
            }
        }

        if (paramsSearch.containsKey("toDate")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("toDate"))) {
                sb.append("AND os.created_date <= :toDate");
                params.put("toDate", paramsSearch.get("toDate").toString());
            }
        }

        if (paramsSearch.containsKey("code")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("code"))) {
                sb.append("AND UPPER(os.registration_id) LIKE :code");
                params.put("code", formatLike((String) paramsSearch.get("code").toString().toUpperCase()));
            }
        }

        if (paramsSearch.containsKey("status")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("status"))) {
                sb.append("AND os.status = :status");
                params.put("status",  paramsSearch.get("status").toString());
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY os.id ASC "));
            } else {
                sb.append(" ORDER BY os.id DESC ");
            }
        }

        if (!count && paramNotNullOrEmpty(paramsSearch, "pageSize")
                && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS");
            sb.append(" FETCH NEXT :limit ROWS ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")),
                    DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            Integer limit = !DataUtils.isNullOrEmpty(paramsSearch.get("limit"))
                    ? DataUtils.parseToInt(paramsSearch.get("limit"))
                    : DataUtils.parseToInt(paramsSearch.get("pageSize"));
            params.put("limit", limit);
        }

        return sb.toString();
    }
    
    public String reportQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count) {
        
        StringBuilder sb = new StringBuilder();

        String selectStr = "SELECT s.*, tc.header, tc.money as tuition_fee_payable ,SUM(t.money) as tuition_fee_paid \n";
        
        String joinStr = "JOIN tran_class as tc ON tc.id = ss.train_class_id \n"
        + "JOIN tuition as t ON t.student_id = ss.id \n";
        

        if (count) {
            sb.append("SELECT COUNT(s.id) \n")
                    .append("FROM student AS s \n")   
                    .append(joinStr)
                    .append("WHERE 1=1 ");
        } else {
            sb.append(selectStr)
                    .append("FROM student s \n")
                    .append(joinStr)
                    .append("WHERE 1=1 ")
                    .append("GROUP BY s.registration_id");
        }

        if (paramsSearch.containsKey("txtSearch")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("txtSearch"))) {
                sb.append(
                        "AND (UPPER(s.full_name) LIKE :txtSearch) OR (UPPER(s.registration_id) LIKE :txtSearch) OR (UPPER(os.citizen_id) LIKE :txtSearch)");
                params.put("txtSearch", formatLike((String) paramsSearch.get("txtSearch")).toUpperCase());
            }
        }

        if (paramsSearch.containsKey("fromDate")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("fromDate"))) {
                sb.append("AND s.created_date >= :fromDate");
                params.put("fromDate", paramsSearch.get("fromDate").toString());
            }
        }

        if (paramsSearch.containsKey("toDate")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("toDate"))) {
                sb.append("AND s.created_date <= :toDate");
                params.put("toDate", paramsSearch.get("toDate").toString());
            }
        }

        if (paramsSearch.containsKey("code")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("code"))) {
                sb.append("AND UPPER(s.registration_id) LIKE :code");
                params.put("code", formatLike((String) paramsSearch.get("code").toString().toUpperCase()));
            }
        }

        if (paramsSearch.containsKey("status")) {
            if (!DataUtils.isNullOrEmpty(paramsSearch.get("status"))) {
                sb.append("AND s.status = :status");
                params.put("status",  paramsSearch.get("status").toString());
            }
        }

        if (!count) {
            if (paramsSearch.containsKey("sort")) {
                sb.append(formatSort((String) paramsSearch.get("sort"), " ORDER BY s.id ASC "));
            } else {
                sb.append(" ORDER BY s.id DESC ");
            }
        }

        if (!count && paramNotNullOrEmpty(paramsSearch, "pageSize")
                && !"0".equalsIgnoreCase(String.valueOf(paramsSearch.get("pageSize")))) {
            sb.append(" OFFSET :offset ROWS");
            sb.append(" FETCH NEXT :limit ROWS ONLY");
            params.put("offset", offsetPaging(DataUtils.parseToInt(paramsSearch.get("pageNumber")),
                    DataUtils.parseToInt(paramsSearch.get("pageSize"))));
            Integer limit = !DataUtils.isNullOrEmpty(paramsSearch.get("limit"))
                    ? DataUtils.parseToInt(paramsSearch.get("limit"))
                    : DataUtils.parseToInt(paramsSearch.get("pageSize"));
            params.put("limit", limit);
        }

        return sb.toString();
    }


    @Override
    public List<ReportDTO> reportSearch(Map<String, Object> searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = reportQuery(searchParam, parameters, false);
        return getResultList(sql, ReportDTO.class, parameters);
    }

    @Override
    public Long countReport(Map<String, Object> searchParam) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = reportQuery(searchParam, parameters, true);
        return getCountResult(sql, parameters);
    }

}
