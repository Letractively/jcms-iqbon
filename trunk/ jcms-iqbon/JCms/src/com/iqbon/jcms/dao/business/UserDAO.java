package com.iqbon.jcms.dao.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.User;

/**
 * 用户DAO类，自动加载
 * @author hp
 *
 */
@Repository
public class UserDAO{
  
  private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
  
  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 增加用户
  * @param user
  * @return
  * @throws DataAccessException
  */
  public int insertUser(User user) throws DataAccessException{
     String sql = "insert into bu_user(user_name,password,position_num,add_user,add_time,last_login,del,nickname) "
        + "values (:userName,password(:password),:positionNum,:addUser,now(),now(),0,:nickName)";
     SqlParameterSource paramMap = new BeanPropertySqlParameterSource(user);
    return namedParameterJdbcTemplate.update(sql, paramMap);
   }

  /**
   * 把用户标记删除
   * @param userName
   * @return
   * @throws DataAccessException
   */
  public int deleteUser(String userName) throws DataAccessException {
    String sql = "update bu_user set del = 1 where user_name = :userName";
    SqlParameterSource paramMap = new MapSqlParameterSource("userName", userName);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
}
