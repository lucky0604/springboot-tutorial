package com.lucky.blog.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.job.entity.ScheduleJobLogEntity;

import java.util.Map;

public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
