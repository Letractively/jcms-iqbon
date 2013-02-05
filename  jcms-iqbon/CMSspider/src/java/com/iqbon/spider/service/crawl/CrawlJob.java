package com.iqbon.spider.service.crawl;

import java.util.List;

import org.apache.log4j.Logger;

import com.iqbon.spider.dao.SourceDao;
import com.iqbon.spider.domain.Source;

/**
 * 定时抓取任务
 * @author hp
 *
 */
public class CrawlJob {

  private static final Logger logger = Logger.getLogger(CrawlJob.class);

  private SourceDao sourceDao;

  public void crawl() {
    List<Source> sourceList = sourceDao.queryAll(null, null);
    for(Source source:sourceList){
      logger.info("start get source:" + source.getUrl());
      try {
      // create a thread for each URI
        CrawlThreadJob[] threads = new CrawlThreadJob[sourceList.size()];
        for (int i = 0; i < threads.length; i++) {
          threads[i] = new CrawlThreadJob(sourceList.get(i));
        }
        for (int j = 0; j < threads.length; j++) {
          threads[j].start();
        }
        for (int j = 0; j < threads.length; j++) {
          threads[j].join();
        }

      } catch (InterruptedException e) {
        logger.error("抓取线程被中断", e);
      }
    }

  }

}
