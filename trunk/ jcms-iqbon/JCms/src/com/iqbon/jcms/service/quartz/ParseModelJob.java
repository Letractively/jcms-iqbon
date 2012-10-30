package com.iqbon.jcms.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseModelJob implements Job {

  private static final Logger logger = Logger.getLogger(ParseModelJob.class);

  @Autowired
  private QuartzService quartzService;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    // TODO Auto-generated method stub

  }
}
