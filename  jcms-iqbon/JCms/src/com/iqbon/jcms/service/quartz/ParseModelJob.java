package com.iqbon.jcms.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.util.BeanFactory;

public class ParseModelJob implements Job {

  private static final Logger logger = Logger.getLogger(ParseModelJob.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    QuartzService quartzService = (QuartzService) BeanFactory.getBean("quartzService");
    JobDetail jobDetail = context.getJobDetail();
    String jobName = jobDetail.getKey().getName();
    if (String.valueOf(Quartz.runningStatus.running.ordinal()).equals(
        quartzService.getQuartzStatus(jobName))) {//如果是正在运行状态
      logger.warn("上一个调度任务" + jobName + "正在运行，本次调度运行跳过。");
      return;
    } else {
      quartzService.setQuartzStatus(jobName, Quartz.runningStatus.running.ordinal());
      long start = System.currentTimeMillis();
      logger.info("开始刷新文章");
      quartzService.setQuartzStatus(jobName, Quartz.runningStatus.stop.ordinal());
      long end = System.currentTimeMillis();
      logger.info(jobName + "刷新完毕，耗时:" + (start - end) + "ms");

    }

  }
}
