package com.iqbon.jcms.service.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SchedulerService {
  private Scheduler scheduler;
  private JobDetail jobDetail;

  @Autowired
  public void setJobDetail(@Qualifier("jobDetail")
  JobDetail jobDetail) {
    this.jobDetail = jobDetail;
  }

  @Autowired
  public void setScheduler(@Qualifier("quartzScheduler")
  Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  /**
   * 添加任务
   */
  public void addJob() {
  }

  /**
   * 删除任务
   */
  public void removeJob() {
  }

  /**
   * 暂停任务
   */
  public void pauseJob() {
  }

  /**
   * 恢复任务
   */
  public void resumeJob() {
  }
}
