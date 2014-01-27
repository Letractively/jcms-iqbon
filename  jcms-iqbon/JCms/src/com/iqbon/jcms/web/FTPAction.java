package com.iqbon.jcms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.FtpSetting;
import com.iqbon.jcms.service.FtpService;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/ftp")
public class FTPAction extends JCMSAction {

  @Autowired
  private FtpService ftpService;

  /**
   * 获取系统ftp设置
   * @return
   */
  @RequestMapping("/ftpSetting.do")
  public ModelAndView getFTPSetting() {
    ModelAndView mav = new ModelAndView();
    FtpSetting ftpSetting = ftpService.getFtpSetting();
    mav.addObject("ftp", ftpSetting);
    mav.setViewName(KeyConstant.ADMIN_JSP_PATH + "ftpSetting");
    return mav;
  }

  @RequestMapping("/updateFtpSetting.do")
  public ModelAndView updateFTPSetting(@RequestParam("ftpip")
  String ftpIp, @RequestParam("ftpport")
  String ftpPort, @RequestParam("username")
  String username, @RequestParam("password")
  String password, @RequestParam("ftpPath")
  String ftpPath, @RequestParam("ftpRefresh")
  int ftpRefreshRate, @RequestParam("enable")
  boolean enable) {
    FtpSetting ftpSetting = new FtpSetting();
    ftpSetting.setFtpIp(ftpIp);
    ftpSetting.setFtpPort(ftpPort);
    ftpSetting.setUsername(username);
    ftpSetting.setPassword(password);
    ftpSetting.setFtpPath(ftpPath);
    ftpSetting.setFtpPushRate(ftpRefreshRate);
    ftpSetting.setEnable(enable);
    if (ftpService.isFtpAvailable(ftpSetting)) {
      ftpService.setFtpSetting(ftpSetting);
    } else {
      errorMav.setErrorInfo("FTP不可用，请确认FTP的IP，账号密码正确");
      return errorMav;
    }
    return getFTPSetting();
  }

}
