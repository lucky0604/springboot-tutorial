package com.lucky.blog.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.CategoryEntity;
import com.lucky.blog.app.vo.CategoryVo;
import com.lucky.blog.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<CategoryEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 文章分类详情
     * @return
     */
    List<CategoryVo> queryCategoryDetails();

    CategoryVo queryOneCategoryDetail(Integer categoryId);
}
