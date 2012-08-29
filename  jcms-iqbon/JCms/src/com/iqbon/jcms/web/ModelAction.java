package com.iqbon.jcms.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.ModelLog;
import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.CodeService;
import com.iqbon.jcms.service.ModelService;
import com.iqbon.jcms.service.PushRecordService;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/model")
public class ModelAction extends JCMSAction {

  private final Logger logger = Logger.getLogger(ModelAction.class);

  @Autowired
  private ModelService modelService;

  @Autowired
  private CodeService codeService;

  @Autowired
  private PushRecordService pushRecordService;

  /**
   * 显示添加或修改模板页面
   * @return
   */
  @RequestMapping(value = "/addModifyModelPage.do")
  public ModelAndView showAddModel(@RequestParam(value = "modelName", required = false)
  String modelName, @RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, @RequestParam(value = "modelType", required = false)
  int modelType) {
    ModelAndView mav = new ModelAndView();
    Code refresh = codeService.getCodeGroupInfo(KeyConstant.CODE_MODEL_REFRESH);
    if (refresh == null) {
      logger.error("系统码：" + KeyConstant.CODE_MODEL_REFRESH + "为空");
      errorMav.setErrorInfo("系统码：" + KeyConstant.CODE_MODEL_REFRESH + "为空，请联系管理员添加");
      return errorMav;
    }
    List<Code> refreshList = codeService.getSubCodeByGroupAndParent(refresh.getGroupName(),
        refresh.getGroupName());
    Code suffix = codeService.getCodeGroupInfo(KeyConstant.CODE_MODEL_SUFFIX);
    if (suffix == null) {
      logger.error("系统码：" + KeyConstant.CODE_MODEL_SUFFIX + "为空");
      errorMav.setErrorInfo("系统码：" + KeyConstant.CODE_MODEL_SUFFIX + "为空，请联系管理员添加");
      return errorMav;
    }
    List<Code> suffixList = codeService.getSubCodeByGroupAndParent(suffix.getGroupName(),
        suffix.getGroupName());
    Code timeout = codeService.getCodeGroupInfo(KeyConstant.CODE_MODEL_TIMEOUT);
    List<Code> timeoutList = codeService.getSubCodeByGroupAndParent(timeout.getGroupName(),
        timeout.getGroupName());
    mav.addObject("refreshList", refreshList);
    mav.addObject("suffixList", suffixList);
    mav.addObject("timeoutList",timeoutList);
    mav.addObject("topicid", topicid);
    mav.addObject("type", type);
    mav.addObject("pageNum", pageNum);
    mav.addObject("model", null);
    mav.addObject("modelType", modelType);
    if (StringUtils.isNotEmpty(modelName)) {
      Model model = modelService.getModelInfoByModelName(modelName);
      List<ModelLog> modelLogList = modelService.getModelLogsByModelName(modelName);
      mav.addObject("model", model);
      mav.addObject("modelLogList", modelLogList);
    }
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "addModifyModel");
    return mav;
  }

  /**
   * 增加模板
   * @return
   */
  @RequestMapping(value = "/addModel.do")
  public String addModel(@RequestParam("modelName")
  String modelName, @RequestParam("title")
  String title, @RequestParam("suffix")
  String suffix, @RequestParam("keyword")
  String keyword, @RequestParam("content")
  String content, @RequestParam("status")
  int status, @RequestParam("refresh")
  int refresh, @RequestParam("timeout")
  int timeout, @RequestParam("modelType")
  int modelType, @RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl();
    }
    String url = JCMSConstant.createModelUrl(modelName, suffix);
    Model model = new Model();
    model.setModelName(modelName);
    model.setTitle(title);
    model.setExtname(suffix);
    model.setKeyword(keyword);
    model.setContent(content);
    model.setStatus(status);
    model.setTopicid(topicid);
    model.setRate(refresh);
    model.setType(modelType);
    Date now = new Date();
    Date timeoutDate = DateUtils.addDays(now, timeout);
    model.setTimeout(DateFormatUtils.ISO_DATETIME_FORMAT.format(timeoutDate));
    model.setAddTime(JCMSConstant.getDateString(now));
    model.setLastModify(JCMSConstant.getDateString(now));
    model.setModifyUser(user.getUserName());
    model.setUrl(url);
    int insertModel =modelService.addModel(model);
    int insertPushRecord =pushRecordService.addBlankDoc(title, null, url, topicid, 60, user.getUserName(), null);
    if(insertModel<=0){
      logger.error("插入模板失败：" + ToStringBuilder.reflectionToString(model));
      return getErrorUrl();
    }
    if(insertPushRecord<=0){
      logger.error("插入空文章失败");
      return getErrorUrl();
    }
    if(status==Model.modelStatus.publish.ordinal()){
      return "redirect:/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type;

    } else {
      return "redirect:/admin/model/addModifyModelPage.do?topicid=" + topicid + "&pageNum="
          + pageNum + "&type=" + type + "&modelName=" + modelName + "&modelType=" + modelType;
    }
  }

  /**
   * 修改模板
   * @return
   */
  @RequestMapping(value = "/modifyModel.do")
  public String modifyModel(@RequestParam("modelName")
  String modelName, @RequestParam("title")
  String title, @RequestParam("suffix")
  String suffix, @RequestParam("keyword")
  String keyword, @RequestParam("content")
  String content, @RequestParam("status")
  int status, @RequestParam("refresh")
  int refresh, @RequestParam("timeout")
  int timeout, @RequestParam("modelType")
  int modelType, @RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl();
    }
    Model model = new Model();
    model.setModelName(modelName);
    model.setTitle(title);
    model.setExtname(suffix);
    model.setKeyword(keyword);
    model.setContent(content);
    model.setStatus(status);
    model.setTopicid(topicid);
    model.setRate(refresh);
    model.setType(modelType);
    Date now = new Date();
    Date timeoutDate = DateUtils.addDays(now, timeout);
    model.setTimeout(DateFormatUtils.ISO_DATETIME_FORMAT.format(timeoutDate));
    model.setLastModify(JCMSConstant.getDateString(now));
    model.setModifyUser(user.getUserName());
    int updateModel = modelService.modifyModel(model);
    if (updateModel <= 0) {
      logger.error("插入模板失败：" + ToStringBuilder.reflectionToString(model));
      return getErrorUrl();
    }
    if (status == Model.modelStatus.publish.ordinal()) {
      return "redirect:/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type;

    } else {
      return "redirect:/admin/model/addModifyModelPage.do?topicid=" + topicid + "&pageNum="
          + pageNum + "&type=" + type + "&modelName=" + modelName + "&modelType=" + modelType;
    }
  }

  /**
   * 删除模板
   * @param modelName
   * @param topicid
   * @param pageNum
   * @param type
   * @param session
   * @return
   */
  public String deleteModel(@RequestParam("modelName")
  String modelName, @RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl();
    }
    int deleteNum = modelService.deleteModel(modelName, user.getUserName());
    if (deleteNum > 0) {
      return "redirect:/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type;
    } else {
      return getErrorUrl();
    }
  }
}