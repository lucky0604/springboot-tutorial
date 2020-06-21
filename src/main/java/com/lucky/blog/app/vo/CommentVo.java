package com.lucky.blog.app.vo;

import com.lucky.blog.app.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentVo extends CommentEntity {
    private String avatar;
    private String nickname;
}
