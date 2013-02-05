package com.iqbon.spider.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.iqbon.spider.domain.Record;
import com.iqbon.spider.util.BeanFactory;

public class RecordDaoTest {

  private RecordDao recordDao;

  private Record record = new Record();

  private static final Logger logger = Logger.getLogger(RecordDao.class);

  @Before
  public void setUp() throws Exception {
    record.setContent("test");
    record.setUrl("http://home.163.com/13/0105/20/8KFV6PN000104JSD_all.html#p1");
    record.setLastModify(new Date());
    record.setReported(false);
    record.setSourceId("50e82a51cc7d1fe087c4c48a");
    recordDao = (RecordDao) BeanFactory.getBean("recordDao");
  }

  @Test
  public void testInsertRecordList() {
    List<Record> list = new ArrayList<Record>();
    list.add(record);
    recordDao.insertRecordList(list);
  }

  @Test
  public void testQueryRecordByUrl() {
    Record r = recordDao.queryRecordByUrl(record.getUrl());
    logger.info(ToStringBuilder.reflectionToString(r));
  }

  @Test
  public void testUpdateContentById() {
    Record r = recordDao.queryRecordByUrl(record.getUrl());
    r.setContent("after test");
    recordDao.updateContentById(r.getId(), r.getContent());
  }

}
