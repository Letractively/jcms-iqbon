package com.iqbon.spider.web;

import java.util.List;

import org.apache.log4j.Logger;

import com.iqbon.spider.dao.RecordDao;
import com.iqbon.spider.dao.SourceDao;
import com.iqbon.spider.domain.Record;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.service.crawl.CrawlService;
import com.iqbon.spider.util.BeanFactory;

public class RecordController extends BaseController {

  private final SourceDao sourceDao = (SourceDao) BeanFactory.getBean("sourceDao");

  private final CrawlService crawlService = (CrawlService) BeanFactory.getBean("crawlService");
  
  private final static Logger logger = Logger.getLogger(RecordDao.class);

  /**
   * 根据url获取该抓取数据源所能匹配的链接
   * @param sourceId
   * @return
   */
  public List<String> getCrawlRecordBySourceId(String url) {
    Source source = sourceDao.querySourceByUrl(url);
    return crawlService.getLinkFromSource(source);
  }

  /**
   * 根据url和采集数据源的URL获取所匹配的文章正文
   * @param url
   * @param sourceUrl
   * @return
   */
  public Record getCrawlContentBySourceAndUrl(String url, String sourceUrl) {
    Source source = sourceDao.querySourceByUrl(sourceUrl);
    return crawlService.getContentFromLink(url, source);
  }
}
