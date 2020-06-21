package com.lucky.blog.app.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.dto.ChildCommentDto;
import com.lucky.blog.app.entity.CommentEntity;
import com.lucky.blog.app.vo.CommentVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

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
}