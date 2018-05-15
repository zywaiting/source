package com.zy.source.service.impl;

import com.zy.source.mapper.JokeMapper;
import com.zy.source.service.JokeService;
import com.zy.source.utils.tools.JokeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保存笑话实现类
 *
 * Created by zy on 14/05/2018
 */
@Service
public class JokeServiceImpl implements JokeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokeServiceImpl.class);
    @Autowired
    private JokeMapper jokeMapper;

    @Override
    public void insterJoke(String limit) {
        try {
            for (int i = Integer.parseInt(limit); i > Integer.parseInt(limit) - 500; i++) {
                JokeUtils.Context context = JokeUtils.gain(String.valueOf(i));
                if ("超过每日可允许请求次数!".equals(context.getReason())) break;
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
        }catch (Exception e) {
            LOGGER.warn("笑话大全存储异常::{}",e.getMessage());
        }
    }
}
