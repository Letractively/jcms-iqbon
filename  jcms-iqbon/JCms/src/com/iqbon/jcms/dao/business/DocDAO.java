package com.iqbon.jcms.dao.business;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.mapRow.DocMapper;

/**文章DAO类
 * @author hp
 *
 */
@Repository
public class DocDAO {
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入文章
   * @param doc
   */
  public int insertDoc(Doc doc) {
    String sql = "insert into bu_doc(title,content,digest,modify_user,del,reporter,last_modify,url,type,keyword) "
        + "values(:title,:content,:digest,:modifyUser,'0',:reporter,now(),:url,:type,:keyword)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(doc);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据ID查询文章内容
   * @param docid
   * @return
   */
  public Doc queryDocById(int docid) {
    String sql = "select * from bu_doc where docid = :docid";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("docid", docid);
    return namedParameterJdbcTemplate.queryForObject(sql, map, new DocMapper());
  }

  /**
   * 根据文章ID更新文章内容
   * @param doc
   * @return
   */
  public int updateDoc(Doc doc) {
    String sql = "update bu_doc set title=:title"
        + ",content=:content,digest=:digest,modify_user=:modifyUser,del=:delete,"
        + "reporter=:reporter,url=:url,type=:type,keyword=:keyword,last_modify = now() where docid =:docid";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(doc);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
}
