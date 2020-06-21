package com.lucky.blog.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("blog_comment")
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long userId;

    private Long articleId;

    private String content;

    private Long parentId;

    private Long toUid;

    private String levelFlag;

    private Date createTime;

    private Date updateTime;
}
