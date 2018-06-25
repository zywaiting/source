package com.zy.source.mapper;

import com.zy.source.utils.tools.JokeUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 笑话大全接口
 *
 * Created by zy on 15/05/2018.
 */
@Mapper
public interface JokeMapper {
    /**
     * 存储查询到的数据
     * @param historyDay
     */
    void insterJoke(JokeUtils.Data data);

    /**
     * 查询是否存在此数据
     * @param historyDay
     * @return
     */
    Integer findByContent(@Param("content") String content);

    /**
     * 查询数量
     * @return
     */
    Integer count();
}
