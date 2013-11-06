package com.iqbon.jcms.web.api;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/api/doc")
public class ModelApiAction extends JCMSAction {

  private final static Logger logger = Logger.getLogger(ModelApiAction.class);

}
