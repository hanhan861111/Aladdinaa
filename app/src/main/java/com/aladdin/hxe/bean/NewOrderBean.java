package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/2/6 0006 10:24..
 */

public class NewOrderBean {


    /**
     * data : {"rows":[{"createTime":"2018-05-11 10:04:27","des":"不放香菜 ","desFefount":"","goodsCount":1,"id":2036,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"一纸年华","orderNum":"D152600426656541218","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2478,"orderNum":"D152600426656541218"}],"payType":1,"phone":"15981923467","printerTime":1526004027000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"1","tb_customer_id":71,"totalFee":0.01,"updateTime":"2018-05-11 10:04:34","useDate":"2018-05-11 10:05:26"},{"createTime":"2018-05-11 10:09:00","des":"","desFefount":"","goodsCount":1,"id":2040,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600453991041222","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2479,"orderNum":"D152600453991041222"}],"payType":1,"phone":"13783948535","printerTime":1526004240000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"2","tb_customer_id":68,"totalFee":0.01,"updateTime":"2018-05-11 10:09:08","useDate":"2018-05-11 10:08:59"},{"createTime":"2018-05-11 10:17:17","des":"","desFefount":"","goodsCount":3,"id":2043,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600503714641225","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1889","goodsName":"测试","goodsNum":1,"goodsPrice":0.02,"id":2480,"orderNum":"D152600503714641225"},{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2481,"orderNum":"D152600503714641225"},{"goodsId":"1887","goodsName":"肉夹馍","goodsNum":1,"goodsPrice":0.01,"id":2482,"orderNum":"D152600503714641225"}],"payType":1,"phone":"13783948535","printerTime":1526004137000,"realFee":0.04,"status":"1","sys_user_id":4,"takeNum":"3","tb_customer_id":68,"totalFee":0.04,"updateTime":"2018-05-11 10:17:26","useDate":"2018-05-11 10:17:17"},{"createTime":"2018-05-11 10:18:20","des":"","desFefount":"","goodsCount":1,"id":2044,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600509968441226","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2483,"orderNum":"D152600509968441226"}],"payType":1,"phone":"13783948535","printerTime":1526004800000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"4","tb_customer_id":68,"totalFee":0.01,"updateTime":"2018-05-11 10:18:28","useDate":"2018-05-11 10:18:19"}],"total":4}
     * msg : OK
     * status : 200
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * rows : [{"createTime":"2018-05-11 10:04:27","des":"不放香菜 ","desFefount":"","goodsCount":1,"id":2036,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"一纸年华","orderNum":"D152600426656541218","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2478,"orderNum":"D152600426656541218"}],"payType":1,"phone":"15981923467","printerTime":1526004027000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"1","tb_customer_id":71,"totalFee":0.01,"updateTime":"2018-05-11 10:04:34","useDate":"2018-05-11 10:05:26"},{"createTime":"2018-05-11 10:09:00","des":"","desFefount":"","goodsCount":1,"id":2040,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600453991041222","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2479,"orderNum":"D152600453991041222"}],"payType":1,"phone":"13783948535","printerTime":1526004240000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"2","tb_customer_id":68,"totalFee":0.01,"updateTime":"2018-05-11 10:09:08","useDate":"2018-05-11 10:08:59"},{"createTime":"2018-05-11 10:17:17","des":"","desFefount":"","goodsCount":3,"id":2043,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600503714641225","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1889","goodsName":"测试","goodsNum":1,"goodsPrice":0.02,"id":2480,"orderNum":"D152600503714641225"},{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2481,"orderNum":"D152600503714641225"},{"goodsId":"1887","goodsName":"肉夹馍","goodsNum":1,"goodsPrice":0.01,"id":2482,"orderNum":"D152600503714641225"}],"payType":1,"phone":"13783948535","printerTime":1526004137000,"realFee":0.04,"status":"1","sys_user_id":4,"takeNum":"3","tb_customer_id":68,"totalFee":0.04,"updateTime":"2018-05-11 10:17:26","useDate":"2018-05-11 10:17:17"},{"createTime":"2018-05-11 10:18:20","des":"","desFefount":"","goodsCount":1,"id":2044,"isAppraise":1,"isCoupons":2,"isPay":2,"nickName":"123","orderNum":"D152600509968441226","orderStatus":2,"orderType":"APPOINTMENT","ordersProducts":[{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2483,"orderNum":"D152600509968441226"}],"payType":1,"phone":"13783948535","printerTime":1526004800000,"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"4","tb_customer_id":68,"totalFee":0.01,"updateTime":"2018-05-11 10:18:28","useDate":"2018-05-11 10:18:19"}]
         * total : 4
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * createTime : 2018-05-11 10:04:27
             * des : 不放香菜
             * desFefount :
             * goodsCount : 1
             * id : 2036
             * isAppraise : 1
             * isCoupons : 2
             * isPay : 2
             * nickName : 一纸年华
             * orderNum : D152600426656541218
             * orderStatus : 2
             * orderType : APPOINTMENT
             * ordersProducts : [{"goodsId":"1845","goodsName":"红烧肉","goodsNum":1,"goodsPrice":0.01,"id":2478,"orderNum":"D152600426656541218"}]
             * payType : 1
             * phone : 15981923467
             * printerTime : 1526004027000
             * realFee : 0.01
             * status : 1
             * sys_user_id : 4
             * takeNum : 1
             * tb_customer_id : 71
             * totalFee : 0.01
             * updateTime : 2018-05-11 10:04:34
             * useDate : 2018-05-11 10:05:26
             */

