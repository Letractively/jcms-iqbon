package com.iqbon.spider.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import com.iqbon.spider.dao.SourceDao;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.domain.SourceMatcher;
import com.iqbon.spider.util.BeanFactory;
import com.iqbon.spider.util.Page;

public class SourceController extends BaseController {

  private final SourceDao sourceDao = (SourceDao) BeanFactory.getBean("sourceDao");
  /**
   * url检验器
   */
  private final static UrlValidator URL_VALIDATOR = UrlValidator.getInstance();
  
  /**
   * 获取所有采集源
   * @return
   */
  public List<Source> getAllSource(String keyword, int pageNum,int totalNum) {
    Page page  =  new Page(totalNum, pageNum);
    return sourceDao.queryAll(keyword, page);
  }

  /**
   * 增加采集源
   * @param url
   * @param description
   * @param contentMatcher
   * @param matchers
   * @return
   */
  public String addSource(String url, String description, String contentMatcher,
      List<SourceMatcher> matchers) {
    if (!URL_VALIDATOR.isValid(url)) {
      return "输入的URL不合法";
    }
    if (StringUtils.isEmpty(contentMatcher)) {
      return "内容匹配规则不能为空";
    }
    Source duplicate = sourceDao.querySourceByUrl(url);
    if (duplicate != null) {
      return "已经存在重复的采集数据源" + url;
    }
    Source source = new Source();
    source.setUrl(url);
    source.setContentMatcher(contentMatcher);
    source.setDescription(description);
    source.setMatchers(matchers);
    sourceDao.insertOne(source);
    return null;
  }

  /**
   * 获取抓取数据源的总数
   * @param keyword
   * @return
   */
  public int getSourceNum(String keyword) {
    return sourceDao.querySourceNum();
  }
}
