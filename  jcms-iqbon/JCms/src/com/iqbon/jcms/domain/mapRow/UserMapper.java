package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.User;

public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setNickName(rs.getString("nickname"));
    user.setUserName(rs.getString("user_name"));
    user.setPositionNum(rs.getInt("position_num"));
    user.setEmail(rs.getString("email"));
    user.setTelephone(rs.getString("telephone"));
    user.setMobile(rs.getString("mobile"));
    user.setPositionName(rs.getString("position_num"));
    return user;
  }

}
