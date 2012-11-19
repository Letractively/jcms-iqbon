package com.iqbon.jcms.service.quartz;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

public class ParseModelJobTest extends TestCase {

  private ParseModelJob parseModelJob = new ParseModelJob();
  private Logger logger = Logger.getLogger(ParseModelJobTest.class);

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testExecute() {
    try {
      parseModelJob.execute(null);
    } catch (JobExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
