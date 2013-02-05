package com.iqbon.spider.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.iqbon.spider.dao.RecordDao;
import com.iqbon.spider.domain.Record;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.util.BeanFactory;

/**
 * 多线程抓取页面任务
 * @author hp
 *
 */
public class CrawlThreadJob extends Thread {

  private final static Logger logger = Logger.getLogger(CrawlThreadJob.class);

  private CrawlService crawlService;
  private Source source;
  private RecordDao recordDao;

  public CrawlThreadJob(Source source) {
    this.source = source;
    this.crawlService = (CrawlService) BeanFactory.getBean("crawlService");
    this.recordDao = (RecordDao) BeanFactory.getBean("recordDao");
  }


  public void run() {
    List<Record> records = new ArrayList<Record>();
    List<String> links = crawlService.getLinkFromSource(source);
    for (String url : links) {
      Record record = crawlService.getContentFromLink(url, source);
      if (record != null) {
        records.add(record);
      }
    }
    recordDao.insertRecordList(records);
  }


}
