package com.zy.source.service;

import com.zy.source.pojo.HistoryDay;

/**
 * Created by zy on 15/05/2018.
 *
 * 历史的今天数据保存接口
 */
public interface HistoryDayService {
    /**
     * 存储查询到的数据
     * @param historyDay
     */
    void insterFindAll(HistoryDay historyDay);
}
