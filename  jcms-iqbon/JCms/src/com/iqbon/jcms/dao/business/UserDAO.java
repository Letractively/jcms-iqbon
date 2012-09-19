package com.iqbon.jcms.dao.business;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.domain.mapRow.UserMapper;

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

  /**
   * 根据用户名和密码验证用户
   * @param userName
   * @param password
   * @return
   */
  public User validationUser(String userName, String password) {
    String sql = "select user_name , bu_user.position_num ,email , telephone , position_name,"
        + " nickname, mobile from bu_user left join bu_position on bu_user.position_num = bu_position.position_num "
        + " where bu_user.del = 0 and  bu_user.user_name = :userName and "
        + "bu_user.password = password(:password)";
    Map<String, String> map = new HashMap<String, String>();
    map.put("userName", userName);
    map.put("password", password);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    RowMapper<User> mapper = new UserMapper();
    try{
      return (User) namedParameterJdbcTemplate.queryForObject(sql, paramMap, mapper);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  /**
   * 根据用户名把用户数据彻底删除
   * @param userName
   * @return
   */
  public int deleteUserComplete(String userName) {
    String sql = "delete from bu_user where user_name = :userName";
    SqlParameterSource paramMap = new MapSqlParameterSource("userName", userName);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 更新用户信息
   * @param user
   * @return
   */
  public int updateUserInfo(User user) {
    String sql = "update bu_user set position_num=:positionNum ,"
        + " email=:email , telephone=:telephone , mobile=:mobile , nickname=:nickName "
        + "where user_name = :userName";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(user);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 更新用户最后登录时间
   * @param userName
   * @param lastLogin
   * @return
   */
  public int updaeUserLastLogin(String userName, String lastLogin) {
    String sql = "update bu_user set last_login=:lastLogin where user_name=:userName";
    Map<String, String> map = new HashMap<String, String>();
    map.put("lastLogin", lastLogin);
    map.put("userName", userName);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据用户名获取用户信息
   * @param userName
   * @return
   */
  public User queryUserInfoByUserName(String userName) {
    String sql = "select user_name , position_num ,email , telephone , nickname, mobile from bu_user"
        + " where user_name=:userName";
    Map<String, String> map = new HashMap<String, String>(1);
    map.put("userName", userName);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new UserMapper());
  }

  /**
   * 修改用户密码
   * @param userName
   * @param password
   * @return
   */
  public int updatePassword(String userName, String password) {
    String sql = "update bu_user set password = password(:password) where user_name = :userName";
    Map<String, String> map = new HashMap<String, String>(2);
    map.put("userName", userName);
    map.put("password", password);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
}