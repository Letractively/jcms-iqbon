package com.iqbon.spider.web;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iqbon.spider.domain.Record;

public class RecordControllerTest {

  private static final Logger logger = Logger.getLogger(RecordControllerTest.class);
  private final String sourceUrl = "http://home.163.com/special/latest/";
  private final RecordController controller = new RecordController();
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetCrawlRecordBySourceId() {
    List<String> links = controller.getCrawlRecordBySourceId(sourceUrl);
    for (String link : links) {
      logger.info(link);
    }
  }

  @Test
  public void testGetCrawlContentBySourceAndUrl() {
    Record record = controller.getCrawlContentBySourceAndUrl(
        "http://home.163.com/13/0119/10/8LJ06D77001048P8.html", sourceUrl);
    logger.info(ToStringBuilder.reflectionToString(record));
  }

}
