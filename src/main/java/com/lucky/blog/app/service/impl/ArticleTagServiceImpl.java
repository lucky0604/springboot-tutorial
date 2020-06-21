package com.lucky.blog.app.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lucky.blog.app.dao.ArticleTagDao;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.ArticleTagEntity;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.service.ArticleTagService;
import com.lucky.blog.app.vo.TagPageVo;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.common.utils.Query;

import org.springframework.stereotype.Service;

@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagDao, ArticleTagEntity> implements ArticleTagService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        
        Page<ArticleTagEntity> page = this.selectPage(
            new Query<ArticleTagEntity>(params).getPage(),
            new EntityWrapper<ArticleTagEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询最热标签
     */
    @Override
    public List<Integer> queryHotTagIds(Integer limit) {
        List<Integer> hotTagIds = this.baseMapper.queryHotTagIds(limit);
        return hotTagIds;
    }

    /**
     * 根据标签查询文章
     */
    @Override
    public List<ArticleEntity> queryArticlesByTag(TagPageVo tagPageVo) {
        return this.baseMapper.queryArticlesByTag(tagPageVo);
    }

    /**
     * 获取文章所有标签
     */
    @Override
    public List<TagEntity> queryArticleTags(Long articleId) {
        return this.baseMapper.queryArticleTags(articleId);
    }
}