package com.zy.source.controller;

import com.zy.source.service.JokeService;
import com.zy.source.utils.tools.JokeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 保存笑话实现类
 *
 * Created by zy on 14/05/2018
 */
@RestController
public class JokeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokeController.class);
//24500
    @Autowired
    private JokeService jokeService;
    @RequestMapping(value = "/api/joke", method = RequestMethod.GET)
    public void findOneCity(@RequestParam(value = "limit", required = true) String limit) {
        LOGGER.info("笑话大全配置读取数： {}", limit);
        jokeService.insterJoke(limit);
    }

}
