package com.lucky.blog.app.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.CategoryEntity;
import com.lucky.blog.app.vo.CategoryVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
    /**
     * 获取分类详情
     * @return
     */
    List<CategoryVo> queryCategoryDetails();

    /**
     * 单条文章分类详情
     * @param categoryId
     * @return
     */
    CategoryVo queryOneCategoryDetail(Integer categoryId);
}