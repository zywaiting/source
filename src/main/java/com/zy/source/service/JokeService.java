package com.zy.source.service;

import com.zy.source.utils.tools.JokeUtils;

import java.util.List;

/**
 * 保存笑话接口类
 *
 * Created by zy on 14/05/2018.
 */
public interface JokeService {
    /**
     * 存储查询到的数据
     * @param limit
     */
    void insterJoke(String limit);
}
