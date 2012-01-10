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
   * ����KEY ��ȡ����
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
   * ����Key ��ȡString
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
   * ���� KEY ����Int
   * @param key
   * @return
   * @throws Exception
   */
  public int getInt(String key) throws Exception{
    if (record == null) {
      throw new Exception("record��¼Ϊ��");
    }
    Object value = record.get(key);
    if (value == null) {
       throw new Exception("�Ҳ�����¼");
    }
    return Integer.valueOf(String.valueOf(value));
  }
  
  /**
   * ����Key ����value
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
