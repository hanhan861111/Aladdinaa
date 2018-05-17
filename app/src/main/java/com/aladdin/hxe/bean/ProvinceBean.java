package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/4/8 0008 10:15..
 */

public class ProvinceBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"provinceId":"010000","provinceName":"北京"},{"provinceId":"020000","provinceName":"天津"},{"provinceId":"030000","provinceName":"河北省"},{"provinceId":"040000","provinceName":"山西省"},{"provinceId":"050000","provinceName":"内蒙古自治区"},{"provinceId":"060000","provinceName":"辽宁省"},{"provinceId":"070000","provinceName":"吉林省"},{"provinceId":"080000","provinceName":"黑龙江省"},{"provinceId":"090000","provinceName":"上海"},{"provinceId":"100000","provinceName":"江苏省"},{"provinceId":"110000","provinceName":"浙江省"},{"provinceId":"120000","provinceName":"安徽省"},{"provinceId":"130000","provinceName":"福建省"},{"provinceId":"140000","provinceName":"江西省"},{"provinceId":"150000","provinceName":"山东省"},{"provinceId":"160000","provinceName":"河南省"},{"provinceId":"170000","provinceName":"湖北省"},{"provinceId":"180000","provinceName":"湖南省"},{"provinceId":"190000","provinceName":"广东省"},{"provinceId":"200000","provinceName":"广西壮族自治区"},{"provinceId":"210000","provinceName":"海南省"},{"provinceId":"220000","provinceName":"重庆"},{"provinceId":"230000","provinceName":"四川省"},{"provinceId":"240000","provinceName":"贵州省"},{"provinceId":"250000","provinceName":"云南省"},{"provinceId":"260000","provinceName":"西藏自治区"},{"provinceId":"270000","provinceName":"陕西省"},{"provinceId":"280000","provinceName":"甘肃省"},{"provinceId":"290000","provinceName":"青海省"},{"provinceId":"300000","provinceName":"宁夏回族自治区"},{"provinceId":"310000","provinceName":"新疆维吾尔自治区"},{"provinceId":"340000","provinceName":"台湾省"},{"provinceId":"330000","provinceName":"香港特别行政区"},{"provinceId":"350000","provinceName":"澳门特别行政区"}]
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

    public static class DataBean {
        /**
         * provinceId : 010000
         * provinceName : 北京
         */

        private String provinceId;
        private String provinceName;

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }
    }
}
