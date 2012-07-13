package com.iqbon.jcms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.service.CodeService;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/code")
public class CodeAction extends JCMSAction {

  private final static Logger logger = Logger.getLogger(CodeAction.class);
  @Autowired
  private CodeService codeService;

  /**
   * 获取所有系统码组
   */
  @RequestMapping(value = "/codePage.do")
  public ModelAndView getAllCodeGroup() {
    ModelAndView mav = new ModelAndView();
    List<Code> groupList = codeService.getAllGroup();
    mav.addObject("groupList", groupList);
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "codePage");
    return mav;
  }

  /**
   * 增加系统参数组
   * @param groupName
   * @param description
   * @return
   */
  @RequestMapping(value = "/addCode.do", method = RequestMethod.POST)
  public ModelAndView addGroup(@RequestParam("description")
  String description) {
    int number = codeService.addGroup(description);
    if (number > 0) {
      return this.getAllCodeGroup();
    }else{
      return errorMav;
    }
  }

  /**
   * 增加参数
   * @param groupName
   * @param key
   * @param value
   * @param parentKey
   * @return
   */
  @RequestMapping(value = "/addSubCode.do", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Boolean> addCode(@RequestParam("groupName")
  String groupName, @RequestParam("codeKey")
  String key, @RequestParam("codeValue")
  String value, @RequestParam("parentKey")
  String parentKey) {
    Map<String, Boolean> map = new HashMap<String, Boolean>();
    Code code = new Code();
    code.setGroupName(groupName);
    code.setKey(key);
    code.setParentKey(parentKey);
    code.setValue(value);
    int number = codeService.addCode(code);
    map.put("success", number > 0);
    return map;
  }

  /**
   * 根据参数组名和父参数，获取所有子参数
   * @param groupName
   * @param parentKey
   * @return
   */
  @RequestMapping(value = "/getSubCode.do", method = RequestMethod.POST)
  @ResponseBody
  public List<Code> getSubCode(@RequestParam("groupName")
  String groupName, @RequestParam("parentKey")
  String parentKey) {
    return codeService.getSubCodeByGroupAndParent(groupName, parentKey);
  }

  /**
   * 删除
   * @param groupName
   * @return
   */
  @RequestMapping(value = "/deleteCodeGroup.do")
  public ModelAndView deleteCodeGroup(@RequestParam("groupName")
  String groupName) {
    int number = codeService.deleteCodeGroup(groupName);
    if (number > 0) {
      return this.getAllCodeGroup();
    } else {
      return errorMav;
    }
  }
}
