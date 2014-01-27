package com.iqbon.jcms.service;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.FtpSetting;
import com.iqbon.jcms.util.BeanFactory;

public class FtpServiceTest extends TestCase {

  private static final Logger logger = Logger.getLogger(FtpServiceTest.class);
  private static FtpService ftpService;
  private static FtpSetting ftp = new FtpSetting();

  protected void setUp() throws Exception {
    ftpService = (FtpService) BeanFactory.getBean("ftpService");
    ftp.setFtpIp("127.0.0.1");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testIsFtpAvailable() {

    System.out.println(ftpService.isFtpAvailable(ftp));
  }

}
