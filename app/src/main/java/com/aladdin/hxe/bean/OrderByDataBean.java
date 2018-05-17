package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/3/14 0014 21:37..
 */

public class OrderByDataBean {

    /**
     * data : {"rows":[{"createTime":"2018-03-14 14:50:38","des":"不放香菜 ","goodsCount":1,"id":221,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1521010237823445","orderStatus":2,"ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":389,"orderNum":"D1521010237823445"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"4","tb_customer_id":55,"totalFee":0.01,"updateTime":"2018-03-14 14:50:54","useDate":"2018-03-14 15:10:37","orderStatusMsg":""},{"createTime":"2018-03-14 09:58:26","des":"","goodsCount":1,"id":198,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520992706063422","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":368,"orderNum":"D1520992706063422"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"3","tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:58:31","useDate":"2018-03-14 10:13:26"},{"createTime":"2018-03-14 09:44:32","des":"","goodsCount":2,"id":196,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520991871796420","orderStatus":2,"orderStatusMsg":"修改订单状态","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":2,"goodsPrice":0.01,"id":367,"orderNum":"D1520991871796420"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.02,"updateTime":"2018-03-14 09:44:37","useDate":"2018-03-14 09:59:31"},{"createTime":"2018-03-14 09:26:25","des":"","goodsCount":1,"id":195,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520990784782419","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":366,"orderNum":"D1520990784782419"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:26:54","useDate":"2018-03-14 09:41:24"},{"createTime":"2018-03-14 09:12:03","des":"","goodsCount":1,"id":194,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989922905418","orderStatus":2,"orderStatusMsg":"修改订单状态","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":365,"orderNum":"D1520989922905418"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:12:19","useDate":"2018-03-14 09:27:02"},{"createTime":"2018-03-14 09:07:01","des":"不放香菜 不放香菜 ","goodsCount":1,"id":193,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989620960417","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":364,"orderNum":"D1520989620960417"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:07:32","useDate":"2018-03-14 09:22:00"},{"createTime":"2018-03-14 09:00:27","des":"不要辣 ","goodsCount":1,"id":192,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989226845416","orderStatus":2,"ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":363,"orderNum":"D1520989226845416"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"2","tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:00:31","useDate":"2018-03-14 09:15:26"},{"createTime":"2018-03-14 08:57:05","des":"","goodsCount":1,"id":191,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989024577415","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":362,"orderNum":"D1520989024577415"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 08:57:09","useDate":"2018-03-14 09:12:04"}],"total":8}
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
         * rows : [{"createTime":"2018-03-14 14:50:38","des":"不放香菜 ","goodsCount":1,"id":221,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1521010237823445","orderStatus":2,"ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":389,"orderNum":"D1521010237823445"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"4","tb_customer_id":55,"totalFee":0.01,"updateTime":"2018-03-14 14:50:54","useDate":"2018-03-14 15:10:37"},{"createTime":"2018-03-14 09:58:26","des":"","goodsCount":1,"id":198,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520992706063422","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":368,"orderNum":"D1520992706063422"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"3","tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:58:31","useDate":"2018-03-14 10:13:26"},{"createTime":"2018-03-14 09:44:32","des":"","goodsCount":2,"id":196,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520991871796420","orderStatus":2,"orderStatusMsg":"修改订单状态","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":2,"goodsPrice":0.01,"id":367,"orderNum":"D1520991871796420"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.02,"updateTime":"2018-03-14 09:44:37","useDate":"2018-03-14 09:59:31"},{"createTime":"2018-03-14 09:26:25","des":"","goodsCount":1,"id":195,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520990784782419","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":366,"orderNum":"D1520990784782419"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:26:54","useDate":"2018-03-14 09:41:24"},{"createTime":"2018-03-14 09:12:03","des":"","goodsCount":1,"id":194,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989922905418","orderStatus":2,"orderStatusMsg":"修改订单状态","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":365,"orderNum":"D1520989922905418"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:12:19","useDate":"2018-03-14 09:27:02"},{"createTime":"2018-03-14 09:07:01","des":"不放香菜 不放香菜 ","goodsCount":1,"id":193,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989620960417","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":364,"orderNum":"D1520989620960417"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:07:32","useDate":"2018-03-14 09:22:00"},{"createTime":"2018-03-14 09:00:27","des":"不要辣 ","goodsCount":1,"id":192,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989226845416","orderStatus":2,"ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":363,"orderNum":"D1520989226845416"}],"realFee":0.01,"status":"1","sys_user_id":4,"takeNum":"2","tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 09:00:31","useDate":"2018-03-14 09:15:26"},{"createTime":"2018-03-14 08:57:05","des":"","goodsCount":1,"id":191,"isAppraise":1,"isCoupons":2,"isPay":2,"orderNum":"D1520989024577415","orderStatus":2,"orderStatusMsg":"","ordersProducts":[{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":362,"orderNum":"D1520989024577415"}],"realFee":0.01,"status":"1","sys_user_id":4,"tb_customer_id":58,"totalFee":0.01,"updateTime":"2018-03-14 08:57:09","useDate":"2018-03-14 09:12:04"}]
         * total : 8
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
             * createTime : 2018-03-14 14:50:38
             * des : 不放香菜
             * goodsCount : 1
             * id : 221
             * isAppraise : 1
             * isCoupons : 2
             * isPay : 2
             * orderNum : D1521010237823445
             * orderStatus : 2
             * ordersProducts : [{"goodsId":"11","goodsName":"铁板杏鲍菇","goodsNum":1,"goodsPrice":0.01,"id":389,"orderNum":"D1521010237823445"}]
             * realFee : 0.01
             * status : 1
             * sys_user_id : 4
             * takeNum : 4
             * tb_customer_id : 55
             * totalFee : 0.01
             * updateTime : 2018-03-14 14:50:54
             * useDate : 2018-03-14 15:10:37
             * orderStatusMsg :
             */

            private String createTime;
            private String des;
            private int goodsCount;
            private int id;
            private int isAppraise;
            private int isCoupons;
            private int isPay;
            private String orderNum;
            private int orderStatus;
            private double realFee;
            private String status;
            private int sys_user_id;
            private String takeNum;
            private int tb_customer_id;
            private double totalFee;
            private String updateTime;
            private String useDate;
            private String orderStatusMsg;
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

            public String getOrderStatusMsg() {
                return orderStatusMsg;
            }

            public void setOrderStatusMsg(String orderStatusMsg) {
                this.orderStatusMsg = orderStatusMsg;
            }

            public List<OrdersProductsBean> getOrdersProducts() {
                return ordersProducts;
            }

            public void setOrdersProducts(List<OrdersProductsBean> ordersProducts) {
                this.ordersProducts = ordersProducts;
            }

            public static class OrdersProductsBean {
                /**
                 * goodsId : 11
                 * goodsName : 铁板杏鲍菇
                 * goodsNum : 1
                 * goodsPrice : 0.01
                 * id : 389
                 * orderNum : D1521010237823445
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
