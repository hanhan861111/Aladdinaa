package com.aladdin.hxe.utils;

/**
 * Created by ${韩永光} on on 2018/2/10 0010 10:01..
 */

public class Url {
    public static String baseURL = "http://51hxe.com:";
    public static String loginUrl = baseURL + "9004/appcommercialUser/login";
    //分页查询进行中的订单
    public static String findOrdersByStatus = baseURL + "9007/appordersr/findOrdersByStatus";
    //所有订单URL
    public static String findallallUrlB = baseURL + "9007/appordersr/findallall";
    public static String findallallUrlA = baseURL + "9007/appordersr/findOrdersByDate";
    public static String findbytokenUrl = baseURL + "9002/appcommercial/findbytoken";
    //菜品管理URL
    public static String findallallUrl = baseURL + "9002/appproduct/category/findallall";
    public static String findallevaluationUrl = baseURL + "9002/appcommercial/findallevaluation";
    public static String addURL = baseURL + "9002/appproduct/add";
    //叫号URL
    public static String orderremind = baseURL + "9007/appordersr/orderremind";
    //接单 拒单URL
    public static String updatebyorderstatus = baseURL + "9007/appordersr/updatebyorderstatus";
    //商品上架、下架、删除
    public static String update = baseURL + "9002/appproduct/update";
    //商品分类删除
    public static String categoryDelete = baseURL + "9002/appproduct/category/delete";
    //商品分类添加
    public static String categoryAdd = baseURL + "9002/appproduct/category/add";
    //当日营业额查询
    public static String findtodayinfo = baseURL + "9007/appordersr/findtodayinfo";
    //当日营业额详情查询
    public static String findOrdersByDate = baseURL + "9007/appordersr/findOrdersByDate";
    //商户信息修改
    public static String appcommercialUpdate = baseURL + "9002/appcommercial/update";
    //退出登录
    public static String loginoutURL = baseURL + "9004/appcommercialUser/user/loginout";
    //修改商品分类
    public static String categoryUpdateURL = baseURL + "9002/appproduct/category/update";
    //删除商品
    public static String deleteURL = baseURL + "9002/appproduct/delete";
    //上传图片
    public static String upload = baseURL + "9002/appimage/image/upload";
    //票机查询
    public static String findTicketURL = baseURL + "9002/appprint/findall";
    //票机绑定
    public static String bindTicketURL = baseURL + "9002/appprint/bind";
    //解绑票机
    public static String unbindTicketURL = baseURL + "9002/appprint/unbind";
    //提交意见反馈
    public static String adduserbackmsgURL = baseURL + "9002/appuserbackmsg/adduserbackmsg";
    //查询省份
    public static String findprovinceallURL = baseURL + "9002/appcity/findprovinceall";
    //根据省份查询区
    public static String findcitybyprovURL = baseURL + "9002/appcity/findcitybyprov";
    //查询银行
    public static String findbankcodeallURL = baseURL + "9002/appcity/findbankcodeall";
    //查询支行
    public static String findbankBranchallURL = baseURL + "9002/appcity/findbankBranchall";
    //添加银行卡
    public static String addBankAccountURL = baseURL + "9002/appcommercial/addBankAccount";
    //查询银行卡
    public static String findBankAccountURL = baseURL + "9002/appcommercial/findBankAccount";
    //修改商户信息
    public static String updateShopInfoURL = baseURL + "9002/appcommercial/update";
    //发送验证码
    public static String smsURL = baseURL + "9004/sms/sms";
    //修改电话号码
    public static String updatePhoneURL = baseURL + "9002/appcommercial/updatePhone";
    //修改订单状态
    public static String updatebyorderstatusURL = baseURL + "9007/appordersr/updatebyorderstatus";
    //订单查询----全部订单
    public static String appordersr0427 = baseURL + "9007/appordersr0427/findall0427";
    //订单查询 已完成
    public static String findorderimpl0427=baseURL+"9007/appordersr0427/findorderimpl0427";
    //订单查询 取消/退款
    public static String findordercancel0427=baseURL+"9007/appordersr0427/findordercancel0427";
    //扫码收款
    public static String findallqr0427=baseURL+"9007/appordersr0427/findallqr0427";
}
