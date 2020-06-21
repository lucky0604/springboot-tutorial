package com.lucky.blog.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("blog_log")
public class LogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 访问IP
     */
    private String ip;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 访问方法
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * 操作人昵称
     */
    private String nickname;

    /**
     * 操作事项
     */
    private String operation;

    /**
     * 操作耗时
     */
    private Long time;

    /**
     * 操作用户id
     */
    private Long userId;
}
