package com.lucky.blog.app.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.vo.TagVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagDao extends BaseMapper<TagEntity> {
    List<TagEntity> queryHotTagDetails(Integer[] tagIds);

    List<TagVo> queryTagDetails();

    TagVo queryOneTagDetail(Integer tagId);
}