package com.iqbon.jcms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.PushRecordService;
import com.iqbon.jcms.service.TopicService;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.ActionUtil;

@Controller
@Scope("prototype")
@RequestMapping("/admin/topic")
public class TopicAction {
  
  private final Logger logger = Logger.getLogger(TopicAction.class);
  
  @Autowired
  private TopicService topicService;
  @Autowired
  private PushRecordService pushRecordService;

  /**
     * 顶级栏目JSON数据
     * @return
     */
  @RequestMapping(value = "/topTopicList.do")
  @ResponseBody
  public Map<String, Object> topTopicList() {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Topic> topicList = topicService.getTopTopicList();
    map.put("topicList", topicList);
    return map;
  }

  /**
   * 栏目列表信息页，显示本栏目的子栏目和推送列表
   * @param topicId
   * @return
   */
  @RequestMapping(value = "/topicPage.do")
  public ModelAndView topicPage(@RequestParam("topicid") String topicid,
      @RequestParam("pageNum") int pageNum, @RequestParam("type") int type) {
    ModelAndView mav = new ModelAndView();
    if (pageNum <= 0) {
      pageNum = 1;
    }
    List<Topic> subTopicList = topicService.getSubTopicList(topicid);
    int totalNum = pushRecordService.getPushRecordNumByTopicAndType(topicid, type);
    int totalPageNum = ActionUtil.countTotalPageNum(totalNum, ActionUtil.DEFAUL_PAGE_SIZE);
    List<PushRecord> pushRecordList = pushRecordService.getPushRecordByTopicid(topicid, type,
        pageNum, ActionUtil.DEFAUL_PAGE_SIZE);
    mav.addObject("subTopicList", subTopicList);
    mav.addObject("pushRecordList", pushRecordList);
    mav.addObject("totalPageNum", totalPageNum);
    mav.addObject("pageNum", pageNum);
    mav.addObject("type", type);
    mav.addObject("topicid", topicid);
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "topicPage");
    return mav;
  }

  /**
   *  根据栏目ID获取子栏目列表JSON数据
   * @return
   */
  @RequestMapping(value = "/subTopicList.do")
  @ResponseBody
  public Map<String, Object> subTopicList(@RequestParam("topicId") String topicId) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isEmpty(topicId))
    {
      return map;
    }
    List<Topic> subTopicList = topicService.getSubTopicList(topicId);
    map.put("topicList", subTopicList);
    return map;
  }

  /**
   * 新建子栏目
   * @param parentTopicid
   * @param topicName
   * @param user
   * @return
   */
  @RequestMapping(value = "/addSubTopic.do")
  @ResponseBody
  public ModelAndView addSubTopic(@RequestParam("parentTopicid")
  String parentTopicid, @RequestParam("topicName")
  String topicName, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    ModelAndView errorMav = new ModelAndView();
    errorMav.setViewName(KeyConstant.ERROR_PAGE);
    User user = (User) session.getAttribute(KeyConstant.SESSION_KEY_USER);
    if (user == null) {
      return errorMav;
    }
    try {
      topicService.addTopic(parentTopicid, topicName, user.getUserName());
      return this.topicPage(parentTopicid, pageNum, type);
    } catch (Exception e) {
      logger.error("新建子栏目失败，parentTopicid=" + parentTopicid + " topicName=" + topicName + " user="
          + user, e);
      return errorMav;
    }
  }

 
}
