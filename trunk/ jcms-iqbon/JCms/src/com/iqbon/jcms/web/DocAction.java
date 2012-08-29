package com.iqbon.jcms.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.service.DocService;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/doc")
public class DocAction extends JCMSAction {

  private final static Logger logger = Logger.getLogger(DocAction.class);

  @Autowired
  private DocService docService;
  
  public ModelAndView showAddModifyDoc() {
    return null;
  }
}
