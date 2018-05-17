package com.aladdin.common.base;

import java.util.List;

/**
 * @author: Ruijinglin
 * @date: 2018/2/6
 * Explain:      .
 */

public class BaseModel<T> {

    private String status;
    private String msg;
    private List<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
