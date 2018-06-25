package com.zy.source.service.impl;

import com.zy.source.mapper.HistoryDayMapper;
import com.zy.source.mapper.JokeMapper;
import com.zy.source.mapper.LimitIdMapper;
import com.zy.source.mapper.ScheduleMapper;
import com.zy.source.service.JokeService;
import com.zy.source.utils.email.EmailUtils;
import com.zy.source.utils.tools.JokeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保存笑话实现类
 *
 * Created by zy on 14/05/2018
 */
@Service
@EnableScheduling
public class JokeServiceImpl implements JokeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokeServiceImpl.class);
    @Autowired
    private JokeMapper jokeMapper;
    @Autowired
    private LimitIdMapper limitIdMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Scheduled(cron = "0 0 15 * * ?")
    public void start(){
        if ("true".equals(scheduleMapper.findHistoryDaySchedule())){
            LOGGER.info("获取笑话大全定时开始！");
            insterJoke((limitIdMapper.findJokeMinId()));
        }
    }

    @Override
    public void insterJoke(String limit) {
        int sum1 = jokeMapper.count();
        int count = Integer.parseInt(limit);
        try {
            for (int i = Integer.parseInt(limit); i < Integer.parseInt(limit) + 10000; i++) {
                JokeUtils.Context context = JokeUtils.gain(String.valueOf(i));
                if ("超过每日可允许请求次数!".equals(context.getReason())) {
                    int num = limitIdMapper.updataJokeMinId(count);
                    if (num > 0) {
                        LOGGER.info("更讯数据库成功");
                        int sum2 = jokeMapper.count();
                        EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                                "1573240324@qq.com",
                                "笑话大全",
                                "zy：您好！<br>数据库更新成功，增加" + (sum2 - sum1) + "！<br>勿忘初心！");
                    } else {
                        LOGGER.info("更讯数据库失败");
                        EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                                "1573240324@qq.com",
                                "笑话大全",
                                "zy：您好！<br>更讯数据库失败，请检查代码！勿忘初心！");
                    }
                    break;
                } else {
                    count++;
                    if (context != null && context.getResult() != null) {
                        List<JokeUtils.Data> data = context.getResult().getData();
                        if (data != null){
                            for (JokeUtils.Data datum : data) {
                                Integer num = jokeMapper.findByContent(datum.getContent());
                                if (num==0){
                                    jokeMapper.insterJoke(datum);
                                }
                            }
                        } else {
                            LOGGER.warn("笑话大全未查到数据");
                        }
                    }
                }
            }
        }catch (Exception e) {
            int num = limitIdMapper.updataJokeMinId(count);
            if (num > 0) {
                LOGGER.info("更讯数据库成功");
                int sum2 = jokeMapper.count();
                try {
                    EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                            "1573240324@qq.com",
                            "笑话大全",
                            "zy：您好！<br>获取数据异常，数据库更新成功，增加" + (sum2 - sum1) + "！<br>请检查代码！勿忘初心！");
                } catch (Exception e1) {
                    LOGGER.info("邮件发送失败");
                }
            } else {
                LOGGER.info("更讯数据库失败");
                try {
                    EmailUtils.sendMail("2012602020@qq.com", "2012602020@qq.com", "dmsegigprnfafjcc",
                            "1573240324@qq.com",
                            "笑话大全",
                            "zy：您好！<br>获取数据异常，更讯数据库失败，请检查代码！勿忘初心！");
                } catch (Exception e1) {
                    LOGGER.info("邮件发送失败");
                }
            }
            LOGGER.warn("笑话大全存储异常::{}",e.getMessage());
        }
    }
}
