package com.lucky.blog.job.controller;

import com.lucky.blog.common.component.R;
import com.lucky.blog.common.utils.PageUtils;
import com.lucky.blog.job.entity.ScheduleJobLogEntity;
import com.lucky.blog.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @GetMapping("/list")
    @RequiresPermissions("blog:schedule:log")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = scheduleJobLogService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/info/{logId}")
    public R info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.selectById(logId);
        return R.ok().put("log", log);
    }
}
