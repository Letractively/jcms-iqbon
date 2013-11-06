package com.iqbon.jcms.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.service.PushRecordService;

/**
 * 模板工具类
 * @author hp
 *
 */
public class VelocityTool {

  private static final Logger logger = Logger.getLogger(VelocityTool.class);

  @Autowired
  private PushRecordService pushRecordService;

  /**
   * 获取指定栏目的推送列表
   * @param topicid 栏目ID
   * @param minLspri 最小权重 默认为0(0-100范围)
   * @param maxLspri 最大权重 默认为100(0-100范围)
   * @param isImg 是否有图片 可以为空
   * @param startTime 时间范围，最后修改时间在startTime之后，可以为空，格式yyyy-MM-dd
   * @param endTime 时间范围，最后修改时间在startTime之前，可以为空，格式yyyy-MM-dd
   * @param limit 获取多少条记录
   * @return
   */
  public List<PushRecord> getPushRecordByTopic(String topicid, int minLspri, int maxLspri,
      Boolean isImg, String startTime, String endTime, int limit) throws Exception {
    DateValidator dateValidator = DateValidator.getInstance();
    if (StringUtils.isEmpty(topicid)) {
      throw new Exception("topicid为空");
    }
    if (minLspri > 100 || minLspri < 0) {
      throw new Exception("minLspri只能在0-100之间");
    }
    if (maxLspri > 100 || maxLspri < 0) {
      throw new Exception("maxLspri只能在0-100之间");
    }
    if (limit <= 0) {
      throw new Exception("limit不能小于0");
    }
    if (!dateValidator.isValid(startTime, "yyyy-MM-dd") && StringUtils.isNotEmpty(startTime)) {
      throw new Exception("startTime需要格式yyyy-MM-dd");
    }
    if (!dateValidator.isValid(endTime, "yyyy-MM-dd") && StringUtils.isNotEmpty(endTime)) {
      throw new Exception("endTime需要格式yyyy-MM-dd");
    }
    return pushRecordService.getPushRecordByTopic(topicid, minLspri, maxLspri, isImg, startTime,
        endTime, limit);
  }

  /**
   * 获取指定栏目的推送列表
   * @param topicid 栏目ID
   * @param limit 获取多少条记录
   * @return
   * @throws Exception 
   */
  public List<PushRecord> getPushRecordByTopic(String topicid, int limit) throws Exception {
    return getPushRecordByTopic(topicid, 0, 100, null, null, null, limit);
  }

  /**
   * 获取指定栏目的推送列表
   * @param topicid 栏目ID
   * @param minLspri 最小权重 默认为0(0-100范围)
   * @param maxLspri 最大权重 默认为100(0-100范围)
   * @param limit 获取多少条记录
   * @return
   * @throws Exception
   */
  public List<PushRecord> getPushRecordByTopic(String topicid, int minLspri, int maxLspri, int limit)
      throws Exception {
    return getPushRecordByTopic(topicid, minLspri, maxLspri, null, null, null, limit);
  }
  
  /**
   * 是否空列表
   * @param <T>
   * @return
   */
  public <T> boolean isEmptyList(List<T> list) {
    if (list == null || list.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 获取前一条推送记录
   * @param indexid
   * @return
   */
  public PushRecord lastPushRecord(String indexid, int minLspri, int maxLspri, Boolean isImg) {
    return pushRecordService.getLastPushRecord(indexid, minLspri, maxLspri, isImg);
  }

  /**
   * 获取下一条推送记录
   * @param indexid
   * @param minLspri
   * @param maxLspri
   * @param isImg
   * @return
   */
  public PushRecord nextPushRecord(String indexid, int minLspri, int maxLspri, Boolean isImg) {
    return pushRecordService.getNextPushRecord(indexid, minLspri, maxLspri, isImg);
  }

  /**
   * 把字符串分隔成为list
   * @param str
   * @param separatorChars
   * @return
   */
  public List<String> split2List(String str, String separatorChars) {
    if (StringUtils.isNotEmpty(str) && StringUtils.isNotEmpty(separatorChars)) {
      String[] array = StringUtils.split(str, separatorChars);
      if (array == null || array.length == 0) {
        return null;
      } else {
        return Arrays.asList(array);
      }
    } else {
      return null;
    }
  }

  /**
   * 倒序一个列表
   * @param list
   * @return
   */
  public <T> List<T> reverseOrderList(List<T> list) {

    if (CollectionUtils.isEmpty(list)) {
      return list;
    }
    List<T> returnList = new ArrayList<T>(list.size());
    int index = list.size() - 1;
    for (int i = 0; i < list.size(); i++) {
      returnList.add(list.get(index));
      index--;
    }
    return returnList;
  }
}
