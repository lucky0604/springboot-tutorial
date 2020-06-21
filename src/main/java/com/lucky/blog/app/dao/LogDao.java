package com.lucky.blog.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.LogEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao extends BaseMapper<LogEntity> {

}