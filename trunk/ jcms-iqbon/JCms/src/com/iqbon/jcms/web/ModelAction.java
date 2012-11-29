package com.iqbon.jcms.web;

import java.io.IOException;
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
import com.iqbon.jcms.util.NotFoundException;
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
  String modelName, @RequestParam(value = "topicid", required = false)
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
      try {
      Model model = modelService.getModelInfoByModelName(modelName);
      List<ModelLog> modelLogList = modelService.getModelLogsByModelName(modelName);
      mav.addObject("model", model);
      mav.addObject("modelLogList", modelLogList);
      } catch (NotFoundException e) {
        logger.error("修改模板失败", e);
        return errorMav;
      }
    }
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "addModifyModel");
    return mav;
  }

  /**
   * 显示文章模板列表
   * @return
   */
  @RequestMapping(value = "/showDocModelList.do")
  public ModelAndView showDocModel() {
    ModelAndView mav = new ModelAndView();
    List<Model> modelList = modelService.getModelByTopic("", Model.modelType.doc.ordinal());
    mav.addObject("modelList", modelList);
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "docsModelPage");
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
  int modelType, @RequestParam(value = "topicid", required = false)
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl(UNLOGIN_ERROR_MESSAGE);
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
      return getErrorUrl("插入模板失败");
    }
    if(insertPushRecord<=0){
      logger.error("插入空文章失败");
      return getErrorUrl("插入空文章失败");
    }
    if (status == Model.modelStatus.publish.ordinal()) {//发布模板
      try {
        modelService.publishModelContent(model);
      } catch (IOException e) {
        logger.error("输出模板页面出错，modelName=" + model.getModelName(), e);
        return getErrorUrl("插入空文章失败");
      }
      return redirect("/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type);

    } else {
      return redirect("/admin/model/addModifyModelPage.do?topicid=" + topicid + "&pageNum="
          + pageNum + "&type=" + type + "&modelName=" + modelName + "&modelType=" + modelType);
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
  String content, @RequestParam("url")
  String url, @RequestParam("status")
  int status, @RequestParam("refresh")
  int refresh, @RequestParam("timeout")
  int timeout, @RequestParam("modelType")
  int modelType, @RequestParam(value = "topicid", required = false)
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl(UNLOGIN_ERROR_MESSAGE);
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
    model.setUrl(url);
    Date now = new Date();
    Date timeoutDate = DateUtils.addDays(now, timeout);
    model.setTimeout(DateFormatUtils.ISO_DATETIME_FORMAT.format(timeoutDate));
    model.setLastModify(JCMSConstant.getDateString(now));
    model.setModifyUser(user.getUserName());
    int updateModel = modelService.modifyModel(model);
    if (updateModel <= 0) {
      logger.error("插入模板失败：" + ToStringBuilder.reflectionToString(model));
      return getErrorUrl("插入模板失败");
    }
    if (status == Model.modelStatus.publish.ordinal()) {
      try {
        modelService.publishModelContent(model);
      } catch (IOException e) {
        logger.error("输出模板页面出错，modelName=" + model.getModelName(), e);
        return getErrorUrl("输出模板页面出错");
      }
      return redirect("/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type);

    } else {
      return redirect("/admin/model/addModifyModelPage.do?topicid=" + topicid + "&pageNum="
          + pageNum + "&type=" + type + "&modelName=" + modelName + "&modelType=" + modelType);
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
  @RequestMapping(value = "/deleteModel.do")
  public String deleteModel(@RequestParam("modelName")
  String modelName, @RequestParam(value = "topicid", required = false)
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, HttpSession session) {
    User user = getUserFromSession(session);
    if (user == null) {
      return getErrorUrl(UNLOGIN_ERROR_MESSAGE);
    }
    int deleteNum = modelService.deleteModel(modelName, user.getUserName());
    if (deleteNum > 0) {
      return "redirect:/admin/topic/topicPage.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type;
    } else {
      return getErrorUrl("删除文章失败");
    }
  }
}
