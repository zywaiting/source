package com.zy.source.mapper;

import com.zy.source.utils.tools.JokeUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface JokeMapper {
    void insterJoke(JokeUtils.Data data);

    Integer findByContent(@Param("content") String content);
}
