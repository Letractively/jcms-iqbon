package com.iqbon.jcms.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.ModelDAO;
import com.iqbon.jcms.dao.system.ModelLogDAO;
import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.ModelLog;

@Service
public class ModelService {

  private static final Logger logger = Logger.getLogger(ModelService.class);
  @Autowired
  private ModelDAO modelDAO;
  @Autowired
  private ModelLogDAO modelLogDAO;

  /**
   * 根据栏目获取模板列表
   * @param topicid
   * @return
   */
  public List<Model> getModelByTopic(String topicid) {
    return modelDAO.queryModelByTopic(topicid);
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
  public Model getModelInfoByModelName(String modelName) {
    return modelDAO.queryModelByModelName(modelName);
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
    return modelDAO.updateModel(model);
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
    return modelDAO.deleteModel(modelName);
  }
}