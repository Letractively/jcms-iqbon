package com.iqbon.jcms.service.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.iqbon.jcms.domain.Quartz;

public class ScheduleFactory extends SchedulerFactoryBean {

  @Autowired
  private QuartzService quartzService;

  @Override
  public void afterPropertiesSet() throws Exception {
    super.afterPropertiesSet();
    List<Quartz> quartzList = quartzService.getQuartzJobList();
    for(Quartz quartz:quartzList){
      String jobName = quartz.getJobName();
      logger.info("初始化定时任务" + jobName + "临时状态");
      quartzService.setQuartzStatus(jobName, Quartz.runningStatus.none.ordinal());
    }
  }
}
