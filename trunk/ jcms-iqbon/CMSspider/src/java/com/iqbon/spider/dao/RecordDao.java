package com.iqbon.spider.dao;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.iqbon.spider.domain.Record;
import com.mongodb.Mongo;

public class RecordDao extends BasicDAO<Record, ObjectId> {

  private Datastore datastore;

  protected RecordDao(Mongo mongo, Morphia morphia, String dbName) {
    super(mongo, morphia, dbName);
    datastore = morphia.createDatastore(mongo, dbName);
    datastore.ensureIndexes();
  }

  /**
   *  批量添加抓取记录
   * @param list
   */
  public void insertRecordList(List<Record> list) {
    datastore.save(list);
  }

  /**
   * 根据url查找抓取记录
   * @param url
   * @return
   */
  public Record queryRecordByUrl(String url) {
    return datastore.find(Record.class).field("url").equal(url).get();
  }

  /**
   * 根据ID更新抓取内容
   * @param id
   * @param content
   */
  public void updateContentById(ObjectId id,String content){
    Query<Record> query = datastore.createQuery(Record.class).field("id").equal(id);
    UpdateOperations<Record> updateOperations = datastore.createUpdateOperations(Record.class)
        .set("content", content).set("reported", true).set("lastModify", new Date());
    datastore.findAndModify(query, updateOperations);
  }
}
