package com.iqbon.jcms.web.api.interf;

import java.util.Map;

public interface DocApi {

  /**
   * 增加普通文章
   * @param topicid 栏目ID
   * @param type 文章类型 0 普通文章，1图片文章，2视频文章
   * @param title 文章标题
   * @param content 文章内容
   * @param digest 描述
   * @param reporter 作者
   * @param keyword 关键字
   * @param modelName 模板名称
   * @param clientName 用户名
   * @param password 密码
   * @return
   */
  public Map<String, String> addDoc(String topicid, int type, String title, String content,
      String digest, String reporter, String keyword, String modelName, String clientName,
      String password);
}
