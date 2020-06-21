package com.lucky.blog.common.validator;

import com.lucky.blog.common.exception.RestException;
import org.apache.commons.lang.StringUtils;

public abstract class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RestException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RestException(message);
        }
    }
}
