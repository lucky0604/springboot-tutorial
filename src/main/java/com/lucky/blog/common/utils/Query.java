package com.lucky.blog.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.lucky.blog.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private Page<T> page;

    /**
     * 当前页码
     */
    private int pageNo = 1;

    private int pageSize = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        // 分页参数
        if (params.get("pageNo") != null) {
            pageNo = Integer.parseInt((String) params.get("pageNo"));
        }
        if (params.get("pageSize") != null) {
            pageSize = Integer.parseInt((String) params.get("pageSize"));
        }
        this.put("offset", (pageNo - 1) * pageSize);
        this.put("page", pageNo);
        this.put("limit", pageSize);

        // 防止SQL注入
        String sidx = SQLFilter.sqlInject((String) params.get("sidx"));
        String order = SQLFilter.sqlInject((String) params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        // mybatis-plus分页
        this.page = new Page<>(pageNo, pageSize);

        if (StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)) {
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }
    }

    public Page<T> getPage() {
        return page;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }
}
