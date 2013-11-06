package com.iqbon.jcms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.service.TopicService;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

/**
 * 登录首页
 * @author hp
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class MainAction extends JCMSAction {

  @Autowired
  private TopicService topicService;
  
  /**
   * 后台登录首页
   * @return
   */
  @RequestMapping(value = "/main.do")
  public ModelAndView mainPage() {
    ModelAndView mav = new ModelAndView();
    List<Topic> topicList = topicService.getTopTopicList();
    mav.addObject("topicList",topicList);
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "main");
    return mav;
  }

}
