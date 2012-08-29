package com.iqbon.jcms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.iqbon.jcms.dao.business.DocDAO;
import com.iqbon.jcms.domain.Doc;

public class DocService {

  @Autowired
  private DocDAO docDAO;

  /**
   * 添加文章
   * @param doc
   * @return
   */
  public int addDoc(Doc doc) {
    return docDAO.insertDoc(doc);
  }

  /**
   * 修改文章
   * @param doc
   * @return
   */
  public int modifyDoc(Doc doc) {
    return docDAO.updateDoc(doc);
  }

  /**
   * 根据文章ID获取文章信息
   * @param docid
   * @return
   */
  public Doc getDocById(int docid) {
    return docDAO.queryDocById(docid);
  }
}
