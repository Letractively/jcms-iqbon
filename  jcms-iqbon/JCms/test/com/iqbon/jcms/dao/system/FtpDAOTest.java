package com.iqbon.jcms.dao.system;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.FtpSetting;
import com.iqbon.jcms.util.BeanFactory;

public class FtpDAOTest extends TestCase {

  private static final Logger logger = Logger.getLogger(FtpDAOTest.class);
  FtpSetting ftpSetting = new FtpSetting();
  FtpDAO ftpDAO;

  protected void setUp() throws Exception {
    super.setUp();
    ftpSetting.setEnable(false);
    ftpSetting.setFtpIp("127.0.0.1");
    ftpSetting.setFtpPath("\\");
    ftpDAO = (FtpDAO) BeanFactory.getBean("ftpDAO");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertFtpSetting() {
    ftpDAO.insertFtpSetting(ftpSetting);
  }

  public void testDeleteFtpSetting() {
    ftpDAO.deleteFtpSetting(ftpSetting.getFtpIp());
  }

  public void testSelectFtpSetting() {
    FtpSetting setting = ftpDAO.queryFtpSetting();
    logger.info(ToStringBuilder.reflectionToString(setting));
  }

  public void testUpdateFtpSetting() {
    ftpSetting.setFtpIp("192.168.0.1");
    ftpSetting.setEnable(true);
    ftpDAO.updateFtpSetting(ftpSetting);
  }
}
