package com.iqbon.jcms.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.system.FtpDAO;
import com.iqbon.jcms.domain.FtpSetting;

/**
 * FTP服务类
 * @author hp
 *
 */
@Service
public class FtpService {

  private static final Logger logger = Logger.getLogger(FtpService.class);

  @Autowired
  private FtpDAO ftpDAO;

  /**
   * 设置系统ftp设置，原来没有设置进行添加，如果已经设置进行更新
   * @param ftpSetting
   */
  public void setFtpSetting(FtpSetting ftpSetting) {
    FtpSetting oldSetting = ftpDAO.queryFtpSetting();
    if (oldSetting == null) {
      ftpDAO.insertFtpSetting(ftpSetting);
    } else {
      ftpDAO.updateFtpSetting(ftpSetting);
    }
  }

  /**
   * 获取FTP设置
   * @return
   */
  public FtpSetting getFtpSetting() {
    return ftpDAO.queryFtpSetting();
  }

  /**
   * 判断FTP设置是否可用
   * @param ftp
   * @return
   */
  public boolean isFtpAvailable(FtpSetting ftp) {
    FTPClient ftpclient = new FTPClient();
    if (StringUtils.isEmpty(ftp.getFtpIp())) {
      logger.error("ftp ip 为空");
      return false;
    }
    String port = StringUtils.defaultIfEmpty(ftp.getFtpPort(), "21");
    String username = StringUtils.defaultString(ftp.getUsername(), "");
    String password = StringUtils.defaultString(ftp.getPassword(), "");
    try{
      ftpclient.connect(ftp.getFtpIp(), Integer.valueOf(port));
      ftpclient.login(username, password);
      return true;
    }catch(Exception e){
      logger.error("ftp不可用" + ftp.getFtpIp() + ":" + ftp.getUsername() + "\\" + ftp.getPassword(),
          e);
      return false;
    }
  }

}
