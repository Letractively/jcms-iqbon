package com.iqbon.jcms.service.quartz;

import junit.framework.TestCase;

import com.iqbon.jcms.domain.FtpSetting;

public class FTPPushJobTest extends TestCase {

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testFTPPushJob() {
    FtpSetting ftp = new FtpSetting();
    ftp.setFtpIp("54.244.115.171");
    ftp.setFtpPath("\\");
    ftp.setUsername("webapp");
    ftp.setPassword("da#@I*O(");
    ftp.setFtpPath("xiaoshuo");
    FTPPushJob job = new FTPPushJob(ftp);
    job.run1();
  }

}
