package com.iqbon.jcms.web.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.service.DocService;
import com.iqbon.jcms.service.PushRecordService;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.web.DocAction;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/api/doc")
public class DocApiAction extends JCMSAction {

  private final static Logger logger = Logger.getLogger(DocAction.class);

  @Autowired
  private DocService docService;
  
  @Autowired
  private PushRecordService pushRecordService;
  
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> addDoc(@RequestParam("topicid")
  String topicid,@RequestParam("type")
  int type, @RequestParam("title")
  String title, @RequestParam("content")
  String content, @RequestParam("digest")
  String digest, @RequestParam("reporter")
  String reporter, @RequestParam("keyword")
  String keyword, @RequestParam("modelName")
  String modelName, @RequestParam("clientName")
  String clientName, @RequestParam("password")
  String password) {
    Map<String, String> result = new HashMap<String, String>();
    Doc doc = new Doc();
    String docid = RandomStringUtils.randomAlphanumeric(11);
    doc.setDocid(docid);
    doc.setTitle(title);
    doc.setContent(content);
    doc.setDigest(digest);
    doc.setReporter(reporter);
    doc.setModifyUser(clientName);
    doc.setType(Doc.docType.normal.ordinal());
    doc.setKeyword(keyword);
    doc.setModelName(modelName);
    doc.setStatus(Doc.docStatus.unPublish.ordinal());
    String url = JCMSConstant.createDocUrl(docid, Doc.docType.normal.ordinal());
    doc.setUrl(url);
    int number = docService.addDoc(doc);
    if (number > 0) {//增加推送记录
      PushRecord pushRecord = new PushRecord();
      pushRecord.setIndexid(JCMSConstant.createPushRecordId());
      pushRecord.setDocid(docid);
      pushRecord.setLspri(PushRecord.DEFAULT_LSPRI);
      pushRecord.setModifyUser(clientName);
      pushRecord.setTitle(title);
      pushRecord.setTopicid(topicid);
      pushRecord.setType(PushRecord.PUSH_RECORD_TYPE.doc.ordinal());
      pushRecord.setUrl(url);
      number = pushRecordService.addPushRecord(pushRecord);
      if (number <= 0) {
        result.put(APIConstant.K_RESULT, APIConstant.V_FAIL);
        result.put(APIConstant.K_MESSAGE, "添加推送记录失败");
      }
    } else {
      result.put(APIConstant.K_RESULT, APIConstant.V_FAIL);
      result.put(APIConstant.K_MESSAGE, "添加文章失败");
      return result;
    }
    result.put(APIConstant.K_RESULT, APIConstant.V_SUCCESS);
    return result;
  }
}
