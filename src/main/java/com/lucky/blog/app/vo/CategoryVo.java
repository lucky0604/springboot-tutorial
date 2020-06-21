package com.lucky.blog.app.vo;

import com.lucky.blog.app.entity.CategoryEntity;
import lombok.Data;

@Data
public class CategoryVo extends CategoryEntity {
    private static final long serialVersionUID = -2975739216517528014L;

    private int articles;
}
