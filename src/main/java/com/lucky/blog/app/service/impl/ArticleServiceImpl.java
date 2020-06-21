package com.lucky.blog.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lucky.blog.app.dao.ArticleDao;
import com.lucky.blog.app.entity.ArticleEntity;
import com.lucky.blog.app.entity.ArticleTagEntity;
import com.lucky.blog.app.entity.CategoryEntity;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.app.service.ArticleService;
import com.lucky.blog.app.service.ArticleTagService;
import com.lucky.blog.app.service.CategoryService;
import com.lucky.blog.app.service.TagService;
import com.lucky.blog.app.service.UserService;
import com.lucky.blog.app.vo.ArticleArchivesVo;
import com.lucky.blog.common.exception.RestException;
import com.lucky.blog.common.utils.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<ArticleEntity> queryPage(Map<String, Object> params) {
        EntityWrapper<ArticleEntity> entityWrapper = new EntityWrapper<ArticleEntity>();
        String categoryIdStr = (String) params.get("categoryId");
        if (StringUtils.isNotBlank(categoryIdStr)) {
            Integer categoryId = Integer.parseInt(categoryIdStr);
            entityWrapper.eq("category_id", categoryId);
        }
        String year = (String) params.get("year");
        String month = (String) params.get("month");
        if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
            entityWrapper.eq("year(create_year)", year);
            entityWrapper.eq("month(create_time)", month);
        }

        Page<ArticleEntity> page = this.selectPage(
            new Query<ArticleEntity>(params).getPage(),
            entityWrapper
        );
        return page.getRecords();
    }

    /**
     * 发布文章按年-月汇总
     */
    @Override
    public List<ArticleArchivesVo> queryArticleArchives(int limit) {
        return this.baseMapper.queryArticleArchives(limit);
    }

    /**
     * 格式化文章列表输出
     */
    @Override
    public JSONArray getFormatArticleList(List<ArticleEntity> list) {
        JSONArray array = new JSONArray();
        for (ArticleEntity article: list) {
            JSONObject object = new JSONObject();
            object.put("id", article.getId());
            object.put("nickname", article.getNickname());
            object.put("title", article.getTitle());
            object.put("summary", article.getSummary());
            object.put("weight", article.getWeight());
            object.put("tags", article.getTags());
            object.put("createTime", article.getCreateTime());
            object.put("viewNum", article.getViewNum());
            object.put("commentNum", article.getCommentNum());
            array.add(object);
        }
        return array;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject getArticleDetailAndAddViewNum(Long articleId) {
        ArticleEntity article = this.selectById(articleId);
        if (article == null) {
            throw new RestException("Article does not exist");
        }
        JSONObject object = new JSONObject();
        // 文章详情
        object.put("id", article.getId());
        object.put("title", article.getTitle());
        object.put("summary", article.getSummary());
        object.put("createTime", article.getCreateTime());
        article.setViewNum(article.getViewNum() + 1);
        object.put("viewNum", article.getViewNum());
        object.put("commentNum", article.getCommentNum());
        object.put("content", article.getContent());

        // 文章作者信息
        UserEntity userEntity = userService.selectById(article.getUserId());
        JSONObject user = new JSONObject();
        user.put("id", userEntity.getId());
        user.put("avatar", userEntity.getAvatar());
        user.put("nickname", userEntity.getNickname());
        object.put("author", user);
        // 文章所属分类信息
        CategoryEntity categoryEntity = categoryService.selectById(article.getCategoryId());
        object.put("category", categoryEntity);

        // 文章所属标签信息
        List<TagEntity> tagEntities = articleTagService.queryArticleTags(article.getId());
        object.put("tags", tagEntities);
        
        // 文章阅读数+1
        this.updateById(article);
        return object;
    }

    /**
     * 添加一篇新文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addOneArticle(UserEntity userEntity, JSONObject json) {
        Map<Integer, String> maps = getTagMapInfos();
        // 保存文章信息
        ArticleEntity article = new ArticleEntity();
        article.setUserId(userEntity.getId());
        article.setNickname(userEntity.getNickname());
        article.setCommentNum(0);
        article.setViewNum(0);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setWeight(0);
        article.setTitle(json.getString("title"));
        article.setSummary(json.getString("summary"));

        JSONObject body = json.getJSONObject("body");
        article.setContent(body.getString("content"));
        article.setContentHtml(body.getString("contentHtml"));

        // 设置分类信息
        JSONObject category = json.getJSONObject("category");
        article.setCategoryId(category.getInteger("id"));

        // 设置标签信息
        JSONArray tags = json.getJSONArray("tags");
        StringBuilder tagStr = new StringBuilder();
        for (int i = 0; i < tags.size(); i ++) {
            Integer tagId = tags.getJSONObject(i).getInteger("id");
            if (i != 0) {
                tagStr.append(",");
            }
            tagStr.append(maps.get(tagId));
        }
        article.setTags(tagStr.toString());

        // 插入文章信息
        this.insert(article);
        Long id = article.getId();

        // 插入文章对应的标签信息
        List<ArticleTagEntity> articleTagEntityList = new ArrayList<>();
        for (int i = 0; i < tags.size(); i ++) {
            Integer tagId = tags.getJSONObject(i).getInteger("id");
            ArticleTagEntity articleTagEntity = new ArticleTagEntity();
            articleTagEntity.setArticleId(id);
            articleTagEntity.setTagId(tagId);
            articleTagEntity.setCreateTime(new Date());
            articleTagEntity.setUpdateTime(new Date());
            articleTagEntityList.add(articleTagEntity);
        }
        articleTagService.insertBatch(articleTagEntityList);
        return id;
    }

    /**
     * 获取标签map数据
     * @return
     */
    private Map<Integer, String> getTagMapInfos() {
        List<TagEntity> tagEntities = tagService.selectList(null);
        Map<Integer, String> map = new HashMap<>();
        for (TagEntity tag: tagEntities) {
            map.put(tag.getId(), tag.getTagName());
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateOneArticle(UserEntity userEntity, ArticleEntity article, JSONObject json) {
        Long id = article.getId();
        Map<Integer, String> map = getTagMapInfos();

        // 更新文章信息
        article.setTitle(json.getString("title"));
        article.setSummary(json.getString("summary"));
        JSONObject body = json.getJSONObject("body");
        article.setContent(body.getString("content"));
        article.setContentHtml(body.getString("contentHtml"));

        JSONObject category = json.getJSONObject("category");
        if (article.getCategoryId().intValue() != category.getInteger("id").intValue()) {
            article.setCategoryId(category.getInteger("id"));
        }

        // 获取文章对应的标签信息
        List<ArticleTagEntity> articleTagEntityList = new ArrayList<>();
        StringBuilder tagStr = new StringBuilder();
        JSONArray tags = json.getJSONArray("tags");
        for (int i = 0; i < tags.size(); i ++) {
            Integer tagId = tags.getJSONObject(i).getInteger("id");
            if (i != 0) {
                tagStr.append(",");
            }
            tagStr.append(map.get(tagId));
            ArticleTagEntity articleTagEntity = new ArticleTagEntity();
            articleTagEntity.setArticleId(id);
            articleTagEntity.setTagId(tagId);
            articleTagEntity.setCreateTime(new Date());
            articleTagEntity.setUpdateTime(new Date());
            articleTagEntityList.add(articleTagEntity);
        }
        article.setTags(tagStr.toString());
        article.setUpdateTime(new Date());
        article.setUserId(userEntity.getId());
        article.setNickname(userEntity.getNickname());
        this.updateById(article);
        // 删除之前的文章标签信息
        EntityWrapper<ArticleTagEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("article_id", id);
        articleTagService.delete(entityWrapper);

        // 更新文章标签信息
        articleTagService.insertBatch(articleTagEntityList);
        return id;
    }
}