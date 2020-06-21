package com.lucky.blog.common.xss;

import com.lucky.blog.common.exception.RestException;
import org.apache.commons.lang.StringUtils;

public class SQLFilter {
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|:|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        // 转换成小写
        str = str.toLowerCase();

        // 非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};
        // 判断是否包含非法字符
        for (String keyword: keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new RestException("Invalid string");
            }
        }
        return str;
    }
}
