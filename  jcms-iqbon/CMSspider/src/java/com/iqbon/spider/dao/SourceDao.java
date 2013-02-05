package com.iqbon.spider.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.iqbon.spider.domain.Source;
import com.iqbon.spider.util.Page;
import com.mongodb.Mongo;

public class SourceDao extends BasicDAO<Source, ObjectId> {

  private Datastore datastore;

  protected SourceDao(Mongo mongo, Morphia morphia, String dbName) {
    super(mongo, morphia, dbName);
    datastore = morphia.createDatastore(mongo, dbName);
    datastore.ensureIndexes();
  }

  /**
   * 插入一条抓取源记录
   * @param source
   */
  public void insertOne(Source source) {
    datastore.save(source);
  }
  
  /**
   * 根据URL获取抓取源
   * @param url
   * @return
   */
  public Source querySourceByUrl(String url) {
    return datastore.find(Source.class).field("url").equal(url).get();
  }

  /**
   * 获取所有的抓取源
   * @return
   */
  public List<Source> queryAll(String keyword, Page page) {
    Query<Source> query=  datastore.createQuery(Source.class);
    if(StringUtils.isNotEmpty(keyword)){
      query.field("description").contains(keyword);
    }
    if(page!=null){
      query.offset(page.getCurrentPage() * page.getPageSize()).limit(page.getPageSize());
    }
    return query.asList();
  }

  /**
   * 更新抓取数据源
   * @param source
   */
  public void updateSource(Source source){
    Query<Source> query  = datastore.createQuery(Source.class).field("id").equal(source.getId());
    UpdateOperations<Source> update = datastore.createUpdateOperations(Source.class)
        .set("description", source.getDescription()).set("matchers", source.getMatchers())
        .set("contentMatcher", source.getContentMatcher()).set("replaces", source.getReplaces());
    datastore.findAndModify(query, update);
  }

  /**
   * 获取抓取数据源的总数
   * @return
   */
  public int querySourceNum() {
    return Long.valueOf(datastore.getCount(Source.class)).intValue();
  }
}
