package com.zy.source.service.impl;

import com.zy.source.mapper.HistoryDayMapper;
import com.zy.source.mapper.LimitIdMapper;
import com.zy.source.mapper.ScheduleMapper;
import com.zy.source.pojo.HistoryDay;
import com.zy.source.service.HistoryDayService;
import com.zy.source.utils.email.EmailUtils;
import com.zy.source.utils.tools.HistroyDayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by zy on 15/05/2018.
 *
 * 历史的今天数据保存实现类
 */

@Service
@EnableScheduling
public class HistoryDayServiceImpl implements HistoryDayService{

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryDayServiceImpl.class);

    @Autowired
    private HistoryDayMapper historyDayMapper;
    @Autowired
    private LimitIdMapper limitIdMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;


    @Scheduled(cron = "0 0 2 * * ?")
    public void start(){
        if ("true".equals(scheduleMapper.findHistoryDaySchedule())){
            LOGGER.info("获取历史今天定时开始！");
            insterFindAll((limitIdMapper.findHistoryDayMinId()));
        }
    }

    @Override
    public void insterFindAll(String limit) {
        int count = Integer.parseInt(limit);
        try {
            for (int i = Integer.parseInt(limit); i < Integer.parseInt(limit) + 10000; i++) {
                HistroyDayUtils.Context context = HistroyDayUtils.histroyDay(String.valueOf(i));
                if ("超过每日可允许请求次数!".equals(context.getReason())) {
                    int num = limitIdMapper.updateHistoryDayMinId(count);
                    if (num > 0) {
                        LOGGER.info("更讯数据库成功");
                        EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                                "1573240324@qq.com",
                                "历史今天更新",
                                "zy：您好！<br>数据库更新成功，在增加" + count + "！<br>勿忘初心！");
                    } else {
                        LOGGER.info("更讯数据库失败");
                        EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                                "1573240324@qq.com",
                                "历史今天更新",
                                "zy：您好！<br>更讯数据库失败，请检查代码！勿忘初心！");
                    }
                    break;
                } else {
                    count++;
                    if (context != null && context.getResult() != null) {
                        if (context.getResult().length > 0) {
                            for (HistroyDayUtils.Result result : context.getResult()) {
                                if (result != null) {
                                    HistoryDay historyDay = new HistoryDay();
                                    historyDay.setContent(result.getContent());
                                    historyDay.seteId(result.getE_id());
                                    historyDay.setPicNo(result.getPicNo());
                                    historyDay.setTitle(result.getTitle());
                                    String id = "", title = "", url = "";
                                    for (HistroyDayUtils.PicUrl picUrl : result.getPicUrl()) {
                                        id += picUrl.getId() + ",";
                                        title += picUrl.getPic_title() + ",";
                                        url += picUrl.getUrl() + ",";
                                    }
                                    historyDay.setPicId(id);
                                    historyDay.setTitle(title);
                                    historyDay.setUrl(url);
                                    Integer byHiatory = historyDayMapper.findByHiatory(historyDay);
                                    if (byHiatory == 0) {
                                        historyDayMapper.insterFindAll(historyDay);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            int num = limitIdMapper.updateHistoryDayMinId(count);
            if (num > 0) {
                LOGGER.info("更讯数据库成功");
                try {
                    EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                            "1573240324@qq.com",
                            "历史今天更新",
                            "zy：您好！<br>获取数据异常，数据库更新成功，在增加" + count + "！<br>请检查代码！勿忘初心！");
                } catch (Exception e1) {
                    LOGGER.info("邮件发送失败");
                }
            } else {
                LOGGER.info("更讯数据库失败");
                try {
                    EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                            "1573240324@qq.com",
                            "历史今天更新",
                            "zy：您好！<br>获取数据异常，更讯数据库失败，请检查代码！勿忘初心！");
                } catch (Exception e1) {
                    LOGGER.info("邮件发送失败");
                }
            }
            LOGGER.warn("存储历史今天异常::{}",e.getMessage());
        }
    }
}
