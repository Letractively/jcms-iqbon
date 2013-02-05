package com.iqbon.spider.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.iqbon.spider.domain.Replace;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.domain.SourceMatcher;
import com.iqbon.spider.util.BeanFactory;

public class SourceDaoTest {

  private static final Logger logger = Logger.getLogger(SourceDaoTest.class);

  private SourceDao sourceDao;

  private Source source = new Source();

  private List<SourceMatcher> matchers = new ArrayList<SourceMatcher>();

  private List<Replace> replaces = new ArrayList<Replace>();

  @Before
  public void setUp() throws Exception {
    source.setUrl("http://home.163.com/special/latest/");
    source.setDescription("test");
    source.setContentMatcher("#js-epContent");
    SourceMatcher matcher = new SourceMatcher();
    matcher.setLinkParent("li[class=list-left]");
    matcher.setDescription("左侧连接");
    matchers.add(matcher);
    source.setMatchers(matchers);
    source.setReplaces(replaces);
    sourceDao = (SourceDao) BeanFactory.getBean("sourceDao");
  }


  @Test
  public void testInsertOne() {
    sourceDao.insertOne(source);
  }

  @Test
  public void testQuerySourceByUrl() {
    Source s = sourceDao.querySourceByUrl(source.getUrl());
    logger.info(ToStringBuilder.reflectionToString(s));

  }

  @Test
  public void testQueryAll() {
    List<Source> list = sourceDao.queryAll(null, null);
    logger.info(list.size());
    for (Source s : list) {
      logger.info(ToStringBuilder.reflectionToString(s));
    }
  }

  @Test
  public void testUpdateSource() {
    Source s = sourceDao.querySourceByUrl(source.getUrl());
    SourceMatcher matcher = new SourceMatcher();
    matcher.setLinkParent("li[class=list-right]");
    matcher.setDescription("右侧连接");
    Replace replace = new Replace();
    replace.setMatcher("<div class=\"gg200x300\">[\\s\\S]*?</div>");
    replace.setReplacement("");
    Replace replace2 = new Replace();
    replace2.setMatcher("<a [\\s\\S]*?>");
    replace2.setReplacement("");
    Replace replace3 = new Replace();
    replace3.setMatcher("</a>");
    replace3.setReplacement("");
    List<Replace> replaces = new ArrayList<Replace>();
    List<SourceMatcher> matchers = new ArrayList<SourceMatcher>();
    matchers.add(matcher);
    replaces.add(replace);
    replaces.add(replace2);
    replaces.add(replace3);
    s.setContentMatcher("#endText");
    s.setMatchers(matchers);
    s.setReplaces(replaces);
    s.setDescription("after test");
    sourceDao.updateSource(s);
  }

}
