package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.Quartz;

public class QuartzMapper implements RowMapper<Quartz> {

  @Override
  public Quartz mapRow(ResultSet rs, int rowNum) throws SQLException {
    Quartz quartz = new Quartz();
    String triggerName = rs.getString("trigger_name");
    if (StringUtils.indexOf(triggerName, "&") != -1) {
      quartz.setTriggerName(StringUtils.substringBefore(triggerName, "&"));
    } else {
      quartz.setTriggerName(triggerName);
    }
    quartz.setJobName(rs.getString("JOB_NAME"));
    quartz.setNextTime(DateFormatUtils.format(rs.getLong("next_fire_time"), "yyyy-MM-dd HH:mm:ss"));
    quartz.setPrevTime(DateFormatUtils.format(rs.getLong("prev_fire_time"), "yyyy-MM-dd HH:mm:ss"));
    quartz.setStartTime(DateFormatUtils.format(rs.getLong("start_time"), "yyyy-MM-dd HH:mm:ss"));
    quartz.setEndTime(DateFormatUtils.format(rs.getLong("end_time"), "yyyy-MM-dd HH:mm:ss"));
    quartz.setStatus(rs.getString("trigger_state"));
    quartz.setDescription(rs.getString("DESCRIPTION"));
    return quartz;
  }

}
