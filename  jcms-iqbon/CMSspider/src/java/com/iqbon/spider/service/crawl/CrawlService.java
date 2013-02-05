package com.iqbon.spider.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import com.iqbon.spider.domain.Record;
import com.iqbon.spider.domain.Replace;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.domain.SourceMatcher;
import com.iqbon.spider.util.HttpConstant;

public class CrawlService {

  private Logger logger = Logger.getLogger(CrawlService.class);

  private PoolingClientConnectionManager poolingClientConnectionManager;
  
  private HttpContext context = new BasicHttpContext();
  
  public PoolingClientConnectionManager getPoolingClientConnectionManager() {
    return poolingClientConnectionManager;
  }

  public void setPoolingClientConnectionManager(
      PoolingClientConnectionManager poolingClientConnectionManager) {
    this.poolingClientConnectionManager = poolingClientConnectionManager;
  }

  /**
   * 从抓取源中获取文章链接
   * @return
   */
  public List<String> getLinkFromSource(Source source) {
    HttpClient httpClient = new DefaultHttpClient(poolingClientConnectionManager);
    HttpGet httpget = new HttpGet(source.getUrl());
    List<String> links = new ArrayList<String>();
    try {
      HttpResponse response = httpClient.execute(httpget, context);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == HttpConstant.HTTP_STATUS_OK) {
        String content = EntityUtils.toString(entity);
        Document document = Jsoup.parse(content);
        List<SourceMatcher> matchers = source.getMatchers();

        for (SourceMatcher matcher : matchers) {//按照每个匹配器的条件抓取内容

          String linParent = matcher.getLinkParent();
          String linkMatcher = matcher.getLinkMatcher();

          if (StringUtils.isNotEmpty(linParent)) {//如果链接父元素不为空，先找父元素
            Elements elements = document.select(linParent);
            if (elements != null && elements.size() > 0) {
              for (Element element : elements) {
                String address = getFirstLinkFromElement(element, linkMatcher);
                if (HttpConstant.URL_VALIDATOR.isValid(address)) {
                  links.add(address);
                }
              }
            }

          } else if (StringUtils.isNotEmpty(linkMatcher)) {//如果链接父元素为空，直接查找链接元素
            Elements elements = document.select(linParent);
            if (elements != null && elements.size() > 0) {
              for (Element element : elements) {
                String address = getFirstLinkFromElement(element, null);
                if (HttpConstant.URL_VALIDATOR.isValid(address)) {
                  links.add(address);
                }
              }
            }
          }
        }
      }
      EntityUtils.consume(entity);
      return links;
    } catch (Exception e) {
      logger.error("抓取数据源出错:" + source.getUrl(), e);
      return links;
    } finally {
      httpget.abort();
    }
  }

  /**
   * 从element中获取第一个链接地址
   * //获取每个链接地址,如果linkMatcher有内容，
   * //则按linkMatcher的内容来获取，没有则按a获取
   * @param element
   * @param linkMatcher
   * @return
   */
  private String getFirstLinkFromElement(Element element, String linkMatcher) {
    Elements selectLinks = null;
    if (StringUtils.isNotEmpty(linkMatcher)) {
      selectLinks = element.select(linkMatcher);

    } else {
      selectLinks = element.select(HttpConstant.A);
    }
    if (selectLinks != null && selectLinks.size() > 0) {
      return selectLinks.first().attr(HttpConstant.HREF);
    }
    return null;
  }

  /**
   * 从链接中获取文章内容
   * @param url
   * @return
   */
  public Record getContentFromLink(String url, Source source) {
    HttpClient httpClient = new DefaultHttpClient(poolingClientConnectionManager);
    HttpGet httpget = new HttpGet(url);
    HttpResponse response;
    try {
      response = httpClient.execute(httpget, context);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == HttpConstant.HTTP_STATUS_OK) {
        String content = EntityUtils.toString(entity);
        Document document = Jsoup.parse(content);
        Elements contents = document.select(source.getContentMatcher());
        if (contents != null && contents.size() > 0) {
          String recordContent = contents.first().html();
          if (StringUtils.isNotEmpty(recordContent)
              && !CollectionUtils.isEmpty(source.getReplaces())) {
            for (Replace replace : source.getReplaces()) {
              recordContent = recordContent.replaceAll(replace.getMatcher(),
                  replace.getReplacement());
            }
          }
          String sourceId = source.getId().toString();
          String title = document.title();
          return new Record(title, url, recordContent, sourceId);
        }
      }
      EntityUtils.consume(entity);
      return null;
    } catch (Exception e) {
      logger.error("抓取文章内容出错:" + url, e);
      return null;
    } finally {
      httpget.abort();
    }

  }
}
