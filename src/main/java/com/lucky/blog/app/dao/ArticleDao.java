package com.lucky.blog.app.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.vo.ArticleArchivesVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {

    /**
     * 发布文章 按年-月汇总
     * @param limit
     * @return
     */
    List<ArticleArchivesVo> queryArticleArchives(int limit);
}