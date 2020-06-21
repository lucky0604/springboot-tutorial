package com.lucky.blog.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.job.entity.ScheduleJobEntity;

import java.util.Map;

public interface ScheduleJobService extends IService<ScheduleJobEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     * @param scheduleJobEntity
     */
    void save(ScheduleJobEntity scheduleJobEntity);

    /**
     * 更新定时任务
     * @param scheduleJobEntity
     */
    void update(ScheduleJobEntity scheduleJobEntity);

    /**
     * 批量删除任务
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     * @param jobIds
     * @param status
     * @return
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复执行
     * @param jobIds
     */
    void resume(Long[] jobIds);
}
