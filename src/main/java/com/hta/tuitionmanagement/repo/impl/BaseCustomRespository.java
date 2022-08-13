package com.hta.tuitionmanagement.repo.impl;

import com.hta.tuitionmanagement.utils.DataUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class BaseCustomRespository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <T> T getSingleResult(String query, Map<String, Object> params, Class<T> clazz) {
        try {
            Query nativeQuery = entityManager.createNativeQuery(query);
            if (CollectionUtils.isEmpty(params)) {
                return clazz.cast(nativeQuery.getSingleResult());
            }
            params.forEach(nativeQuery::setParameter);
            ;
            return clazz.cast(nativeQuery.getSingleResult());

        } catch (NoResultException e) {
            return null;
        }
    }

    private <T> T excuteSingleResult(Query nativeQuery, Map<String, Object> params) {
        try {
            if (CollectionUtils.isEmpty(params)) {
                return (T) nativeQuery.getSingleResult();
            }
            params.forEach(nativeQuery::setParameter);
            return (T) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private <T> List<T> excuteListResultQuery(Query nativeQuery, Map<String, Object> params) {
        try {
            if (CollectionUtils.isEmpty(params)) {
                return nativeQuery.getResultList();
            }
            params.forEach(nativeQuery::setParameter);
            return nativeQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public abstract String buildQuery(Map<String, Object> paramsSearch, Map<String, Object> params, boolean count);

    public String formatLike(String param) {
        return "%" + param.trim() + "%";
    }

    protected Integer offsetPaging(Integer page, Integer limit) {
        page = page == null ? 0 : page;
        limit = limit == null ? 0 : limit;
        return (page) * limit;
    }

    protected String formatSort(String sort, String defaultSort) {
        if (DataUtils.notNull(sort)) {
            List<String> items = Arrays.asList(sort.split(";"));
            return formatSort(items, defaultSort);
        }
        return defaultSort;
    }

    protected String formatSort(List<String> sort, String defaultSort) {
        if (DataUtils.notNullOrEmpty(sort)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ORDER BY ");
            if (sort.get(0).contains(",")) {
                //&sort=code,asc&sort=lastUpdateDate,desc
                String[] tmpArr;
                for (String tmp : sort) {
                    tmpArr = tmp.split(",");
                    if (tmpArr.length > 1) {
                        sb.append(" ")
                                .append(DataUtils.camelToSnake(tmpArr[0]))
                                .append(" ")
                                .append(tmpArr[1])
                                .append(",");
                    } else {
                        sb.append(" ")
                                .append(DataUtils.camelToSnake(tmpArr[0]))
                                .append(" ASC,");
                    }
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                sb.append(" ");
                return sb.toString();
            } else {
                //sort=code,asc
                for (String s : sort) {
                    sb.append(DataUtils.camelToSnake(s))
                            .append(" ");
                }
                sb.append(" ");
                return sb.toString();
            }
        }
        return defaultSort == null ? "" : String.format(" %s ", defaultSort);
    }

    public boolean paramNotNullOrEmpty(Map<String, Object> paramSearch, String key) {
        if (paramSearch.get(key) == null) {
            return false;
        }
        String data = String.valueOf(paramSearch.get(key));
        return DataUtils.notNullOrEmpty(data);
    }

    protected <T> T getSingleResult(String query, Class<T> clazz, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, clazz);
        return excuteSingleResult(nativeQuery, params);
    }

    protected <T> List<T> getResultList(String query, String mapping, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, mapping);
        return excuteSingleResult(nativeQuery, params);
    }

    protected <T> List<T> getResultList(String query, Class<T> clazz, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query, clazz);
        return excuteSingleResult(nativeQuery, params);
    }

    protected List<Object[]> getResultList(String query, Map<String, Object> params) {
        try {
            Query nativeQuery = entityManager.createNativeQuery(query);
            if (CollectionUtils.isEmpty(params)) {
                return nativeQuery.getResultList();
            }
            params.forEach(nativeQuery::setParameter);
            return nativeQuery.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    protected int excuteUpdate(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return nativeQuery.executeUpdate();
        }
        params.forEach(nativeQuery::setParameter);
        return nativeQuery.executeUpdate();
    }

    protected Long getCountResult(String query, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        if (CollectionUtils.isEmpty(params)) {
            return ((Integer) nativeQuery.getSingleResult()).longValue();
        }
        params.forEach(nativeQuery::setParameter);
        return ((Integer) nativeQuery.getSingleResult()).longValue();
    }


}
