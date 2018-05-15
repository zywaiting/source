package com.zy.source.controller;

import com.zy.source.service.HistoryDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zy on 15/05/2018.
 */
@RestController
public class HistoryDayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryDayController.class);

    @Autowired
    private HistoryDayService historyDayService;

    @RequestMapping(value = "/api/historyday", method = RequestMethod.GET)
    public void findOneCity(@RequestParam(value = "limit", required = true) String limit) {
        LOGGER.info("配置读取数量： {}", limit);
        historyDayService.insterFindAll(limit);
    }
}
