package com.lucky.blog.job.utils;

import com.lucky.blog.common.component.SpringContextUtils;
import com.lucky.blog.job.entity.ScheduleJobEntity;
import com.lucky.blog.job.entity.ScheduleJobLogEntity;
import com.lucky.blog.job.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScheduleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService service = Executors.newSingleThreadExecutor();
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);
        // 获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        // 数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());

        // 任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            logger.info("Job ready for start, Job ID: " + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            // 任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);
            // 任务状态 0：成功  1：失败
            log.setStatus(0);
            logger.info("Task done, Job ID: " + scheduleJob.getJobId() + " cost :" + times + " ms");
        } catch (Exception e) {
            logger.error("Task failed, Job ID: " + scheduleJob.getJobId(), e);

            // 任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int) times);

            // 任务状态 0：成功 1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.insert(log);
        }
    }
}
