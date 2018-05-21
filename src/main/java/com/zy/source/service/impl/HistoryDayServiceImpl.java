package com.zy.source.service.impl;

import com.zy.source.mapper.HistoryDayMapper;
import com.zy.source.mapper.LimitIdMapper;
import com.zy.source.mapper.ScheduleMapper;
import com.zy.source.pojo.HistoryDay;
import com.zy.source.service.HistoryDayService;
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


    @Scheduled(cron = "0/5 * * * * ?")
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
                    } else {
                        LOGGER.info("更讯数据库失败");
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
            LOGGER.warn("存储历史今天异常::{}",e.getMessage());
        }
    }
}
