package com.iqbon.jcms.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.TopicDAO;
import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.util.JCMSVelocityContext;
import com.iqbon.jcms.util.VelocityTool;

@Service
public class VelocityService {

  private static final Logger logger = Logger.getLogger(VelocityService.class);
  @Autowired
  private VelocityTool tool;

  @Autowired
  private TopicDAO topicDao;

  /**
   * 构建器，从veloctiy.properties读入配置，初始化Velocity引擎
   */
  public VelocityService() {
    Properties props = new Properties();
    try {
      InputStream rs = getClass().getResourceAsStream("/velocity.properties");
      props.load(rs);
    } catch (IOException e) {
      logger.error("初始化Velocity引擎错误：" + e.getMessage());
      throw new RuntimeException(e);
    }
    try {
      Velocity.init(props);
    } catch (Exception e) {
      logger.error("初始化Velocity引擎错误：" + e.getMessage());
      throw new RuntimeException(e);
    }
  }
  
  /**
   * 解析velocity模板
   * @param templateName
   * @param context
   * @return
   * @throws ResourceNotFoundException
   * @throws ParseErrorException
   */
  public String parse(String content)
      throws ResourceNotFoundException, ParseErrorException {
    if (StringUtils.isEmpty(content)) {
      throw new ParseErrorException("输入内容为空");
    }
    StringWriter writer = new StringWriter();
    JCMSVelocityContext context = JCMSVelocityContext.getInstance();
    context.put("tool", tool);
    try {
      Velocity.evaluate(context, writer, "VMMessage", content);
      writer.flush();
      String result = writer.toString();
      writer.close();
      return result;
    } catch (MethodInvocationException e) {
      logger.error("解析模板，方法调用出错：", e);
      throw new RuntimeException(e);
    } catch (IOException e) {
      logger.error("解析模板，生成文件出错：", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 解析文章模板
   * @param content
   * @param doc
   * @return
   * @throws ResourceNotFoundException
   * @throws ParseErrorException
   */
  public String parseDoc(String content, Doc doc, PushRecord pushRecord)
      throws ResourceNotFoundException,
      ParseErrorException {
    if (StringUtils.isEmpty(content)) {
      throw new ParseErrorException("输入内容为空");
    }
    StringWriter writer = new StringWriter();
    JCMSVelocityContext context = JCMSVelocityContext.getInstance();
    //插入对象
    context.put("doc", doc);
    context.put("pushRecord", pushRecord);
    context.put("tool", tool);
    if (pushRecord != null && StringUtils.isNotBlank(pushRecord.getTopicid())) {
      Topic topic = topicDao.queryTopicById(pushRecord.getTopicid());
      context.put("topic", topic);
    }

    try {
      Velocity.evaluate(context, writer, "VMMessage", content);
      writer.flush();
      String result = writer.toString();
      writer.close();
      return result;
    } catch (MethodInvocationException e) {
      logger.error("解析模板出错：" + e.getMessage());
      throw new RuntimeException(e);
    } catch (IOException e) {
      logger.error("解析模板出错：" + e.getMessage());
      throw new RuntimeException(e);
    }
  }


}
