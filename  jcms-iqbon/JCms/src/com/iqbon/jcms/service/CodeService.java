package com.iqbon.jcms.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.system.CodeDAO;
import com.iqbon.jcms.domain.Code;

@Service
public class CodeService {

  @Autowired
  private CodeDAO codeDAO;

  /**
   * 增加一个系统码
   * @param code
   * @return
   */
  public int addCode(Code code) {
    return codeDAO.insertCode(code);
  }

  /**
   * 删除单个系统码
   * @param groupName
   * @param key
   * @param parentKey
   * @return
   */
  public int deleteCode(String groupName, String key, String parentKey) {
      return codeDAO.deleteCode(groupName, key, parentKey);
  }

  /**
   * 获取系统中所有码组
   * @return
   */
  public List<Code> getAllGroup() {
    return codeDAO.queryAllGroup();
  }
  
  /**
   * 根据参数组名和父参数，获取所有子参数
   * @param groupName
   * @return
   */
  public List<Code> getSubCodeByGroupAndParent(String groupName, String parentKey) {
    return codeDAO.querySubCodeByGroupAndParent(groupName, parentKey);
  }

  /**
   * 增加一个系统参数组
   * @param groupName
   * @return
   */
  public int addGroup(String description) {
    String groupName = RandomStringUtils.randomNumeric(10);
    Code code = new Code();
    code.setGroupName(groupName);
    code.setKey(groupName);
    code.setParentKey("");
    code.setValue(description);
    return codeDAO.insertCode(code);
  }

  /**
   * 删除系统参数组，已经所属的参数
   * @param groupName
   * @return
   */
  public int deleteCodeGroup(String groupName) {
    return codeDAO.deleteCodeByGroup(groupName);
  }

  /**
   * 根据系统码组名获取系统码组信息
   * @param groupName
   * @return
   */
  public Code getCodeGroupInfo(String groupName){
    return codeDAO.queryGroupInfo(groupName);
  }
}
