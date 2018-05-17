package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/5/3 0003 16:51..
 */

public class InquireCancelOrderBean {

    /**
     * status : 200
     * msg : OK
     * data : {"total":1,"rows":[{"id":1741,"orderType":"APPOINTMENT","tb_customer_id":68,"sys_user_id":4,"orderNum":"D15253369193034904","totalFee":0.01,"realFee":0.01,"isCoupons":2,"couponsId":null,"couponsFee":null,"goodsCount":1,"des":"","isPay":2,"payDate":null,"payType":null,"takeNum":"4","isFefund":null,"desFefount":"","refundFee":null,"orderStatus":3,"orderStatusMsg":"暗示一下","deskNum":null,"useDate":"2018-05-03 16:41:59","createTime":"2018-05-03 16:41:59","updateTime":"2018-05-03 16:42:06","printerTime":1525336619000,"isAppraise":1,"status":"1","data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[{"id":2199,"orderNum":"D15253369193034904","goodsId":"1717","goodsNum":1,"goodsPrice":0.01,"goodsName":"大饼","token":null,"data1":null,"data2":null,"data3":null}],"phone":"13783948535","nickName":"123","logoImage":null,"shopSign":null,"address":null}]}
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
         * total : 1
         * rows : [{"id":1741,"orderType":"APPOINTMENT","tb_customer_id":68,"sys_user_id":4,"orderNum":"D15253369193034904","totalFee":0.01,"realFee":0.01,"isCoupons":2,"couponsId":null,"couponsFee":null,"goodsCount":1,"des":"","isPay":2,"payDate":null,"payType":null,"takeNum":"4","isFefund":null,"desFefount":"","refundFee":null,"orderStatus":3,"orderStatusMsg":"暗示一下","deskNum":null,"useDate":"2018-05-03 16:41:59","createTime":"2018-05-03 16:41:59","updateTime":"2018-05-03 16:42:06","printerTime":1525336619000,"isAppraise":1,"status":"1","data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[{"id":2199,"orderNum":"D15253369193034904","goodsId":"1717","goodsNum":1,"goodsPrice":0.01,"goodsName":"大饼","token":null,"data1":null,"data2":null,"data3":null}],"phone":"13783948535","nickName":"123","logoImage":null,"shopSign":null,"address":null}]
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
             * id : 1741
             * orderType : APPOINTMENT
             * tb_customer_id : 68
             * sys_user_id : 4
             * orderNum : D15253369193034904
             * totalFee : 0.01
             * realFee : 0.01
             * isCoupons : 2
             * couponsId : null
             * couponsFee : null
             * goodsCount : 1
             * des :
             * isPay : 2
             * payDate : null
             * payType : null
             * takeNum : 4
             * isFefund : null
             * desFefount :
             * refundFee : null
             * orderStatus : 3
             * orderStatusMsg : 暗示一下
             * deskNum : null
             * useDate : 2018-05-03 16:41:59
             * createTime : 2018-05-03 16:41:59
             * updateTime : 2018-05-03 16:42:06
             * printerTime : 1525336619000
             * isAppraise : 1
             * status : 1
             * data1 : null
             * data2 : null
             * data3 : null
             * token : null
             * ordersProducts : [{"id":2199,"orderNum":"D15253369193034904","goodsId":"1717","goodsNum":1,"goodsPrice":0.01,"goodsName":"大饼","token":null,"data1":null,"data2":null,"data3":null}]
             * phone : 13783948535
             * nickName : 123
             * logoImage : null
             * shopSign : null
             * address : null
             */

            private int id;
            private String orderType;
            private int tb_customer_id;
            private int sys_user_id;
            private String orderNum;
            private double totalFee;
            private double realFee;
            private int isCoupons;
            private Object couponsId;
            private Object couponsFee;
            private int goodsCount;
            private String des;
            private int isPay;
            private Object payDate;
            private Object payType;
            private String takeNum;
            private Object isFefund;
            private String desFefount;
            private Object refundFee;
            private int orderStatus;
            private String orderStatusMsg;
            private Object deskNum;
            private String useDate;
            private String createTime;
            private String updateTime;
            private long printerTime;
            private int isAppraise;
            private String status;
            private Object data1;
            private Object data2;
            private Object data3;
            private Object token;
            private String phone;
            private String nickName;
            private Object logoImage;
            private Object shopSign;
            private Object address;
            private List<OrdersProductsBean> ordersProducts;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public int getTb_customer_id() {
                return tb_customer_id;
            }

