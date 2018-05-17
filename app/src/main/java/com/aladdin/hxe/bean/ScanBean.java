package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/5/5 0005 10:57..
 */

public class ScanBean  {

    /**
     * status : 200
     * msg : OK
     * data : {"total":2,"rows":[{"id":1829,"orderType":"QR","tb_customer_id":null,"sys_user_id":4,"orderNum":"D152548800245541003","totalFee":null,"realFee":0.01,"isCoupons":null,"couponsId":null,"couponsFee":null,"goodsCount":null,"des":null,"isPay":2,"payDate":null,"payType":2,"takeNum":null,"isFefund":null,"desFefount":null,"refundFee":null,"orderStatus":6,"orderStatusMsg":null,"deskNum":null,"useDate":null,"createTime":"2018-05-05 10:40:02","updateTime":"2018-05-05 10:40:21","printerTime":null,"isAppraise":null,"status":null,"data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[],"phone":null,"nickName":null,"logoImage":null,"shopSign":null,"address":null},{"id":1827,"orderType":"QR","tb_customer_id":null,"sys_user_id":4,"orderNum":"D152548608956941001","totalFee":null,"realFee":0.01,"isCoupons":null,"couponsId":null,"couponsFee":null,"goodsCount":null,"des":null,"isPay":2,"payDate":null,"payType":2,"takeNum":null,"isFefund":null,"desFefount":null,"refundFee":null,"orderStatus":6,"orderStatusMsg":null,"deskNum":null,"useDate":null,"createTime":"2018-05-05 10:08:10","updateTime":"2018-05-05 10:15:40","printerTime":null,"isAppraise":null,"status":null,"data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[],"phone":null,"nickName":null,"logoImage":null,"shopSign":null,"address":null}]}
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
         * total : 2
         * rows : [{"id":1829,"orderType":"QR","tb_customer_id":null,"sys_user_id":4,"orderNum":"D152548800245541003","totalFee":null,"realFee":0.01,"isCoupons":null,"couponsId":null,"couponsFee":null,"goodsCount":null,"des":null,"isPay":2,"payDate":null,"payType":2,"takeNum":null,"isFefund":null,"desFefount":null,"refundFee":null,"orderStatus":6,"orderStatusMsg":null,"deskNum":null,"useDate":null,"createTime":"2018-05-05 10:40:02","updateTime":"2018-05-05 10:40:21","printerTime":null,"isAppraise":null,"status":null,"data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[],"phone":null,"nickName":null,"logoImage":null,"shopSign":null,"address":null},{"id":1827,"orderType":"QR","tb_customer_id":null,"sys_user_id":4,"orderNum":"D152548608956941001","totalFee":null,"realFee":0.01,"isCoupons":null,"couponsId":null,"couponsFee":null,"goodsCount":null,"des":null,"isPay":2,"payDate":null,"payType":2,"takeNum":null,"isFefund":null,"desFefount":null,"refundFee":null,"orderStatus":6,"orderStatusMsg":null,"deskNum":null,"useDate":null,"createTime":"2018-05-05 10:08:10","updateTime":"2018-05-05 10:15:40","printerTime":null,"isAppraise":null,"status":null,"data1":null,"data2":null,"data3":null,"token":null,"ordersProducts":[],"phone":null,"nickName":null,"logoImage":null,"shopSign":null,"address":null}]
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
             * id : 1829
             * orderType : QR
             * tb_customer_id : null
             * sys_user_id : 4
             * orderNum : D152548800245541003
             * totalFee : null
             * realFee : 0.01
             * isCoupons : null
             * couponsId : null
             * couponsFee : null
             * goodsCount : null
             * des : null
             * isPay : 2
             * payDate : null
             * payType : 2
             * takeNum : null
             * isFefund : null
             * desFefount : null
             * refundFee : null
             * orderStatus : 6
             * orderStatusMsg : null
             * deskNum : null
             * useDate : null
             * createTime : 2018-05-05 10:40:02
             * updateTime : 2018-05-05 10:40:21
             * printerTime : null
             * isAppraise : null
             * status : null
             * data1 : null
             * data2 : null
             * data3 : null
             * token : null
             * ordersProducts : []
             * phone : null
             * nickName : null
             * logoImage : null
             * shopSign : null
             * address : null
             */

            private int id;
            private String orderType;
            private Object tb_customer_id;
            private int sys_user_id;
            private String orderNum;
            private Object totalFee;
            private double realFee;
            private Object isCoupons;
            private Object couponsId;
            private Object couponsFee;
            private Object goodsCount;
            private Object des;
            private int isPay;
            private Object payDate;
            private int payType;
            private Object takeNum;
            private Object isFefund;
            private Object desFefount;
            private Object refundFee;
            private int orderStatus;
            private Object orderStatusMsg;
            private Object deskNum;
            private Object useDate;
            private String createTime;
            private String updateTime;
            private Object printerTime;
            private Object isAppraise;
            private Object status;
            private Object data1;
            private Object data2;
            private Object data3;
            private Object token;
            private Object phone;
            private Object nickName;
            private Object logoImage;
            private Object shopSign;
            private Object address;
            private List<?> ordersProducts;

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

            public Object getTb_customer_id() {
                return tb_customer_id;
            }

            public void setTb_customer_id(Object tb_customer_id) {
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

            public Object getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(Object totalFee) {
                this.totalFee = totalFee;
            }

            public double getRealFee() {
                return realFee;
            }

            public void setRealFee(double realFee) {
                this.realFee = realFee;
            }

            public Object getIsCoupons() {
                return isCoupons;
            }

            public void setIsCoupons(Object isCoupons) {
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

            public Object getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(Object goodsCount) {
                this.goodsCount = goodsCount;
            }

            public Object getDes() {
                return des;
            }

            public void setDes(Object des) {
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

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public Object getTakeNum() {
                return takeNum;
            }

            public void setTakeNum(Object takeNum) {
                this.takeNum = takeNum;
            }

            public Object getIsFefund() {
                return isFefund;
            }

            public void setIsFefund(Object isFefund) {
                this.isFefund = isFefund;
            }

            public Object getDesFefount() {
                return desFefount;
            }

            public void setDesFefount(Object desFefount) {
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

            public Object getOrderStatusMsg() {
                return orderStatusMsg;
            }

            public void setOrderStatusMsg(Object orderStatusMsg) {
                this.orderStatusMsg = orderStatusMsg;
            }

            public Object getDeskNum() {
                return deskNum;
            }

            public void setDeskNum(Object deskNum) {
                this.deskNum = deskNum;
            }

            public Object getUseDate() {
                return useDate;
            }

            public void setUseDate(Object useDate) {
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

            public Object getPrinterTime() {
                return printerTime;
            }

            public void setPrinterTime(Object printerTime) {
                this.printerTime = printerTime;
            }

            public Object getIsAppraise() {
                return isAppraise;
            }

            public void setIsAppraise(Object isAppraise) {
                this.isAppraise = isAppraise;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
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

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
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

            public List<?> getOrdersProducts() {
                return ordersProducts;
            }

            public void setOrdersProducts(List<?> ordersProducts) {
                this.ordersProducts = ordersProducts;
            }
        }
    }
}
