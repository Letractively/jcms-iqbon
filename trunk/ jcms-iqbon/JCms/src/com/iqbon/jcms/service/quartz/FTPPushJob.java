package com.iqbon.jcms.service.quartz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.FtpSetting;
import com.iqbon.jcms.util.JCMSProperties;

/**
 * FTP推送任务类
 * <br/>网络情况不好情况下不好用
 * @author hp
 *
 */
@Deprecated
public class FTPPushJob implements Runnable {

  private static final Logger logger = Logger.getLogger(FTPPushJob.class);

  private static final JCMSProperties properties = JCMSProperties.getInstance();

  private FtpSetting ftp;

  private InputStream input;

  private String fileName;
  
  private String workingPath;

  private Stack<String> pathStack = new Stack<String>();

  /**
   * 推送的运行状态，0初始化，1推送中，2出错
   */
  private int status = 0;

  private String msg = "";

  private FTPPushJob() {
  }

  public FTPPushJob(FtpSetting ftp) {
    this.ftp = ftp;
  }

  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  // @Override
  public void run1() {
    FTPClient ftpClient = new FTPClient();
    String port = StringUtils.defaultIfEmpty(ftp.getFtpPort(), "21");
    String username = StringUtils.defaultString(ftp.getUsername(), "");
    String password = StringUtils.defaultString(ftp.getPassword(), "");
    String path = StringUtils.defaultString(ftp.getFtpPath(), "/");
    String sourcePath = properties.getOutFilePath();//本地文件目录
    workingPath = path;
    pathStack.add(workingPath);
    try {
      ftpClient.connect(ftp.getFtpIp(), Integer.valueOf(port));//连接FTP服务器
      ftpClient.login(username, password);//登录
      ftpClient.changeWorkingDirectory(path);
      File file = new File(sourcePath);
      if (file.exists() && file.isDirectory()) {
        File sourceFile[] = file.listFiles();
        for (File subFile : sourceFile) {
          if (subFile.isDirectory()) {//是目录的话
            processFolder(subFile, ftpClient);
          } else {//是文件
            fileName = subFile.getName();
            input = new FileInputStream(subFile);
            ftpClient.storeFile(fileName, input);
          }
          }
        }
      input.close();
      ftpClient.logout();
    } catch (Exception e) {
      logger.error("推送FTP失败", e);
    } finally {//最后关闭连接
      try {
        ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 处理目录
   * @param file
   * @param ftpClient
   * @throws IOException 
   */
  private void processFolder(File file, FTPClient ftpClient) throws IOException {
    String folderName = file.getName();
    String lastFolder = pathStack.peek();
    workingPath = lastFolder + "/" + folderName;
    pathStack.add(workingPath);
    if (!enterDir(workingPath, ftpClient)) {
      ftpClient.makeDirectory(folderName);
      ftpClient.changeWorkingDirectory(folderName);
    }
    File sourceFile[] = file.listFiles();
    for (File subFile : sourceFile) {
      if (subFile.isDirectory()) {//是目录的话
        processFolder(subFile, ftpClient);
      } else {//是文件
        fileName = subFile.getName();
        input = new FileInputStream(subFile);
        ftpClient.storeFile(fileName, input);
      }
    }
    pathStack.pop();
    ftpClient.changeToParentDirectory();
  }

  /**
   * 检查文件夹是否存在
   * @param dir
   * @param ftpClient
   * @return
   */
  private boolean enterDir(String dir, FTPClient ftpClient) {
    try {
      return ftpClient.changeWorkingDirectory(dir);
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}
