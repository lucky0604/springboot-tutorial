package com.lucky.blog.app.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lucky.blog.app.dao.CategoryDao;
import com.lucky.blog.app.entity.CategoryEntity;
import com.lucky.blog.app.service.CategoryService;
import com.lucky.blog.app.vo.CategoryVo;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.common.utils.Query;

import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.selectPage(
            new Query<CategoryEntity>(params).getPage(),
            new EntityWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryVo> queryCategoryDetails() {
        return this.baseMapper.queryCategoryDetails();
    }

    @Override
    public CategoryVo queryOneCategoryDetail(Integer categoryId) {
        return this.baseMapper.queryOneCategoryDetail(categoryId);
    }

}