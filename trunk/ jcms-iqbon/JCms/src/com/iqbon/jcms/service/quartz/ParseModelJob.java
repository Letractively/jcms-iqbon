package com.iqbon.jcms.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ParseModelJob implements Job {

  private static final Logger logger = Logger.getLogger(ParseModelJob.class);

  private QuartzService quartzService;

  public ParseModelJob() {
    System.out.println("初始化定时任务");
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("定时任务开始。。。。。。。。");

  }
}
