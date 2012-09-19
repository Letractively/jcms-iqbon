package com.iqbon.jcms.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.DocDAO;
import com.iqbon.jcms.dao.business.PushRecordDAO;
import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.JCMSProperties;

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

  /**
   * 添加文章
   * @param doc
   * @return
   */
  public int addDoc(Doc doc) {
    return docDAO.insertDoc(doc);
  }

  /**
   * 修改文章
   * @param doc
   * @return
   */
  public int modifyDoc(Doc doc) {
    return docDAO.updateDoc(doc);
  }

  /**
   * 根据文章ID获取文章信息
   * @param docid
   * @return
   */
  public Doc getDocById(String docid) {
    return docDAO.queryDocById(docid);
  }

  /**
   * 批量删除文章
   * @param idList
   * @return
   */
  public int deleteDoc(String docid) {
    int deleteNum = docDAO.deleteDoc(docid);
    if (deleteNum > 0) {
      int deletePush = pushRecordDAO.deletePushRecordByDocid(docid);
      if (deletePush <= 0) {
        logger.error("删除文章" + docid + "推送记录失败 ");
        return 0;
      }
    } else {
      logger.error("删除文章" + docid + "失败");
    }
      return deleteNum;
  }

  /**
   * 发布文章
   * @param docid
   * @return
   * @throws IOException
   */
  public String publishDoc(String docid) throws IOException {
    Doc doc = docDAO.queryDocById(docid);
    if(doc==null){
      logger.error("文章" + docid + "不存在");
      return null;
    }
    String modelName = doc.getModelName();
    Model model = modelService.getModelInfoByModelName(modelName);
    if (model == null) {
      logger.error("文章模板" + modelName + "不存在");
      return null;
    }
    String docContent = velocityService.parseDoc(model.getContent(), doc);
    String encoding = jcmsProperties.getOutFileCoding();
    File file = JCMSConstant.createDocOutputFile(doc.getUrl());
    if (file == null) {
      throw new IOException("获取模板uri出错");
    }
    FileUtils.write(file, docContent, encoding);
    return docContent;
  }
}
