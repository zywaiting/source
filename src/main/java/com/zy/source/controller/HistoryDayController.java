package com.zy.source.controller;

import com.zy.source.service.HistoryDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class HistoryDayController {

    @Autowired
    private HistoryDayService historyDayService;

    @RequestMapping(value = "/api/historyday", method = RequestMethod.GET)
    public void findOneCity(@RequestParam(value = "num", required = true) String cityName) {
      
    }
}
