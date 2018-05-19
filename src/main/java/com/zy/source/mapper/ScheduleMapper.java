package com.zy.source.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 查询是否发送
 *
 * Created by zy on 19/05/2017.
 */
@Mapper
public interface ScheduleMapper {
    /**
     *查询笑话大全定时是否开启
     * @return
     */
    String findJokeSchedule();

    /**
     * 查询历史今天定时是否开启
     * @return
     */
    String findHistoryDaySchedule();
}
