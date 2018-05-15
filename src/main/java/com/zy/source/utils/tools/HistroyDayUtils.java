package com.zy.source.utils.tools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zy.source.utils.bean.KeyValue;
import com.zy.source.utils.httpUtils.HttpUtils;
import com.zy.source.utils.httpUtils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.maven.shared.utils.StringUtils;

import java.util.List;

/**
 * Created by zy on 15/05/2018.
 *
 * 获取聚合数据的历史的今天
 */
public class HistroyDayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistroyDayUtils.class);

    private static final String URL = "http://v.juhe.cn/todayOnhistory/queryDetail.php";
    private static final String KEY = "2388eea6f5ded4580190dd21b9c42521";

    public static void main(String[] args) {
        histroyDay("1");
    }

    public static Context histroyDay(String e_id){
        Context context = null;
        try {
            KeyValue[] args = new KeyValue[]{
                    new KeyValue("key", KEY),
                    new KeyValue("e_id", e_id)
            };
            String json = HttpUtils.post(URL, args);
            if (StringUtils.isNotEmpty(json)) {
                context = Utils.json(json,Context.class);
                if ("success".equals(context.getReason())){
                    LOGGER.info("获取历史今天数据::{}",context.getReason());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("获取历史今天数据异常::{}", e.getMessage());
        }
        return context;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Context{
        private String reason;
        @JsonProperty("result")
        private Result[] result;
        private String error_code;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Result[] getResult() {
            return result;
        }

        public void setResult(Result[] result) {
            this.result = result;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result{
        private String e_id;
        private String title;
        private String content;
        private String picNo;
        private List<PicUrl> picUrl;

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicNo() {
            return picNo;
        }

        public void setPicNo(String picNo) {
            this.picNo = picNo;
        }

        public List<PicUrl> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrl> picUrl) {
            this.picUrl = picUrl;
        }
    }

    public static class PicUrl{
        private String pic_title;
        private String id;
        private String url;

        public String getPic_title() {
            return pic_title;
        }

        public void setPic_title(String pic_title) {
            this.pic_title = pic_title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
