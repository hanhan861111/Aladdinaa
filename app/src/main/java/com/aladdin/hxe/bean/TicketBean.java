package com.aladdin.hxe.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/3/31 0031 17:08..
 */

public class TicketBean implements Serializable{

    /**
     * status : 200
     * msg : OK
     * data : [{"printersStatus":"在线","isPag":null,"id":6,"brand":"FC","deviceNo":"671206017","name":"HC","type":"GP","status":1}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * printersStatus : 在线
         * isPag : null
         * id : 6
         * brand : FC
         * deviceNo : 671206017
         * name : HC
         * type : GP
         * status : 1
         */

        private String printersStatus;
        private Object isPag;
        private int id;
        private String brand;
        private String deviceNo;
        private String name;
        private String type;
        private int status;

        public String getPrintersStatus() {
            return printersStatus;
        }

        public void setPrintersStatus(String printersStatus) {
            this.printersStatus = printersStatus;
        }

        public Object getIsPag() {
            return isPag;
        }

        public void setIsPag(Object isPag) {
            this.isPag = isPag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
