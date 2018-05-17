package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/3/1 0001 10:53..
 */

public class TeamBean {

    /**
     * id : 10003
     * categoryId : 151641627957925
     * name : 饮料2
     * sys_user_id : null
     * token : null
     * product : [{"id":11,"productId":"151642933912698","name":"啤酒5","tb_category_categoryId":10003,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":1,"createTime":"2018-01-20 14:22:19","updateTime":"2018-01-20 14:22:19","shelvesTime":"2018-01-20 14:22:19","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null}]
     */

    private int id;
    private String categoryId;
    private String name;
    private Object sys_user_id;
    private Object token;
    private List<ProductBean> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSys_user_id() {
        return sys_user_id;
    }

    public void setSys_user_id(Object sys_user_id) {
        this.sys_user_id = sys_user_id;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * id : 11
         * productId : 151642933912698
         * name : 啤酒5
         * tb_category_categoryId : 10003
         * productCount : null
         * sellRate : null
         * clickRate : null
         * sellPrice : 0
         * sellPrice1 : 0
         * sellPrice2 : 0
         * activityPrice : 0
         * isSell : null
         * isHot : null
         * limit_sell_number : null
         * startTime : null
         * endTime : null
         * imageUrl :
         * imageUrl1 : null
         * imageUrl2 : null
         * status : 1
         * createTime : 2018-01-20 14:22:19
         * updateTime : 2018-01-20 14:22:19
         * shelvesTime : 2018-01-20 14:22:19
         * sys_user_id : 4
         * des :
         * data1 : null
         * data2 : null
         * data3 : null
         * token : null
         */

        private int id;
        private String productId;
        private String name;
        private int tb_category_categoryId;
        private Object productCount;
        private Object sellRate;
        private Object clickRate;
        private int sellPrice;
        private int sellPrice1;
        private int sellPrice2;
        private int activityPrice;
        private Object isSell;
        private Object isHot;
        private Object limit_sell_number;
        private Object startTime;
        private Object endTime;
        private String imageUrl;
        private Object imageUrl1;
        private Object imageUrl2;
        private int status;
        private String createTime;
        private String updateTime;
        private String shelvesTime;
        private int sys_user_id;
        private String des;
        private Object data1;
        private Object data2;
        private Object data3;
        private Object token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTb_category_categoryId() {
            return tb_category_categoryId;
        }

        public void setTb_category_categoryId(int tb_category_categoryId) {
            this.tb_category_categoryId = tb_category_categoryId;
        }

        public Object getProductCount() {
            return productCount;
        }

        public void setProductCount(Object productCount) {
            this.productCount = productCount;
        }

        public Object getSellRate() {
            return sellRate;
        }

        public void setSellRate(Object sellRate) {
            this.sellRate = sellRate;
        }

        public Object getClickRate() {
            return clickRate;
        }

        public void setClickRate(Object clickRate) {
            this.clickRate = clickRate;
        }

        public int getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
        }

        public int getSellPrice1() {
            return sellPrice1;
        }

        public void setSellPrice1(int sellPrice1) {
            this.sellPrice1 = sellPrice1;
        }

        public int getSellPrice2() {
            return sellPrice2;
        }

        public void setSellPrice2(int sellPrice2) {
            this.sellPrice2 = sellPrice2;
        }

        public int getActivityPrice() {
            return activityPrice;
        }

        public void setActivityPrice(int activityPrice) {
            this.activityPrice = activityPrice;
        }

        public Object getIsSell() {
            return isSell;
        }

        public void setIsSell(Object isSell) {
            this.isSell = isSell;
        }

        public Object getIsHot() {
            return isHot;
        }

        public void setIsHot(Object isHot) {
            this.isHot = isHot;
        }

        public Object getLimit_sell_number() {
            return limit_sell_number;
        }

        public void setLimit_sell_number(Object limit_sell_number) {
            this.limit_sell_number = limit_sell_number;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Object getImageUrl1() {
            return imageUrl1;
        }

        public void setImageUrl1(Object imageUrl1) {
            this.imageUrl1 = imageUrl1;
        }

        public Object getImageUrl2() {
            return imageUrl2;
        }

        public void setImageUrl2(Object imageUrl2) {
            this.imageUrl2 = imageUrl2;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getShelvesTime() {
            return shelvesTime;
        }

        public void setShelvesTime(String shelvesTime) {
            this.shelvesTime = shelvesTime;
        }

        public int getSys_user_id() {
            return sys_user_id;
        }

        public void setSys_user_id(int sys_user_id) {
            this.sys_user_id = sys_user_id;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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
    }
}
