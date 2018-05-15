package com.zy.source.service.impl;

import com.zy.source.mapper.HistoryDayMapper;
import com.zy.source.pojo.HistoryDay;
import com.zy.source.service.HistoryDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zy on 15/05/2018.
 *
 * 历史的今天数据保存实现类
 */
@Service
public class HistoryDayServiceImpl implements HistoryDayService{

    @Autowired
    private HistoryDayMapper historyDayMapper;

    @Override
    public void insterFindAll(HistoryDay historyDay) {
        historyDayMapper.insterFindAll(historyDay);
    }
}
