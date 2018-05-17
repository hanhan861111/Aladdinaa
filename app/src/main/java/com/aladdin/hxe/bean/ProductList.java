package com.aladdin.hxe.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Ruijinglin
 * @date: 2018/3/1/0001
 * Explain:      .
 */

public class ProductList implements Serializable {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":10001,"categoryId":"151641627957921","name":"招牌菜","sys_user_id":null,"token":null,"product":[{"id":8,"productId":"151642919418370","name":"蒸饺","tb_category_categoryId":10001,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":2,"createTime":"2018-01-20 14:19:54","updateTime":"2018-01-20 14:19:54","shelvesTime":"2018-01-20 14:19:54","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null},{"id":9,"productId":"151642929115073","name":"大份黄焖鸡","tb_category_categoryId":10001,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":2,"createTime":"2018-01-20 14:21:31","updateTime":"2018-01-20 14:21:31","shelvesTime":"2018-01-20 14:21:31","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null}]},{"id":10002,"categoryId":"151641627957923","name":"主食","sys_user_id":null,"token":null,"product":[{"id":10,"productId":"151642931358965","name":"中份黄焖鸡","tb_category_categoryId":10002,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":2,"createTime":"2018-01-20 14:21:53","updateTime":"2018-01-20 14:21:53","shelvesTime":"2018-01-20 14:21:53","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null},{"id":11,"productId":"151642933912698","name":"炸鸡+啤酒","tb_category_categoryId":10002,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":1,"createTime":"2018-01-20 14:22:19","updateTime":"2018-01-20 14:22:19","shelvesTime":"2018-01-20 14:22:19","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null}]},{"id":10003,"categoryId":"151641627957924","name":"小吃","sys_user_id":null,"token":null,"product":[]},{"id":100006,"categoryId":"151781721173725","name":"饮品","sys_user_id":null,"token":null,"product":[]},{"id":100008,"categoryId":"151781721173727","name":"凉菜","sys_user_id":null,"token":null,"product":[{"id":23,"productId":"151781803981818","name":"大盘鸡123","tb_category_categoryId":100008,"productCount":99123,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"img/a2.jpg","imageUrl1":null,"imageUrl2":null,"status":1,"createTime":"2018-02-05 16:07:20","updateTime":"2018-02-05 17:17:05","shelvesTime":"2018-02-05 16:07:20","sys_user_id":4,"des":"无123","data1":null,"data2":null,"data3":null,"token":null}]}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
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

    public static class DataBean implements Serializable {
        /**
         * id : 10001
         * categoryId : 151641627957921
         * name : 招牌菜
         * sys_user_id : null
         * token : null
         * product : [{"id":8,"productId":"151642919418370","name":"蒸饺","tb_category_categoryId":10001,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":2,"createTime":"2018-01-20 14:19:54","updateTime":"2018-01-20 14:19:54","shelvesTime":"2018-01-20 14:19:54","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null},{"id":9,"productId":"151642929115073","name":"大份黄焖鸡","tb_category_categoryId":10001,"productCount":null,"sellRate":null,"clickRate":null,"sellPrice":0,"sellPrice1":0,"sellPrice2":0,"activityPrice":0,"isSell":null,"isHot":null,"limit_sell_number":null,"startTime":null,"endTime":null,"imageUrl":"","imageUrl1":null,"imageUrl2":null,"status":2,"createTime":"2018-01-20 14:21:31","updateTime":"2018-01-20 14:21:31","shelvesTime":"2018-01-20 14:21:31","sys_user_id":4,"des":"","data1":null,"data2":null,"data3":null,"token":null}]
         */

        private int id;
        private String categoryId;
        private String name;
        private Object sys_user_id;
        private Object token;
        private List<ProductBean> product;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

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

        public static class ProductBean implements Serializable{
            /**
             * id : 8
             * productId : 151642919418370
             * name : 蒸饺
             * tb_category_categoryId : 10001
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
             * status : 2
             * createTime : 2018-01-20 14:19:54
             * updateTime : 2018-01-20 14:19:54
             * shelvesTime : 2018-01-20 14:19:54
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
            private String sellPrice;
            private String sellPrice1;
            private String sellPrice2;
            private String activityPrice;
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

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            private String typeName;
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

            public String getSellPrice() {
                return sellPrice;
            }

            public void setSellPrice(String sellPrice) {
                this.sellPrice = sellPrice;
            }

            public String getSellPrice1() {
                return sellPrice1;
            }

            public void setSellPrice1(String sellPrice1) {
                this.sellPrice1 = sellPrice1;
            }

            public String getSellPrice2() {
                return sellPrice2;
            }

            public void setSellPrice2(String sellPrice2) {
                this.sellPrice2 = sellPrice2;
            }

            public String getActivityPrice() {
                return activityPrice;
            }

            public void setActivityPrice(String activityPrice) {
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
}
