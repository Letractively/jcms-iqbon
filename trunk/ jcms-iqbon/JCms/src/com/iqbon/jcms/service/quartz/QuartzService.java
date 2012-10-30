package com.iqbon.jcms.service.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.system.QuartzDAO;
import com.iqbon.jcms.domain.Quartz;

@Service
public class QuartzService {

  private static final Logger logger = Logger.getLogger(QuartzService.class);
  
  private static Map<String, String> quartzStatus = new HashMap<String, String>(10);

  @Autowired
  private QuartzDAO quartzDAO;

  @Autowired
  private Scheduler quartzScheduler;

  /**
   * 查询所有定时任务信息
   * @return
   */
  public List<Quartz> getQuartzJobList() {
    return quartzDAO.selectAllQuartJob();
  }

  /**
   * 增加模板解析任务
   * @param jobName 
   * @param topicIds
   * @param description
   * @param minutePattern
   * @param hourPattern
   * @throws SchedulerException 
   */
  public void addParseModelJob(String jobName, List<String> topicIds, String description,
      String minutePattern, String hourPattern) throws SchedulerException {
    //初始化JobDetail
    JobDataMap dataMap = new JobDataMap();
    dataMap.put(Quartz.PARSE_MODEL_TOPIC_KEY, topicIds);
    dataMap.put(Quartz.JOB_LOG_KEY, new StringBuilder());
    dataMap.put(Quartz.CREATE_JOB_TIME_KEY, DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
    JobDetail jobDetail = JobBuilder.newJob(ParseModelJob.class)
        .withIdentity(jobName, Scheduler.DEFAULT_GROUP)
        .withDescription(description).usingJobData(dataMap).build();
    //初始化CronTrigger
    String cronPattern = "0 " + minutePattern + " " + hourPattern + " * * ?";
    CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(jobName + "_trigger", Scheduler.DEFAULT_GROUP)
        .forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(cronPattern)).build();
    //添加cornjob
    quartzScheduler.scheduleJob(jobDetail, trigger);
    //添加listener
    Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
    quartzScheduler.getListenerManager().addJobListener(new QuartzJobListener(jobName), matcher);
  }

  /**
   * 删除定时任务
   * @param jobName
   * @throws SchedulerException
   */
  public void deleteJob(String jobName) throws SchedulerException {
    quartzScheduler.deleteJob(new JobKey(jobName, Scheduler.DEFAULT_GROUP));
  }

  /**
   * 设置定时任务状态
   * @param jobName
   * @param status
   */
  public synchronized String setQuartzStatus(String jobName, int status) {
    String sta = String.valueOf(status);
    quartzStatus.put(jobName, sta);
    return sta;
  }

  /**
   * 获取定时任务状态
   * @param jobName
   */
  public synchronized String getQuartzStatus(String jobName) {
    return quartzStatus.get(jobName);
  }
}
