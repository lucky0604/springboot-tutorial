package com.lucky.blog.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果add group失败，则update group组不会再校验
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
