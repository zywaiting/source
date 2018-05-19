package com.zy.source.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 查询开始id
 *
 * Created by zy on 19/05/2017.
 */
@Mapper
public interface LimitIdMapper {

    /**
     * 查询笑话大全最小id
     * @return
     */
    String findJokeMinId();

    /**
     * 查询历史今天最小id
     * @return
     */
    String findHistoryDayMinId();

    /**
     * 修改历史今天最小id
     * @param limitId
     * @return
     */
    int updateHistoryDayMinId(@Param("limitId") int limitId);

    int updataJokeMinId(@Param("limitId") int limitId);
}