            public void setTb_customer_id(int tb_customer_id) {
                this.tb_customer_id = tb_customer_id;
            }

            public int getSys_user_id() {
                return sys_user_id;
            }

            public void setSys_user_id(int sys_user_id) {
                this.sys_user_id = sys_user_id;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public double getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(double totalFee) {
                this.totalFee = totalFee;
            }

            public double getRealFee() {
                return realFee;
            }

            public void setRealFee(double realFee) {
                this.realFee = realFee;
            }

            public int getIsCoupons() {
                return isCoupons;
            }

            public void setIsCoupons(int isCoupons) {
                this.isCoupons = isCoupons;
            }

            public Object getCouponsId() {
                return couponsId;
            }

            public void setCouponsId(Object couponsId) {
                this.couponsId = couponsId;
            }

            public Object getCouponsFee() {
                return couponsFee;
            }

            public void setCouponsFee(Object couponsFee) {
                this.couponsFee = couponsFee;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public int getIsPay() {
                return isPay;
            }

            public void setIsPay(int isPay) {
                this.isPay = isPay;
            }

            public Object getPayDate() {
                return payDate;
            }

            public void setPayDate(Object payDate) {
                this.payDate = payDate;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public String getTakeNum() {
                return takeNum;
            }

            public void setTakeNum(String takeNum) {
                this.takeNum = takeNum;
            }

            public Object getIsFefund() {
                return isFefund;
            }

            public void setIsFefund(Object isFefund) {
                this.isFefund = isFefund;
            }

            public String getDesFefount() {
                return desFefount;
            }

            public void setDesFefount(String desFefount) {
                this.desFefount = desFefount;
            }

            public Object getRefundFee() {
                return refundFee;
            }

            public void setRefundFee(Object refundFee) {
                this.refundFee = refundFee;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderStatusMsg() {
                return orderStatusMsg;
            }

            public void setOrderStatusMsg(String orderStatusMsg) {
                this.orderStatusMsg = orderStatusMsg;
            }

            public Object getDeskNum() {
                return deskNum;
            }

            public void setDeskNum(Object deskNum) {
                this.deskNum = deskNum;
            }

            public String getUseDate() {
                return useDate;
            }

            public void setUseDate(String useDate) {
                this.useDate = useDate;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public long getPrinterTime() {
                return printerTime;
            }

            public void setPrinterTime(long printerTime) {
                this.printerTime = printerTime;
            }

            public int getIsAppraise() {
                return isAppraise;
            }

            public void setIsAppraise(int isAppraise) {
                this.isAppraise = isAppraise;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getData1() {
                return data1;
            }

            public void setData1(Object data1) {
                this.data1 = data1;
            }

            public Object getData2() {
                return data2;
            }

            public void setData2(Object data2) {
                this.data2 = data2;
            }

            public Object getData3() {
                return data3;
            }

            public void setData3(Object data3) {
                this.data3 = data3;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public Object getLogoImage() {
                return logoImage;
            }

            public void setLogoImage(Object logoImage) {
                this.logoImage = logoImage;
            }

            public Object getShopSign() {
                return shopSign;
            }

            public void setShopSign(Object shopSign) {
                this.shopSign = shopSign;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public List<OrdersProductsBean> getOrdersProducts() {
                return ordersProducts;
            }

            public void setOrdersProducts(List<OrdersProductsBean> ordersProducts) {
                this.ordersProducts = ordersProducts;
            }

            public static class OrdersProductsBean {
                /**
                 * id : 2199
                 * orderNum : D15253369193034904
                 * goodsId : 1717
                 * goodsNum : 1
                 * goodsPrice : 0.01
                 * goodsName : 大饼
                 * token : null
                 * data1 : null
                 * data2 : null
                 * data3 : null
                 */

                private int id;
                private String orderNum;
                private String goodsId;
                private int goodsNum;
                private double goodsPrice;
                private String goodsName;
                private Object token;
                private Object data1;
                private Object data2;
                private Object data3;

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

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
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

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public Object getToken() {
                    return token;
                }

                public void setToken(Object token) {
                    this.token = token;
                }

                public Object getData1() {
                    return data1;
                }

                public void setData1(Object data1) {
                    this.data1 = data1;
                }

                public Object getData2() {
                    return data2;
                }

                public void setData2(Object data2) {
                    this.data2 = data2;
                }

                public Object getData3() {
                    return data3;
                }

                public void setData3(Object data3) {
                    this.data3 = data3;
                }
            }
        }
    }
}
