package com.zy.source.service.impl;

import com.zy.source.mapper.HistoryDayMapper;
import com.zy.source.pojo.HistoryDay;
import com.zy.source.service.HistoryDayService;
import com.zy.source.utils.tools.HistroyDayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zy on 15/05/2018.
 *
 * 历史的今天数据保存实现类
 */
@Service
public class HistoryDayServiceImpl implements HistoryDayService{

    @Autowired
    private HistoryDayMapper historyDayMapper;

    @Override
    public void insterFindAll(String limit) {
        for (int i = Integer.parseInt(limit); i < Integer.parseInt(limit) + 10; i++) {
            HistroyDayUtils.Context context = HistroyDayUtils.histroyDay(String.valueOf(i));
            for (HistroyDayUtils.Result result : context.getResult()) {
                HistoryDay historyDay = new HistoryDay();
                historyDay.setContent(result.getContent());
                historyDay.seteId(result.getE_id());
                historyDay.setPicNo(result.getPicNo());
                historyDay.setTitle(result.getTitle());
                String id = "", title = "", url = "";
                for (HistroyDayUtils.PicUrl picUrl : result.getPicUrl()) {
                    id += picUrl.getId() + ",";
                    title += picUrl.getPic_title() + ",";
                    url += picUrl.getUrl() + ",";
                }
                historyDay.setPicId(id);
                historyDay.setTitle(title);
                historyDay.setUrl(url);
                Integer byHiatory = historyDayMapper.findByHiatory(historyDay);
                if (byHiatory == 0){
                    historyDayMapper.insterFindAll(historyDay);
                }
            }
        }
    }
}
