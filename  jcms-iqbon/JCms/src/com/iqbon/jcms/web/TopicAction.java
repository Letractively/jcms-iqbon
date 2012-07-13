package com.iqbon.jcms.web;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.PushRecordService;
import com.iqbon.jcms.service.TopicService;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;
import com.iqbon.jcms.web.util.Page;

@Controller
@Scope("prototype")
@RequestMapping("/admin/topic")
public class TopicAction extends JCMSAction {
  
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
    Topic topic = topicService.getTopicInfo(topicid);
    List<Topic> subTopicList = topicService.getSubTopicList(topicid);
    int totalNum = pushRecordService.getPushRecordNumByTopicAndType(topicid, type);
    Page page = new Page(totalNum, pageNum);
    int totalPageNum = page.getTotalPage();
    List<PushRecord> pushRecordList = pushRecordService.getPushRecordByTopicid(topicid, type,
        pageNum, page.getPageSize());
    mav.addObject("subTopicList", subTopicList);
    mav.addObject("pushRecordList", pushRecordList);
    mav.addObject("totalPageNum", totalPageNum);
    mav.addObject("pageNum", pageNum);
    mav.addObject("nextPageNum", page.getNextPage());
    mav.addObject("prePageNum", page.getPrePage());
    mav.addObject("type", type);
    mav.addObject("topicid", topicid);
    mav.addObject("topic", topic);
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
  @RequestMapping(value = "/addSubTopic.do", method = RequestMethod.POST)
  public ModelAndView addSubTopic(@RequestParam("parentTopicid")
  String parentTopicid, @RequestParam("topicName")
  String topicName, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
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
  
  /**
   * 删除子栏目
   * @param topicid
   * @param topicName
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/deleteSubTopic.do")
  public ModelAndView deleteSubTopic(@RequestParam("topicid")
  String topicid, @RequestParam("topicSelect")
  String[] topicSelect, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    try {
      topicService.batchDeleteTopics(Arrays.asList(topicSelect));
      return this.topicPage(topicid, pageNum, type);
    } catch (Exception e) {
      logger.error("删除子栏目失败，topicid=" + topicid + " user=" + user, e);
      return errorMav;
    }
  }

  /**
   * 获取栏目详细信息
   * @param topicid
   * @return
   */
  @RequestMapping(value = "/getTopicInfo.do")
  @ResponseBody
  public Topic getTopicInfo(@RequestParam("topicid")
  String topicid) {
    return topicService.getTopicInfo(topicid);
  }

  /**
   * 修改栏目
   * @param topicid
   * @param topicName
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/modifyTopic.do")
  public ModelAndView modifyTopic(@RequestParam("topicid")
  String topicid, @RequestParam("topicName")
  String topicName, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    int number = topicService.modifyTopic(topicName, topicid, user.getUserName());
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }
  
  /**
   * 增加空文章
   * @param title
   * @param subTitle
   * @param url
   * @param lspri
   * @param topicid
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/addBlankDoc.do")
  public ModelAndView addBlankDoc(@RequestParam("title")
  String title, @RequestParam(value = "subTitle", required = false)
  String subTitle, @RequestParam(value = "img", required = false)
  String img, @RequestParam("url")
  String url, @RequestParam("lspri")
  int lspri, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    int number = pushRecordService.addBlankDoc(title, subTitle, url, topicid, lspri,
        user.getUserName(), img);
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }

  /**
   * 更新推送记录权重
   * @param indexid
   * @param lspri
   * @return
   */
  @RequestMapping(value = "/updateLspri.do")
  public ModelAndView updateLspri(@RequestParam("indexid")
  int indexid, @RequestParam("lspri")
  int lspri, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    int number = pushRecordService.updateLspri(indexid, lspri, user.getUserName());
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }

  /**
   * 根据ID获取推送记录信息
   * @param indexid
   * @return
   */
  @RequestMapping(value = "/getPushRecord.do")
  @ResponseBody
  public PushRecord getPushRecord(@RequestParam("indexid")
  int indexid) {
    return pushRecordService.getPushRecordById(indexid);
  }
 
  /**
   * 修改推送记录
   * @param indexid
   * @param title
   * @param subTitle
   * @param img
   * @param url
   * @param lspri
   * @param topicid
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/modifyPushRecord.do")
  public ModelAndView modifyPushRecord(@RequestParam("indexid")
  int indexid, @RequestParam("title")
  String title, @RequestParam(value = "subTitle", required = false)
  String subTitle, @RequestParam(value = "img", required = false)
  String img, @RequestParam("url")
  String url, @RequestParam("lspri")
  int lspri, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    PushRecord pushRecord = new PushRecord();
    pushRecord.setIndexid(indexid);
    pushRecord.setTitle(title);
    pushRecord.setSubTitle(subTitle);
    pushRecord.setImg(img);
    pushRecord.setUrl(url);
    pushRecord.setLspri(lspri);
    pushRecord.setModifyUser(user.getUserName());
    pushRecord.setTopicid(topicid);
    int number = pushRecordService.updatePushRecord(pushRecord);
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }
  
  /**
   * 批量删除推送记录
   * @param indexid
   * @param topicid
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/deletePushRecord.do")
  public ModelAndView deletePushRecords(@RequestParam("pushRecordSelect")
  String[] indexid, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return errorMav;
    }
    int number = pushRecordService.deletePushRecords(Arrays.asList(indexid));
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }

  /**
   * 复制推送记录
   * @param indexid
   * @param session
   * @return
   */
  @RequestMapping(value = "/copyPushRecord.do")
  public ModelAndView copyPushRecord(@RequestParam("pushRecordSelect")
  String[] indexid, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    session.setAttribute(KeyConstant.SESSION_KEY_COPY_INDEX, indexid);
    session.setAttribute(KeyConstant.SESSION_KEY_COPY_TYPE, JCMSConstant.CopyType.copy);
    return this.topicPage(topicid, pageNum, type);
  }
  
  /**
   * 复制推送记录
   * @param indexid
   * @param session
   * @return
   */
  @RequestMapping(value = "/cutPushRecord.do")
  public ModelAndView cutPushRecord(@RequestParam("pushRecordSelect")
  String[] indexid, @RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    session.setAttribute(KeyConstant.SESSION_KEY_COPY_INDEX, indexid);
    session.setAttribute(KeyConstant.SESSION_KEY_COPY_TYPE, JCMSConstant.CopyType.cut);
    return this.topicPage(topicid, pageNum, type);
  }

  /**
   * 粘贴推送记录
   * @param topicid
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  @RequestMapping(value = "/pastePushRecord.do")
  public ModelAndView pastePushRecord(@RequestParam("topicid")
  String topicid, @RequestParam(value = "pageNum", required = false)
  int pageNum, @RequestParam(value = "type", required = false)
  int type, HttpSession session) {
    int number =0;
    JCMSConstant.CopyType copyType= (JCMSConstant.CopyType)session.getAttribute(KeyConstant.SESSION_KEY_COPY_TYPE);
    String[] indexid = (String[])session.getAttribute(KeyConstant.SESSION_KEY_COPY_INDEX);
    if(copyType.equals(JCMSConstant.CopyType.copy)){
       number  =pushRecordService.copyPushRecords(Arrays.asList(indexid), topicid);
    }else{
      number = pushRecordService.cutPushRecords(Arrays.asList(indexid), topicid);
    }
    if (number > 0) {
      return this.topicPage(topicid, pageNum, type);
    }
    return errorMav;
  }
}
