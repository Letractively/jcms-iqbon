package com.iqbon.jcms.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.DocDAO;
import com.iqbon.jcms.dao.business.PushRecordDAO;
import com.iqbon.jcms.dao.system.DocLogDAO;
import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.DocLog;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.JCMSProperties;
import com.iqbon.jcms.util.NotFoundException;

@Service
public class DocService {

  private static final Logger logger = Logger.getLogger(DocService.class);

  private final JCMSProperties jcmsProperties = JCMSProperties.getInstance();

  @Autowired
  private DocDAO docDAO;

  @Autowired
  private PushRecordDAO pushRecordDAO;

  @Autowired
  private VelocityService velocityService;

  @Autowired
  private ModelService modelService;

  @Autowired
  private DocLogDAO docLogDAO;

  /**
   * 添加文章
   * @param doc
   * @return
   */
  public int addDoc(Doc doc) {
    int insertNum = docDAO.insertDoc(doc);
    DocLog docLog = new DocLog();
    docLog.setDocid(doc.getDocid());
    docLog.setContent(DocLog.DOC_ADD_COMMON);
    docLog.setTime(new Date());
    docLog.setUserName(doc.getLastModify());
    return insertNum;
  }

  /**
   * 修改文章
   * @param doc
   * @return
   */
  public int modifyDoc(Doc doc) {
    int modifyNum = docDAO.updateDoc(doc);
    DocLog docLog = new DocLog();
    docLog.setContent(DocLog.DOC_MODIFY_COMMON);
    docLog.setTime(new Date());
    docLog.setUserName(doc.getLastModify());
    return modifyNum;
  }

  /**
   * 根据文章ID获取文章信息
   * @param docid
   * @return
   * @throws NotFoundException 
   */
  public Doc getDocById(String docid) throws NotFoundException {
    Doc doc = docDAO.queryDocById(docid);
    if (doc.getDelete() == 1) {
      throw new NotFoundException();
    }
    return doc;
  }

  /**
   * 删除文章
   * @param idList
   * @return
   */
  public int deleteDoc(String docid, String userName) {
    int deleteNum = docDAO.deleteDoc(docid);
    if (deleteNum > 0) {
      int deletePush = pushRecordDAO.deletePushRecordByDocid(docid);
      if (deletePush <= 0) {
        logger.error("删除文章" + docid + "推送记录失败 ");
        return 0;
      } else {
        DocLog docLog = new DocLog();
        docLog.setContent(DocLog.DOC_DELETE_COMMON);
        docLog.setTime(new Date());
        docLog.setUserName(userName);
      }
    } else {
      logger.error("删除文章" + docid + "失败");
    }
      return deleteNum;
  }

  /**
   * 发布文章
   * @param docid 不能为空
   * @param indexid  可以为空
   * @return
   * @throws IOException
   * @throws NotFoundException
   */
  public String publishDoc(String docid, String indexid) throws IOException, NotFoundException {
    Doc doc = docDAO.queryDocById(docid);
    if(doc==null){
      logger.error("文章" + docid + "不存在");
      return null;
    }

    PushRecord pushRecord = new PushRecord();
    if (StringUtils.isNotBlank(indexid)) {
      pushRecord = pushRecordDAO.queryPushRecordById(indexid);
    } else {//如果没有传indexid，获取文章的第一个推送记录
      List<PushRecord> pushList = pushRecordDAO.queryPushRecordByDocid(docid);
      if (CollectionUtils.isNotEmpty(pushList)) {
        pushRecord = pushList.get(0);
      }
    }

    String modelName = doc.getModelName();
    Model model = modelService.getModelInfoByModelName(modelName);
    if (model == null) {
      logger.error("文章模板" + modelName + "不存在");
      return null;
    }
    String docContent = velocityService.parseDoc(model.getContent(), doc, pushRecord);
    String encoding = jcmsProperties.getOutFileCoding();
    File file = JCMSConstant.createDocOutputFile(doc.getUrl());
    if (file == null) {
      throw new IOException("获取模板uri出错");
    }
    FileUtils.write(file, docContent, encoding);

    //如果文章不是发布状态，设置文章状态
    setDocStatus(docid, Doc.docStatus.publish);

    //写日志
    DocLog docLog = new DocLog();
    docLog.setContent(DocLog.DOC_PUBLISH_COMMON);
    docLog.setTime(new Date());
    docLog.setUserName(doc.getLastModify());
    return docContent;
  }

  /**
   * 获取指定文章的操作日志
   * @param docid
   * @return
   */
  public List<DocLog> getDocLogByDocid(String docid) {
    return docLogDAO.queryDocLogByDocid(docid);
  }

  /**
   * 更新文章的状态
   * @param status
   */
  public void setDocStatus(String docid, Doc.docStatus status) {
    Doc doc = new Doc();
    doc.setDocid(docid);
    doc.setStatus(status.ordinal());
    docDAO.updateDocStatus(doc);
  }
}
