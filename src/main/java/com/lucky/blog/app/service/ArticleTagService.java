package com.lucky.blog.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.ArticleTagEntity;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.vo.TagPageVo;
import com.lucky.blog.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface ArticleTagService extends IService<ArticleTagEntity> {
    PageUtils queryPage(Map<String, Object> params);

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
