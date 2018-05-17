package com.aladdin.hxe.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/2/10 0010 10:14..
 */

public class OrderInfoBean implements Serializable {


    /**
     * status : 200
     * msg : OK
     * data : {"id":4,"userName":"瑞雯","passWord":null,"sex":"2","userType":null,"phone":"15290126358","iDNumber":"5555555555555556","email":"139886284500@126.com","createTime":"2018-01-02 00:00:00","updateTime":null,"city":"河南省/郑州市/新郑市","address":"中原工学院北苑餐厅3楼(龙湖校区)","shopSign":"知青居（内测店铺.）","ref_x_user_id":2,"shopStutas":1,"stutas":2,"longitude":"114.049516","latitude":"32.984979","licenseImage":"http://51hxe.com/group1/M00/00/08/rBLko1q1qQeAbJgIAAAoolh7BVg487.jpg","hygienicImage":"img/a9.jpg","shopImage1":"img/a9.jpg","shopImage2":"img/a9.jpg","shopImage3":"http://51hxe.com/group1/M00/00/08/rBLko1q1qVuAcLhGAAAoolh7BVg103.jpg","userImage":"img/a9.jpg","logoImage":"http://51hxe.com/group1/M00/00/0E/rBLko1q8-zuAILk5AAG4xRfK1Uw569.jpg","iDImage":"img/a9.jpg","sys_user_id":null,"employ":1,"token":null,"level":null,"parentId":null,"isAuto":2,"data1":"","data2":null,"data3":"新店开业,全场五折!!!","count":0,"score":null,"distance":null,"isCollection":null,"productList":[]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 4
         * userName : 瑞雯
         * passWord : null
         * sex : 2
         * userType : null
         * phone : 15290126358
         * iDNumber : 5555555555555556
         * email : 139886284500@126.com
         * createTime : 2018-01-02 00:00:00
         * updateTime : null
         * city : 河南省/郑州市/新郑市
         * address : 中原工学院北苑餐厅3楼(龙湖校区)
         * shopSign : 知青居（内测店铺.）
         * ref_x_user_id : 2
         * shopStutas : 1
         * stutas : 2
         * longitude : 114.049516
         * latitude : 32.984979
         * licenseImage : http://51hxe.com/group1/M00/00/08/rBLko1q1qQeAbJgIAAAoolh7BVg487.jpg
         * hygienicImage : img/a9.jpg
         * shopImage1 : img/a9.jpg
         * shopImage2 : img/a9.jpg
         * shopImage3 : http://51hxe.com/group1/M00/00/08/rBLko1q1qVuAcLhGAAAoolh7BVg103.jpg
         * userImage : img/a9.jpg
         * logoImage : http://51hxe.com/group1/M00/00/0E/rBLko1q8-zuAILk5AAG4xRfK1Uw569.jpg
         * iDImage : img/a9.jpg
         * sys_user_id : null
         * employ : 1
         * token : null
         * level : null
         * parentId : null
         * isAuto : 2
         * data1 :
         * data2 : null
         * data3 : 新店开业,全场五折!!!
         * count : 0
         * score : null
         * distance : null
         * isCollection : null
         * productList : []
         */

        private int id;
        private String userName;
        private Object passWord;
        private String sex;
        private Object userType;
        private String phone;
        private String iDNumber;
        private String email;
        private String createTime;
        private Object updateTime;
        private String city;
        private String address;
        private String shopSign;
        private int ref_x_user_id;
        private int shopStutas;
        private int stutas;
        private String longitude;
        private String latitude;
        private String licenseImage;
        private String hygienicImage;
        private String shopImage1;
        private String shopImage2;
        private String shopImage3;
        private String userImage;
        private String logoImage;
        private String iDImage;
        private Object sys_user_id;
        private int employ;
        private Object token;
        private Object level;
        private Object parentId;
        private int isAuto;
        private String data1;
        private Object data2;
        private String data3;
        private int count;
        private Object score;
        private Object distance;
        private Object isCollection;
        private List<?> productList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getPassWord() {
            return passWord;
        }

        public void setPassWord(Object passWord) {
            this.passWord = passWord;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getUserType() {
            return userType;
        }

        public void setUserType(Object userType) {
            this.userType = userType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIDNumber() {
            return iDNumber;
        }

        public void setIDNumber(String iDNumber) {
            this.iDNumber = iDNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShopSign() {
            return shopSign;
        }

        public void setShopSign(String shopSign) {
            this.shopSign = shopSign;
        }

        public int getRef_x_user_id() {
            return ref_x_user_id;
        }

        public void setRef_x_user_id(int ref_x_user_id) {
            this.ref_x_user_id = ref_x_user_id;
        }

        public int getShopStutas() {
            return shopStutas;
        }

        public void setShopStutas(int shopStutas) {
            this.shopStutas = shopStutas;
        }

        public int getStutas() {
            return stutas;
        }

        public void setStutas(int stutas) {
            this.stutas = stutas;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLicenseImage() {
            return licenseImage;
        }

        public void setLicenseImage(String licenseImage) {
            this.licenseImage = licenseImage;
        }

        public String getHygienicImage() {
            return hygienicImage;
        }

        public void setHygienicImage(String hygienicImage) {
            this.hygienicImage = hygienicImage;
        }

        public String getShopImage1() {
            return shopImage1;
        }

        public void setShopImage1(String shopImage1) {
            this.shopImage1 = shopImage1;
        }

        public String getShopImage2() {
            return shopImage2;
        }

        public void setShopImage2(String shopImage2) {
            this.shopImage2 = shopImage2;
        }

        public String getShopImage3() {
            return shopImage3;
        }

        public void setShopImage3(String shopImage3) {
            this.shopImage3 = shopImage3;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getLogoImage() {
            return logoImage;
        }

        public void setLogoImage(String logoImage) {
            this.logoImage = logoImage;
        }

        public String getIDImage() {
            return iDImage;
        }

        public void setIDImage(String iDImage) {
            this.iDImage = iDImage;
        }

        public Object getSys_user_id() {
            return sys_user_id;
        }

        public void setSys_user_id(Object sys_user_id) {
            this.sys_user_id = sys_user_id;
        }

        public int getEmploy() {
            return employ;
        }

        public void setEmploy(int employ) {
            this.employ = employ;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public int getIsAuto() {
            return isAuto;
        }

        public void setIsAuto(int isAuto) {
            this.isAuto = isAuto;
        }

        public String getData1() {
            return data1;
        }

        public void setData1(String data1) {
            this.data1 = data1;
        }

        public Object getData2() {
            return data2;
        }

        public void setData2(Object data2) {
            this.data2 = data2;
        }

        public String getData3() {
            return data3;
        }

        public void setData3(String data3) {
            this.data3 = data3;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(Object isCollection) {
            this.isCollection = isCollection;
        }

        public List<?> getProductList() {
            return productList;
        }

        public void setProductList(List<?> productList) {
            this.productList = productList;
        }
    }
}
