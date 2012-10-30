package com.iqbon.jcms.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * 定时任务监听器
 * @author hp
 *
 */
public class QuartzJobListener implements JobListener {

  private Logger logger = Logger.getLogger(QuartzJobListener.class);

  private Thread runningThreads;

  private String name;

  private QuartzJobListener() {
  }

  public QuartzJobListener(String jobName) {
    name = jobName;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void jobExecutionVetoed(JobExecutionContext context) {
    logger.warn("Job vetoed - " + name);
  }

  /**
   * 调度任务开始前执行
   **/
  @Override
  public void jobToBeExecuted(JobExecutionContext context) {
    JobDetail jobDetail = context.getJobDetail();
    String jobName = jobDetail.getKey().getName();
    logger.info(jobName + " to be executed");
  }

  /**
   * 调度任务结束时，记录任务的运行状态为停止，更新最近一次运行时间为当前减上次开始，
   * 记录本次运行的停止时间，把本次运行时产生的日志log保存下来
   */
  @Override
  public void jobWasExecuted(JobExecutionContext context,
      JobExecutionException jobExecutionException) {
    JobDetail jobDetail = context.getJobDetail();
    String jobName = jobDetail.getKey().getName();
    logger.info(jobName + " was executed");

  }

}
