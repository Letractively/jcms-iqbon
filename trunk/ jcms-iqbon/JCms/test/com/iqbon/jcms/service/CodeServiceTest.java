package com.iqbon.jcms.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.util.BeanFactory;

public class CodeServiceTest extends TestCase {

  private static final Logger logger = Logger.getLogger(CodeServiceTest.class);
  private CodeService codeService;
  private Code code;
  
  protected void setUp() throws Exception {
    super.setUp();
    code = new Code();
    code.setGroupName("test");
    code.setKey("key");
    code.setParentKey("test");
    code.setValue("value");
    codeService = (CodeService) BeanFactory.getBean("codeService");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }
  
  public void testGetAllGroup() {
    List<Code> codeList = codeService.getAllGroup();
    for (Code code : codeList) {
      logger.info(ToStringBuilder.reflectionToString(code));
    }
  }
  
  public void testAddCode() {
    int number = codeService.addCode(code);
    logger.info(number);
  }
  
  public void testDeleteCode() {
    int number = codeService.deleteCode(code.getGroupName(), code.getKey(), code.getParentKey());
    logger.info(number);
  }

  public void testGetSubCodeByGroupAndParent() {
    List<Code> list = codeService.getSubCodeByGroupAndParent(code.getGroupName(),
        code.getParentKey());
    for (Code code : list) {
      logger.info(ToStringBuilder.reflectionToString(code));
    }
  }

  public void testAddGroup() {
    int number = codeService.addGroup(code.getValue());
    logger.info(number);
  }

  public void testDeleteCodeGroup() {

    int deleteNum = codeService.deleteCodeGroup("3413034351");
    logger.info(deleteNum);
  }

}
