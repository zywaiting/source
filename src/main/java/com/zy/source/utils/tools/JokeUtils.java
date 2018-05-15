package com.zy.source.utils.tools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zy.source.utils.bean.KeyValue;
import com.zy.source.utils.httpUtils.HttpUtils;
import com.zy.source.utils.httpUtils.Utils;
import org.apache.maven.shared.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JokeUtils {

    /**
     * 请求地址：http://v.juhe.cn/joke/content/text.php
     请求参数：page=29000&pagesize=20&key=7fd0093f5384fad63053a56b920edc3d
     请求方式：GET
     * @param args
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(JokeUtils.class);

    private static String postUrl = "http://v.juhe.cn/joke/content/text.php";
    private static String key = "7fd0093f5384fad63053a56b920edc3d";


    public static Context gain(String page) {
        Context context = null;
        try {
            KeyValue[] args = new KeyValue[]{
                    new KeyValue("page", page),
                    new KeyValue("pagesize", "20"),
                    new KeyValue("key",key)
            };
            String resultStr = HttpUtils.post(postUrl, args);
            if (StringUtils.isNotEmpty(resultStr)) {
                context = Utils.json(resultStr,Context.class);
                if ("Success".equals(context.getReason())){
                    LOGGER.info("获取笑话大全数据::{}",context.getReason());
                } else if ("超过每日可允许请求次数!".equals(context.getReason())){
                    LOGGER.info("笑话大全查询结果::{}",context.getReason());
                }
               /* Result result = context.getResult();
                data = result.getData();*/
            }
        } catch (Exception e) {
            LOGGER.warn("获取笑话大全数据异常::{}", e.getMessage());
        }
        return context;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Context{
        private String reason;
        private Result result;
        private Integer error_code;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Integer getError_code() {
            return error_code;
        }

        public void setError_code(Integer error_code) {
            this.error_code = error_code;
        }
    }

    public static class Result{
        private List<Data> data;

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }

    public static class Data {
        private String content;
        private String hashId;
        private String unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public String getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(String unixtime) {
            this.unixtime = unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }


}
