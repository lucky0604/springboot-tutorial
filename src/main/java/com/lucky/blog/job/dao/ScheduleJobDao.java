package com.lucky.blog.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.job.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {
    /**
     * 批量更新状态
     * @param map
     * @return
     */
    int updateBatch(Map<String, Object> map);
}
