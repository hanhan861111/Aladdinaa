package com.aladdin.hxe.bean;

/**
 * Created by ${韩永光} on on 2018/4/28 0028 09:13..
 */

public class VerSionCodeBean {

    /**
     * status : 200
     * msg : OK
     * data : {"id":1,"versionid":"1.0","apptype":1,"url":"http://51hxe.com/app/hxe_v1.0.0.apk","createTime":"2018-04-28 09:00:00"}
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
         * id : 1
         * versionid : 1.0
         * apptype : 1
         * url : http://51hxe.com/app/hxe_v1.0.0.apk
         * createTime : 2018-04-28 09:00:00
         */

        private int id;
        private String versionid;
        private int apptype;
        private String url;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionid() {
            return versionid;
        }

        public void setVersionid(String versionid) {
            this.versionid = versionid;
        }

        public int getApptype() {
            return apptype;
        }

        public void setApptype(int apptype) {
            this.apptype = apptype;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
