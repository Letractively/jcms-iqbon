package com.iqbon.jcms.dao.system;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.util.BeanFactory;

public class CodeDAOTest extends TestCase {

  private Code code;
  private CodeDAO codeDAO;
  private Logger logger = Logger.getLogger(CodeDAOTest.class);
  
  protected void setUp() throws Exception {
    super.setUp();
    code = new Code();
    code.setGroupName("test");
    code.setKey("key");
    code.setParentKey(null);
    code.setValue("value");
    codeDAO = (CodeDAO) BeanFactory.getBean("codeDAO");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertCode() {
    logger.info(codeDAO.insertCode(code));
  }

  public void testDeleteCode() {
    logger.info(codeDAO.deleteCode(code.getGroupName(), "key1", code.getKey()));
  }

  public void testDeleteCodeByGroup() {
    logger.info(codeDAO.deleteCodeByGroup(code.getGroupName()));
  }

  public void testQuerySubCodeByGroupAndParent() {
    List<Code> list = codeDAO
        .querySubCodeByGroupAndParent(code.getGroupName(), code.getParentKey());
    logger.info(list.size());
    for (Code code : list) {
      logger.info(ToStringBuilder.reflectionToString(code));
    }
  }

  public void testQueryAllGroup() {
    List<Code> list = codeDAO.queryAllGroup();
    logger.info(list.size());
    for (Code code : list) {
      logger.info(ToStringBuilder.reflectionToString(code));
    }
  }

  public void testQueryGroupInfo() {
    Code codeGroup = codeDAO.queryGroupInfo(code.getGroupName());
    logger.info(ToStringBuilder.reflectionToString(codeGroup));
  }
}
