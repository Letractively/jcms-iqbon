package com.iqbon.spider.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.iqbon.spider.domain.Source;
import com.iqbon.spider.domain.SourceMatcher;

public class CrawlThreadJobTest {

  private Source source = new Source();
  private List<Source> sources = new ArrayList<Source>();
  private List<SourceMatcher> matchers = new ArrayList<SourceMatcher>();
  @Before
  public void setUp() throws Exception {
    source.setUrl("http://home.163.com/special/latest/");
    source.setDescription("test");
    source.setContentMatcher("#endText");
    source.setId(new ObjectId());
    SourceMatcher matcher = new SourceMatcher();
    matcher.setLinkParent("li.list-left");
    matcher.setDescription("×ó²àÁ¬½Ó");
    matchers.add(matcher);
    source.setMatchers(matchers);
    sources.add(source);
  }

  @Test
  public void testRun() {
    CrawlThreadJob crawlThreadJob = new CrawlThreadJob(source);
    crawlThreadJob.run();
  }

}
