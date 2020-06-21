package com.lucky.blog.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("blog_article_tag")
public class ArticleTagEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long articleId;

    private Integer tagId;

    private Date createTime;

    private Date updateTime;
}
