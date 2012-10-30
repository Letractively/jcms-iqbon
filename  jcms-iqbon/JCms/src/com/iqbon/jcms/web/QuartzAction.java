package com.iqbon.jcms.web;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.service.quartz.QuartzService;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/quartz")
public class QuartzAction extends JCMSAction {

  private static final Logger logger = Logger.getLogger(QuartzAction.class);

  @Autowired
  private QuartzService quartzService;

  /**
   * 查询所有定时任务信息
   * @return
   */
  @RequestMapping(value = "/jobList.do")
  public ModelAndView getQuartzJobList() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName(KeyConstant.QUARTZ_JSP_PATH + "quartzJobList");
    List<Quartz> list =  quartzService.getQuartzJobList();
    mav.addObject("quartzList", list);
    return mav;
  }

  /**
   * 重置任务状态
   */
  @RequestMapping(value = "/resetJob.do")
  public String resetJobStatus(@RequestParam("jobName")
  String jobName) {
    quartzService.setQuartzStatus(jobName, Quartz.runningStatus.stop.ordinal());
    return redirect("/quartz/jobList.do");
  }
  
  /**
   * 删除任务
   * @param jobName
   * @return
   */
  public String deleteJob(@RequestParam("jobName")
  String jobName) {
    try {
      quartzService.deleteJob(jobName);
      return redirect("/quartz/jobList.do");
    } catch (SchedulerException e) {
      logger.error("删除定时任务" + jobName + "失败", e);
      return getErrorUrl("删除定时任务失败");
    }
  }

  /**
   * 增加刷新模板任务
   * @param jobName
   * @param topicIds
   * @param description
   * @param minutePattern
   * @param hourPattern
   * @return
   */
  public String addParseModelJob(@RequestParam("jobName") String jobName,@RequestParam("topicIds") String topicIds,@RequestParam(value = "description", required = false) String description,@RequestParam("minutePattern") String minutePattern,@RequestParam("hourPattern") String hourPattern){
    List<String> topicList = Arrays.asList(topicIds.split(","));
    try {
      quartzService.addParseModelJob(jobName, topicList, description, minutePattern, hourPattern);
      return redirect("/quartz/jobList.do");
    } catch (SchedulerException e) {
      logger.error("增加定时刷新模板任务失败", e);
      return getErrorUrl("增加定时任务失败");
    }
  }

}
