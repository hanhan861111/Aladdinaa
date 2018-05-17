package com.aladdin.hxe.bean;

/**
 * Created by ${韩永光} on on 2018/3/11 0011 17:48..
 */

public class StatusBean {

    /**
     * status : 200
     * msg : 状态更新成功
     * data : null
     */

    private int status;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
