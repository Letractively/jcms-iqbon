package com.iqbon.jcms.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.ModelDAO;
import com.iqbon.jcms.dao.system.ModelLogDAO;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.ModelLog;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.JCMSProperties;
import com.iqbon.jcms.util.NotFoundException;

@Service
public class ModelService {

  private static final Logger logger = Logger.getLogger(ModelService.class);

  private final JCMSProperties jcmsProperties = JCMSProperties.getInstance();

  @Autowired
  private ModelDAO modelDAO;
  @Autowired
  private ModelLogDAO modelLogDAO;

  @Autowired
  private VelocityService velocityService;

  /**
   * 根据栏目获取模板列表
   * @param topicid 
   *  栏目ID，空字符串""获取系统相关的模板，如文章模板
   * @return
   */
  public List<Model> getModelByTopic(String topicid, int type) {
    return modelDAO.queryModelByTopic(topicid, type);
  }

  /**
   * 创建模板
   * @param model
   * @return
   */
  public int addModel(Model model) {
    logger.info("创建模板:" + model.getModelName());
    int number = modelDAO.insertModel(model);
    modelDAO.insertModelRefresh(model);
    addModeCreateLog(model);
    return number;
  }

  /**
   * 按模板名获取模板日志
   * @param modelName
   * @return
   */
  public List<ModelLog> getModelLogsByModelName(String modelName) {
    return modelLogDAO.queryModelLogsByModelName(modelName);
  }

  /**
   * 模板创建日志
   * @param model
   * @return
   */
  private int addModeCreateLog(Model model) {
    ModelLog modelLog = new ModelLog();
    if (model.getStatus() == Model.modelStatus.unPublish.ordinal()) {
      modelLog.setContent(ModelLog.MODEL_ADD_COMMON);
    } else if (model.getStatus() == Model.modelStatus.publish.ordinal()) {
      modelLog.setContent(ModelLog.MODEL_PUBLISH_COMMON);
    }
    modelLog.setModelContent(model.getContent());
    modelLog.setModelName(model.getModelName());
    modelLog.setUserName(model.getModifyUser());
    int logNumber = modelLogDAO.insertModelLog(modelLog);
    if (logNumber <= 0) {
      logger.error("插入模板日志失败");
    }
    return logNumber;
  }

  /**
   * 根据模板名获取模板信息
   * @param modelName
   * @return
   */
  public Model getModelInfoByModelName(String modelName) throws NotFoundException {
    Model model = modelDAO.queryModelByModelName(modelName);
    if (model.getDelete() == 1) {
      throw new NotFoundException();
    }
    return model;
  }

  /**
   * 更新模板信息
   * @param model
   * @return
   */
  public int modifyModel(Model model) {
    ModelLog modelLog = new ModelLog();
    if (model.getStatus() == Model.modelStatus.unPublish.ordinal()) {
      modelLog.setContent(ModelLog.MODEL_MODIFY_COMMON);
    } else if (model.getStatus() == Model.modelStatus.publish.ordinal()) {
      modelLog.setContent(ModelLog.MODEL_PUBLISH_COMMON);
    }
    modelLog.setModelContent(model.getContent());
    modelLog.setModelName(model.getModelName());
    modelLog.setUserName(model.getModifyUser());
    int logNumber = modelLogDAO.insertModelLog(modelLog);
    if (logNumber <= 0) {
      logger.error("插入模板日志失败");
    }
    int updateNum = modelDAO.updateModel(model);
    if (updateNum <= 0) {
      logger.error("修改文章失败");
      return updateNum;
    }
    return modelDAO.updateModelRefresh(model);
  }

  /**
   * 删除模板
   * @param modelName
   * @return
   */
  public int deleteModel(String modelName, String userName) {
    ModelLog modelLog = new ModelLog();
    modelLog.setContent(ModelLog.MODEL_DELETE_COMMON);
    modelLog.setModelContent("");
    modelLog.setModelName(modelName);
    modelLog.setUserName(userName);
    int logNumber = modelLogDAO.insertModelLog(modelLog);
    if (logNumber <= 0) {
      logger.error("插入模板日志失败");
    }
    Model model = modelDAO.queryModelByModelName(modelName);
    int delNum = modelDAO.deleteModel(modelName);
    if (delNum > 0) {
      model.setTimeout(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
      modelDAO.updateModelRefresh(model);
    }
    return delNum;
  }

  /**
   * 批量删除模板
   * @param modelNames
   * @param userName
   * @return
   */
  public int deleteModels(List<String> modelNames, String userName) {
    int num = 0;
    for (String modelName : modelNames) {
      num += deleteModel(modelName, userName);
    }
    return num;
  }

  /**
   * 发布模板内容
   * @param model
   * @throws IOException 
   */
  public void publishModelContent(Model model) throws IOException {
    String output = velocityService.parse(model.getContent());
    File file = JCMSConstant.createModelOutputFile(model.getUrl());
    if (file == null) {
      throw new IOException("获取模板uri出错");
    }
    String encoding = jcmsProperties.getOutFileCoding();
    FileUtils.write(file, output, encoding);
  }

  /**
   * 根据刷新频率获取需要刷新的模板名列表
   * @param fresh
   */
  public List<String> getNeedPublishModelByRefresh(String refresh) {
    List<String> list = modelDAO.queryModelIdByRefresh(refresh);
    return list;
  }

}
