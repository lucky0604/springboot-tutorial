package com.lucky.blog.app.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.dto.ChildCommentDto;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.CommentEntity;
import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.app.vo.CommentVo;
import com.lucky.blog.common.utils.PageUtils;

public interface CommentService extends IService<CommentEntity> {
    PageUtils queryPage(Map<String, Object> map);

    /**
     * 获取文章评论信息
     * @param articleId
     * @return
     */
    List<CommentVo> queryArticleComments(Long articleId);

    /**
     * 获取子评论信息
     * @param dto
     * @return
     */
    List<CommentVo> queryChildrenComments(ChildCommentDto dto);

    /**
     * 发布文章评论
     * @param articleEntity
     * @param userEntity
     * @param object
     * @return
     */
    JSONObject publishArticleComment(ArticleEntity articleEntity, UserEntity userEntity, JSONObject object);
}