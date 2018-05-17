package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/2/26 0026 20:16..
 */

public class FindallevaluationBean {


    /**
     * status : 200
     * msg : OK
     * data : {"shopscore":"4.00","sumscore":"4.50","productscore":"5.00","evaluationlist":{"total":2,"rows":[{"id":1,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"谢谢支持","images":"a1.jpg","isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-09-09 00:00:00","token":null,"phone":"17303823668","nickName":"小刘","userImage":"img/a5.jpg"},{"id":2,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"那是必须的","images":null,"isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-02-07 16:15:17","token":null,"phone":"17303823608","nickName":"拐角处等候","userImage":"img/a5.jpg"}]}}
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
         * shopscore : 4.00
         * sumscore : 4.50
         * productscore : 5.00
         * evaluationlist : {"total":2,"rows":[{"id":1,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"谢谢支持","images":"a1.jpg","isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-09-09 00:00:00","token":null,"phone":"17303823668","nickName":"小刘","userImage":"img/a5.jpg"},{"id":2,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"那是必须的","images":null,"isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-02-07 16:15:17","token":null,"phone":"17303823608","nickName":"拐角处等候","userImage":"img/a5.jpg"}]}
         */

        private String shopscore;
        private String sumscore;
        private String productscore;
        private EvaluationlistBean evaluationlist;

        public String getShopscore() {
            return shopscore;
        }

        public void setShopscore(String shopscore) {
            this.shopscore = shopscore;
        }

        public String getSumscore() {
            return sumscore;
        }

        public void setSumscore(String sumscore) {
            this.sumscore = sumscore;
        }

        public String getProductscore() {
            return productscore;
        }

        public void setProductscore(String productscore) {
            this.productscore = productscore;
        }

        public EvaluationlistBean getEvaluationlist() {
            return evaluationlist;
        }

        public void setEvaluationlist(EvaluationlistBean evaluationlist) {
            this.evaluationlist = evaluationlist;
        }

        public static class EvaluationlistBean {
            /**
             * total : 2
             * rows : [{"id":1,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"谢谢支持","images":"a1.jpg","isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-09-09 00:00:00","token":null,"phone":"17303823668","nickName":"小刘","userImage":"img/a5.jpg"},{"id":2,"shopId":4,"orderId":null,"userId":null,"shopScore":4,"productScore":5,"content":"此店的小吃好好吃1","shopReply":"那是必须的","images":null,"isShow":1,"createTime":"2018-02-03 16:53:26","replyTime":"2018-02-07 16:15:17","token":null,"phone":"17303823608","nickName":"拐角处等候","userImage":"img/a5.jpg"}]
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
                 * id : 1
                 * shopId : 4
                 * orderId : null
                 * userId : null
                 * shopScore : 4
                 * productScore : 5
                 * content : 此店的小吃好好吃1
                 * shopReply : 谢谢支持
                 * images : a1.jpg
                 * isShow : 1
                 * createTime : 2018-02-03 16:53:26
                 * replyTime : 2018-09-09 00:00:00
                 * token : null
                 * phone : 17303823668
                 * nickName : 小刘
                 * userImage : img/a5.jpg
                 */

                private int id;
                private int shopId;
                private Object orderId;
                private Object userId;
                private int shopScore;
                private int productScore;
                private String content;
                private String shopReply;
                private String images;
                private int isShow;
                private String createTime;
                private String replyTime;
                private Object token;
                private String phone;
                private String nickName;
                private String userImage;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getShopId() {
                    return shopId;
                }

                public void setShopId(int shopId) {
                    this.shopId = shopId;
                }

                public Object getOrderId() {
                    return orderId;
                }

                public void setOrderId(Object orderId) {
                    this.orderId = orderId;
                }

                public Object getUserId() {
                    return userId;
                }

                public void setUserId(Object userId) {
                    this.userId = userId;
                }

                public int getShopScore() {
                    return shopScore;
                }

                public void setShopScore(int shopScore) {
                    this.shopScore = shopScore;
                }

                public int getProductScore() {
                    return productScore;
                }

                public void setProductScore(int productScore) {
                    this.productScore = productScore;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getShopReply() {
                    return shopReply;
                }

                public void setShopReply(String shopReply) {
                    this.shopReply = shopReply;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public int getIsShow() {
                    return isShow;
                }

                public void setIsShow(int isShow) {
                    this.isShow = isShow;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getReplyTime() {
                    return replyTime;
                }

                public void setReplyTime(String replyTime) {
                    this.replyTime = replyTime;
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

                public String getUserImage() {
                    return userImage;
                }

                public void setUserImage(String userImage) {
                    this.userImage = userImage;
                }
            }
        }
    }
}
