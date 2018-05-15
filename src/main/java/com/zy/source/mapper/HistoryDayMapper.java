package com.zy.source.mapper;

import com.zy.source.pojo.HistoryDay;
import org.apache.ibatis.annotations.Mapper;

/**
 *Created by zy on 15/05/2018.
 *
 * 存储历史今天的接口数据
 */
@Mapper
public interface HistoryDayMapper {
    /**
     * 存储查询到的数据
     * @param historyDay
     */
    void insterFindAll(HistoryDay historyDay);

    /**
     * 查询是否存在此数据
     * @param historyDay
     * @return
     */
    Integer findByHiatory(HistoryDay historyDay);
}
