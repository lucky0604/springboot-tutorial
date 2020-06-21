package com.lucky.blog.app.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.ArticleTagEntity;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.vo.TagPageVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleTagDao extends BaseMapper<ArticleTagEntity> {

    /**
     * 查询最热标签
     * @param limit
     * @return
     */
    List<Integer> queryHotTagIds(Integer limit);

    /**
     * 根据标签查询文章
     * @param tagPageVo
     * @return
     */
    List<ArticleEntity> queryArticlesByTag(TagPageVo tagPageVo);

    /**
     * 获取文章所有标签
     * @param articleId
     * @return
     */
    List<TagEntity> queryArticleTags(Long articleId);
}