            private String createTime;
            private String des;
            private String desFefount;
            private int goodsCount;
            private int id;
            private int isAppraise;
            private int isCoupons;
            private int isPay;
            private String nickName;
            private String orderNum;
            private int orderStatus;
            private String orderType;
            private int payType;
            private String phone;
            private long printerTime;
            private double realFee;
            private String status;
            private int sys_user_id;
            private String takeNum;
            private int tb_customer_id;
            private double totalFee;
            private String updateTime;
            private String useDate;
            private List<OrdersProductsBean> ordersProducts;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getDesFefount() {
                return desFefount;
            }

            public void setDesFefount(String desFefount) {
                this.desFefount = desFefount;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsAppraise() {
                return isAppraise;
            }

            public void setIsAppraise(int isAppraise) {
                this.isAppraise = isAppraise;
            }

            public int getIsCoupons() {
                return isCoupons;
            }

            public void setIsCoupons(int isCoupons) {
                this.isCoupons = isCoupons;
            }

            public int getIsPay() {
                return isPay;
            }

            public void setIsPay(int isPay) {
                this.isPay = isPay;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getPrinterTime() {
                return printerTime;
            }

            public void setPrinterTime(long printerTime) {
                this.printerTime = printerTime;
            }

            public double getRealFee() {
                return realFee;
            }

            public void setRealFee(double realFee) {
                this.realFee = realFee;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getSys_user_id() {
                return sys_user_id;
            }

            public void setSys_user_id(int sys_user_id) {
                this.sys_user_id = sys_user_id;
            }

            public String getTakeNum() {
                return takeNum;
            }

            public void setTakeNum(String takeNum) {
                this.takeNum = takeNum;
            }

            public int getTb_customer_id() {
                return tb_customer_id;
            }

            public void setTb_customer_id(int tb_customer_id) {
                this.tb_customer_id = tb_customer_id;
            }

            public double getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(double totalFee) {
                this.totalFee = totalFee;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUseDate() {
                return useDate;
            }

            public void setUseDate(String useDate) {
                this.useDate = useDate;
            }

            public List<OrdersProductsBean> getOrdersProducts() {
                return ordersProducts;
            }

            public void setOrdersProducts(List<OrdersProductsBean> ordersProducts) {
                this.ordersProducts = ordersProducts;
            }

            public static class OrdersProductsBean {
                /**
                 * goodsId : 1845
                 * goodsName : 红烧肉
                 * goodsNum : 1
                 * goodsPrice : 0.01
                 * id : 2478
                 * orderNum : D152600426656541218
                 */

                private String goodsId;
                private String goodsName;
                private int goodsNum;
                private double goodsPrice;
                private int id;
                private String orderNum;

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public int getGoodsNum() {
                    return goodsNum;
                }

                public void setGoodsNum(int goodsNum) {
                    this.goodsNum = goodsNum;
                }

                public double getGoodsPrice() {
                    return goodsPrice;
                }

                public void setGoodsPrice(double goodsPrice) {
                    this.goodsPrice = goodsPrice;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOrderNum() {
                    return orderNum;
                }

                public void setOrderNum(String orderNum) {
                    this.orderNum = orderNum;
                }
            }
        }
    }
}
