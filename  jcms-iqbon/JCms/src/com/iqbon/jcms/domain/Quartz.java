package com.iqbon.jcms.domain;


/**
 * 定时任务的对象
 * @author hp
 *
 */
public class Quartz {

  /**
   * @author 运行状态
   *
   */
  public static enum runningStatus {
    /**
     * 运行中
     */
    running,
    /**
     * 运行结束
     */
    stop,
    /**
     * 从未运行
     */
    none
  };
  
  /**
   * 定时任务启动时间保存的键值
   */
  public static final String CREATE_JOB_TIME_KEY = "createTime";

  /**
   * 定时任务启动日志保存的键值
   */
  public static final String JOB_LOG_KEY = "jobLog";

  /**
   * 解析模板定时任务栏目ID信息保存的键值
   */
  public static final String PARSE_MODEL_TOPIC_KEY = "topicIds";

  private String jobName;

  private String triggerName;

  private String nextTime;

  private String prevTime;

  private String startTime;

  private String endTime;

  private String status;

  private String description;

  public String getTriggerName() {
    return triggerName;
  }

  public void setTriggerName(String triggerName) {
    this.triggerName = triggerName;
  }

  public String getNextTime() {
    return nextTime;
  }

  public void setNextTime(String nextTime) {
    this.nextTime = nextTime;
  }

  public String getPrevTime() {
    return prevTime;
  }

  public void setPrevTime(String prevTime) {
    this.prevTime = prevTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
