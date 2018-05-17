package com.aladdin.hxe.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/4/9 0009 11:10..
 */

public class MyBankBean implements Serializable {

    /**
     * status : 200
     * msg : 查询成功
     * data : [{"id":7,"sys_user_id":4,"accountType":null,"settlementAccountName":"卡哇伊","settlementAccount":"434135463454635","openBank":"兴业银行","subBranch":"兴业银行西安分行高新区支行","createTime":"2018-04-09 10:53:17","updateTime":"2018-04-09 10:53:17","token":null,"des":null},{"id":8,"sys_user_id":4,"accountType":1,"settlementAccountName":"卡哇伊","settlementAccount":"434135463454635","openBank":"兴业银行","subBranch":"兴业银行西安分行高新区支行","createTime":"2018-04-09 10:55:18","updateTime":"2018-04-09 10:55:18","token":null,"des":null}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 7
         * sys_user_id : 4
         * accountType : null
         * settlementAccountName : 卡哇伊
         * settlementAccount : 434135463454635
         * openBank : 兴业银行
         * subBranch : 兴业银行西安分行高新区支行
         * createTime : 2018-04-09 10:53:17
         * updateTime : 2018-04-09 10:53:17
         * token : null
         * des : null
         */

        private int id;
        private int sys_user_id;
        private int accountType;
        private String settlementAccountName;
        private String settlementAccount;
        private String openBank;
        private String subBranch;
        private String createTime;
        private String updateTime;
        private Object token;
        private Object des;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSys_user_id() {
            return sys_user_id;
        }

        public void setSys_user_id(int sys_user_id) {
            this.sys_user_id = sys_user_id;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getSettlementAccountName() {
            return settlementAccountName;
        }

        public void setSettlementAccountName(String settlementAccountName) {
            this.settlementAccountName = settlementAccountName;
        }

        public String getSettlementAccount() {
            return settlementAccount;
        }

        public void setSettlementAccount(String settlementAccount) {
            this.settlementAccount = settlementAccount;
        }

        public String getOpenBank() {
            return openBank;
        }

        public void setOpenBank(String openBank) {
            this.openBank = openBank;
        }

        public String getSubBranch() {
            return subBranch;
        }

        public void setSubBranch(String subBranch) {
            this.subBranch = subBranch;
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

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getDes() {
            return des;
        }

        public void setDes(Object des) {
            this.des = des;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", sys_user_id=" + sys_user_id +
                    ", accountType=" + accountType +
                    ", settlementAccountName='" + settlementAccountName + '\'' +
                    ", settlementAccount='" + settlementAccount + '\'' +
                    ", openBank='" + openBank + '\'' +
                    ", subBranch='" + subBranch + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", token=" + token +
                    ", des=" + des +
                    '}';
        }
    }
}
