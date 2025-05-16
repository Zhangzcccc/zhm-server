package com.ic.agile.modules.job.init;

import com.ic.agile.modules.job.dao.ScheduleJobDao;
import com.ic.agile.modules.job.entity.ScheduleJobEntity;
import com.ic.agile.modules.job.utils.ScheduleUtils;
import lombok.AllArgsConstructor;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化定时任务数据
 *
 * @author zhangzc
 */
@Component
@AllArgsConstructor
public class JobCommandLineRunner implements CommandLineRunner {
    private final Scheduler scheduler;
    private final ScheduleJobDao scheduleJobDao;

    @Override
    public void run(String... args) {
        List<ScheduleJobEntity> scheduleJobList = scheduleJobDao.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }
}