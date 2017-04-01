package com.zd.android.deam.mvp.bean;

import java.util.List;

/**
 * Created by suzy on 2017/3/27.
 **/

public class HistoryInfo {

    /**
     * msg : success
     * result : [{"date":"18620327","day":27,"event":"　content "}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * date : 18620327
         * day : 27
         * event :  content
         * id : 569881b1590146d407331cdb
         * month : 3
         * title : 美商旗昌轮船公司在上海成立
         */

        private String date;
        private int day;
        private String event;
        private String id;
        private int month;
        private String title;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "date='" + date + '\'' +
                    ", day=" + day +
                    ", event='" + event + '\'' +
                    ", id='" + id + '\'' +
                    ", month=" + month +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HistoryInfo{" +
                "msg='" + msg + '\'' +
                ", retCode='" + retCode + '\'' +
                ", result=" + result +
                '}';
    }
}
