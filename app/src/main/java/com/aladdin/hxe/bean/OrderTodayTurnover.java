package com.aladdin.hxe.bean;

/**
 * Created by ${韩永光} on on 2018/3/14 0014 21:16..
 */

public class OrderTodayTurnover {

    /**
     * status : 200
     * msg : OK
     * data : {"orderfeesum":29.7,"orderqrfeesum":"0","ordercount":3,"walletfee":8.97}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderfeesum : 29.7
         * orderqrfeesum : 0
         * ordercount : 3
         * walletfee : 8.97
         */

        private double orderfeesum;
        private String orderqrfeesum;
        private int ordercount;
        private double walletfee;

        public double getOrderfeesum() {
            return orderfeesum;
        }

        public void setOrderfeesum(double orderfeesum) {
            this.orderfeesum = orderfeesum;
        }

        public String getOrderqrfeesum() {
            return orderqrfeesum;
        }

        public void setOrderqrfeesum(String orderqrfeesum) {
            this.orderqrfeesum = orderqrfeesum;
        }

        public int getOrdercount() {
            return ordercount;
        }

        public void setOrdercount(int ordercount) {
            this.ordercount = ordercount;
        }

        public double getWalletfee() {
            return walletfee;
        }

        public void setWalletfee(double walletfee) {
            this.walletfee = walletfee;
        }
    }
}
