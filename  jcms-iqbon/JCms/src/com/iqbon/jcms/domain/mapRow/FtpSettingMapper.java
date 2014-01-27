package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.FtpSetting;

public class FtpSettingMapper implements RowMapper<FtpSetting> {

  @Override
  public FtpSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
    FtpSetting ftp = new FtpSetting();
    ftp.setFtpIp(rs.getString("ftp_ip"));
    ftp.setFtpPort(rs.getString("ftp_port"));
    ftp.setUsername(rs.getString("username"));
    ftp.setPassword(rs.getString("password"));
    ftp.setFtpPath(rs.getString("ftp_path"));
    ftp.setFtpPushRate(rs.getInt("ftp_push_rate"));
    ftp.setEnable(rs.getBoolean("enable"));
    return ftp;
  }

}
