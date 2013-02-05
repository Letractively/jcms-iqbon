package com.iqbon.jcms.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.DocLog;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.DocService;
import com.iqbon.jcms.service.ModelService;
import com.iqbon.jcms.service.PushRecordService;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.util.NotFoundException;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/doc")
public class DocAction extends JCMSAction {

  private final static Logger logger = Logger.getLogger(DocAction.class);

  @Autowired
  private DocService docService;
  
  @Autowired
  private ModelService modelService;

  @Autowired
  private PushRecordService pushRecordService;

  /**
   * 显示修改添加文章页
   * @param topicid
   * @param pageNum
   * @param type
   * @return
   */
  @RequestMapping("/showAddModifyDoc.do")
  public ModelAndView showAddModifyDoc(@RequestParam(value = "topicid", required = false)
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, @RequestParam(value = "docid", required = false)
  String docid, HttpSession session) {
    ModelAndView view = new ModelAndView();
    User user = getUserFromSession(session);
    List<Model> docModelList = modelService.getModelByTopic("", Model.modelType.doc.ordinal());
    if (CollectionUtils.isNotEmpty(docModelList)) {
      view.addObject("docModelList", docModelList);
    }
    if (StringUtils.isNotEmpty(docid)) {
      try {
      Doc doc = docService.getDocById(docid);
      List<DocLog> logList = docService.getDocLogByDocid(docid);
      view.addObject("doc", doc);
      view.addObject("logList", logList);
      } catch (NotFoundException e) {
        logger.error("修改文章失败", e);
        return errorMav;
      }
    }
    view.addObject("user", user);
    view.addObject("topicid", topicid);
    view.addObject("pageNum", pageNum);
    view.addObject("type", type);
    view.setViewName(KeyConstant.ADMIN_JSP_PATH + "addModifyDoc");
    return view;
  }

  /**
   * 增加普通文章
   * @param topicid
   * @param pageNum
   * @param type
   * @param title
   * @param content
   * @param digest
   * @param reporter
   * @param keyword
   * @param session
   * @return
   */
  @RequestMapping("/addDoc.do")
  public String addDoc(@RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, @RequestParam("title")
  String title, @RequestParam("content")
  String content, @RequestParam("digest")
  String digest, @RequestParam("reporter")
  String reporter, @RequestParam("keyword")
  String keyword, @RequestParam("modelName")
  String modelName, @RequestParam("status")
  int status, HttpSession session) {
    User user = getUserFromSession(session);
    Doc doc = new Doc();
    String docid = RandomStringUtils.randomAlphanumeric(11);
    doc.setDocid(docid);
    doc.setTitle(title);
    doc.setContent(content);
    doc.setDigest(digest);
    doc.setReporter(reporter);
    doc.setModifyUser(user.getUserName());
    doc.setType(Doc.docType.normal.ordinal());
    doc.setKeyword(keyword);
    doc.setModelName(modelName);
    doc.setStatus(status);
    String url = JCMSConstant.createDocUrl(docid, Doc.docType.normal.ordinal());
    doc.setUrl(url);
    int number = docService.addDoc(doc);
    if (number > 0) {//增加推送记录
      PushRecord pushRecord = new PushRecord();
      pushRecord.setDocid(docid);
      pushRecord.setLspri(PushRecord.DEFAULT_LSPRI);
      pushRecord.setModifyUser(user.getUserName());
      pushRecord.setTitle(title);
      pushRecord.setTopicid(topicid);
      pushRecord.setType(PushRecord.PUSH_RECORD_TYPE.doc.ordinal());
      pushRecord.setUrl(url);
      number = pushRecordService.addPushRecord(pushRecord);
      if (number > 0) {
        if (status == Doc.docStatus.publish.ordinal()) {//发布文章
          try {
            docService.publishDoc(docid);
          } catch (IOException e) {
            logger.error("发布文章失败", e);
            return getErrorUrl("发布文章失败");
          } catch (NotFoundException e) {
            logger.error("发布文章失败，文章模板已不存在", e);
            return getErrorUrl("发布文章失败，文章模板已不存在");
          }
        }
        return redirect("/admin/doc/showAddModifyDoc.do?topicid=" + topicid + "&pageNum=" + pageNum
            + "&type=" + type + "&docid=" + docid);
      } else {
        return getErrorUrl("添加推送记录失败");
      }
    } else {
      return getErrorUrl("添加文章数据失败");
    }
  }
  
  /**
   * 修改文章内容
   * @param topicid
   * @param pageNum
   * @param type
   * @param title
   * @param content
   * @param digest
   * @param reporter
   * @param keyword
   * @param modelName
   * @param status
   * @param docid
   * @param url
   * @param session
   * @return
   */
  @RequestMapping("/modifyDoc.do")
  public String modifyDoc(@RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, @RequestParam("title")
  String title, @RequestParam("content")
  String content, @RequestParam("digest")
  String digest, @RequestParam("reporter")
  String reporter, @RequestParam("keyword")
  String keyword, @RequestParam("modelName")
  String modelName, @RequestParam("status")
  int status, @RequestParam("docid")
  String docid, @RequestParam("url")
  String url, HttpSession session) {
    User user = getUserFromSession(session);
    Doc doc = new Doc();
    doc.setDocid(docid);
    doc.setTitle(title);
    doc.setContent(content);
    doc.setDigest(digest);
    doc.setReporter(reporter);
    doc.setModifyUser(user.getUserName());
    doc.setType(Doc.docType.normal.ordinal());
    doc.setKeyword(keyword);
    doc.setModelName(modelName);
    doc.setStatus(status);
    doc.setUrl(url);
    int number = docService.modifyDoc(doc);
    if (number > 0) {
      if (status == Doc.docStatus.publish.ordinal()) {//发布文章
        try {
          docService.publishDoc(docid);
        } catch (IOException e) {
          logger.error("发布文章失败", e);
          return getErrorUrl("发布文章失败");
        } catch (NotFoundException e) {
          logger.error("发布文章失败，文章模板已不存在", e);
          return getErrorUrl("发布文章失败，文章模板已不存在");
        }
      }
      return redirect("/admin/doc/showAddModifyDoc.do?topicid=" + topicid + "&pageNum=" + pageNum
          + "&type=" + type + "&docid=" + docid);
    } else {
      return getErrorUrl("修改文章数据失败");
    }
  }

  /**
   * 删除文章
   * @param topicid
   * @param pageNum
   * @param type
   * @param docid
   * @return
   */
  @RequestMapping("/deleteDoc.do")
  public String deleteDoc(@RequestParam("topicid")
  String topicid, @RequestParam("pageNum")
  int pageNum, @RequestParam("type")
  int type, @RequestParam("docid")
  String docid, HttpSession session) {
    User user = getUserFromSession(session);
    int number = docService.deleteDoc(docid, user.getUserName());
    if (number > 0) {
      return redirect("/admin/topic/topicPage.do?pageNum=" + pageNum + "&type=" + type
          + "&topicid=" + topicid);
    } else {
      return getErrorUrl("删除文章失败");
    }
  }
}
