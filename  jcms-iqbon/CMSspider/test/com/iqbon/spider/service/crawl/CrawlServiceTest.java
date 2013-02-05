package com.iqbon.spider.service.crawl;

import static org.junit.Assert.fail;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iqbon.spider.dao.SourceDao;
import com.iqbon.spider.domain.Record;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.util.BeanFactory;

public class CrawlServiceTest {

  private CrawlService crawlService = (CrawlService) BeanFactory.getBean("crawlService");

  private SourceDao sourceDao = (SourceDao) BeanFactory.getBean("sourceDao");

  private String URL = "http://home.163.com/special/latest/";

  private static final Logger logger = Logger.getLogger(CrawlServiceTest.class);

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetLinkFromSource() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetContentFromLink() {
    Source source = sourceDao.querySourceByUrl(URL);
    Record record = crawlService.getContentFromLink(
        "http://home.163.com/13/0123/18/8LU3QD5700104JVC.html", source);
    logger.info(ToStringBuilder.reflectionToString(record));
  }

}
