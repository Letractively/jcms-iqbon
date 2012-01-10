package com.iqbon.jcms.domain;

import java.io.Serializable;
import java.util.HashMap;

public class CMSDomain implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private HashMap<String, Object> record;
  
  /**
   * 根据KEY 获取对象
   * @param key
   * @return
   */
  public Object get(String key){
    if (record == null) {
      return null;
    }
    return record.get(key);
  }
  
  /**
   * 根据Key 获取String
   * @param key
   * @return
   */
  public String getString(String key){
    if (record == null) {
      return "";
    }
    Object value = record.get(key);
    if (value == null) {
      return "";
    }
    return String.valueOf(value);
  }
  
  /**
   * 根据 KEY 返回Int
   * @param key
   * @return
   * @throws Exception
   */
  public int getInt(String key) throws Exception{
    if (record == null) {
      throw new Exception("record记录为空");
    }
    Object value = record.get(key);
    if (value == null) {
       throw new Exception("找不到记录");
    }
    return Integer.valueOf(String.valueOf(value));
  }
  
  /**
   * 按照Key 设置value
   * @param key
   * @param value
   * @return
   */
  public boolean set(String key ,Object value){
    if (key == null || value == null) {
      return false;
    }
    if (record.put(key, value) == null) {
      return false;
    }
    return true;
  }
}
