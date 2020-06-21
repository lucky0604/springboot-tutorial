package com.lucky.blog.app.vo;

import com.lucky.blog.app.entity.TagEntity;
import lombok.Data;

@Data
public class TagVo extends TagEntity {
    private static final long serialVersionUID = 5059212992497947120L;

    private int articles;
}
