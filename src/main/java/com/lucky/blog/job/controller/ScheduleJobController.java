package com.lucky.blog.job.controller;

import com.lucky.blog.common.annotation.SysLog;
import com.lucky.blog.common.component.R;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.common.validator.ValidatorUtils;
import com.lucky.blog.job.entity.ScheduleJobEntity;
import com.lucky.blog.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @GetMapping("/list")
    @RequiresPermissions("blog:schedule:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = scheduleJobService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/info/{jobId}")
    @RequiresPermissions("blog:schedule:info")
    public R info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.selectById(jobId);
        return R.ok().put("schedule", schedule);
    }

    @SysLog("保存定时任务")
    @PostMapping("/save")
    @RequiresPermissions("blog:schedule:save")
    public R save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.save(scheduleJob);
        return R.ok();
    }

    @SysLog("修改定时任务")
    @PostMapping("/update")
    @RequiresPermissions("blog:schedule:update")
    public R update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.update(scheduleJob);
        return R.ok();
    }

    @SysLog("删除定时任务")
    @PostMapping("/delete")
    @RequiresPermissions("blog:schedule:delete")
    public R delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
        return R.ok();
    }

    @SysLog("立即执行任务")
    @PostMapping("/run")
    @RequiresPermissions("blog:schedule:run")
    public R run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return R.ok();
    }

    @SysLog("暂停定时任务")
    @PostMapping("/pause")
    @RequiresPermissions("blog:schedule:pause")
    public R pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return R.ok();
    }

    @SysLog("恢复定时任务")
    @PostMapping("/resume")
    @RequiresPermissions("blog:schedule:resume")
    public R resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return R.ok();
    }
}
