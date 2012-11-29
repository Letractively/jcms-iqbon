package com.iqbon.jcms.util;

public class NotFoundException extends Exception {
  public NotFoundException() {
    super("数据已经被删除");
  }
}
