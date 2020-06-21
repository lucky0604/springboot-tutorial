package com.lucky.blog.app.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.app.vo.ArticleArchivesVo;

import java.util.List;
import java.util.Map;

/**
 * 文章表
 */
public interface ArticleService extends IService<ArticleEntity> {
    List<ArticleEntity> queryPage(Map<String, Object> params);

    /**
     * 发布文章按年-月汇总
     * @param limit
     * @return
     */
    List<ArticleArchivesVo> queryArticleArchives(int limit);

    JSONArray getFormatArticleList(List<ArticleEntity> list);

    /**
     * 根据id获取文章，添加阅读数
     * @param articleId
     * @return
     */
    JSONObject getArticleDetailAndAddViewNum(Long articleId);

    /**
     * 添加一篇新文章
     * @param userEntity
     * @param json
     * @return
     */
    Long addOneArticle(UserEntity userEntity, JSONObject json);

    Long updateOneArticle(UserEntity userEntity, ArticleEntity article, JSONObject json);
}
