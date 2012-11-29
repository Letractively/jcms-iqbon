package com.iqbon.jcms.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.service.CodeService;
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

  @Autowired
  private CodeService codeService;

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
  @RequestMapping(value = "/jobDelete.do")
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
  @RequestMapping(value = "/jobAdd.do")
  public String addParseModelJob(@RequestParam("jobName")
  String jobName, @RequestParam(value = "description", required = false)
  String description, @RequestParam("minutePattern")
  String minutePattern, @RequestParam("hourPattern")
  String hourPattern) {
    try {
      quartzService.addParseModelJob(jobName, description, minutePattern, hourPattern);
      return redirect("/quartz/jobList.do");
    } catch (SchedulerException e) {
      logger.error("增加定时刷新模板任务失败", e);
      return getErrorUrl("增加定时任务失败");
    }
  }

  /**
   * 显示添加模板刷新任务的页面
   * @return
   */
  @RequestMapping(value = "/showJobAdd.do")
  public ModelAndView showAddParseModelJob() {
    ModelAndView mav = new ModelAndView();
    Code refresh = codeService.getCodeGroupInfo(KeyConstant.CODE_MODEL_REFRESH);
    if (refresh == null) {
      logger.error("系统码：" + KeyConstant.CODE_MODEL_REFRESH + "为空");
      errorMav.setErrorInfo("系统码：" + KeyConstant.CODE_MODEL_REFRESH + "为空，请联系管理员添加");
      return errorMav;
    }
    List<Code> refreshList = codeService.getSubCodeByGroupAndParent(refresh.getGroupName(),
        refresh.getGroupName());
    mav.addObject("refreshList", refreshList);
    mav.setViewName(KeyConstant.QUARTZ_JSP_PATH + "quartzJobAdd");
    return mav;
  }

}
