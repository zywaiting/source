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
    void insterJoke(JokeUtils.Data data);

    Integer findByContent(@Param("content") String content);
}
