package com.iqbon.jcms.dao.system;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.FtpSetting;
import com.iqbon.jcms.domain.mapRow.FtpSettingMapper;

/**
 * 系统FTP设置DAP
 * @author hp
 *
 */
@Repository
public class FtpDAO {
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(@Qualifier("dataSource")
  DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入FTP设置记录
   * @param ftpSetting
   */
  public void insertFtpSetting(FtpSetting ftpSetting) {
    String sql = "insert into sys_ftp(ftp_ip,ftp_port,username,password,ftp_path,ftp_push_rate,enable)"
        + " values(:ftpIp,:ftpPort,:username,:password,:ftpPath,:ftpPushRate,:enable)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(ftpSetting);
    namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 删除FTP设置
   * @param ip
   */
  public void deleteFtpSetting(String ip) {
    String sql = "delete from sys_ftp where ftp_ip = :ip";
    Map<String, Object> map = new HashMap<String, Object>(1);
    map.put("ip", ip);
    namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 更新FTP设置
   * @param ftpSetting
   */
  public void updateFtpSetting(FtpSetting ftpSetting) {
    String sql = "update sys_ftp set ftp_ip=:ftpIp,ftp_port=:ftpPort,username=:username,password=:password,"
        + "ftp_path=:ftpPath,ftp_push_rate=:ftpPushRate,enable=:enable";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(ftpSetting);
    namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 查询FTP设置
   * @return
   */
  public FtpSetting queryFtpSetting() {
    String sql = "select * from sys_ftp";
    try{
    return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<String, String>(),
        new FtpSettingMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

}
