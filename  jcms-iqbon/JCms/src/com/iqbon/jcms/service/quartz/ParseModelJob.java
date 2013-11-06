package com.iqbon.jcms.service.quartz;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.service.ModelService;
import com.iqbon.jcms.util.BeanFactory;
import com.iqbon.jcms.util.NotFoundException;

public class ParseModelJob implements Job {

  private static final Logger logger = Logger.getLogger(ParseModelJob.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    QuartzService quartzService = (QuartzService) BeanFactory.getBean("quartzService");
    ModelService modelService = (ModelService) BeanFactory.getBean("modelService");
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
      String refresh = jobDetail.getJobDataMap().getString(Quartz.JOB_FRESH_MINUTE);
      List<String> modelIdList = modelService.getNeedPublishModelByRefresh(refresh);
      for (String modelName : modelIdList) {
        try {
          Model model = modelService.getModelInfoByModelName(modelName);
          modelService.publishModelContent(model);
        } catch (IOException e) {
          logger.error(modelName + "模板刷新失败", e);
          logEnd(start, jobName);
          quartzService.setQuartzStatus(jobName, Quartz.runningStatus.stop.ordinal());
        } catch (NotFoundException ne) {
          logger.error(modelName + "模板已经被删除", ne);
          logEnd(start, jobName);
          quartzService.setQuartzStatus(jobName, Quartz.runningStatus.stop.ordinal());
        }
      }
      logEnd(start, jobName);
      quartzService.setQuartzStatus(jobName, Quartz.runningStatus.stop.ordinal());
    }
  }

  /**
   * 输出结束日志
   * @param jobName
   */
  private void logEnd(long start, String jobName) {
    long end = System.currentTimeMillis();
    logger.info(jobName + "刷新完毕，耗时:" + (end - start) + "ms");
  }
}
