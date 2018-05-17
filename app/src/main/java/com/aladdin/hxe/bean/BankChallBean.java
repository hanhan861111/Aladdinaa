package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/4/8 0008 15:40..
 */

public class BankChallBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"bankCoupletNum":"102492022014","bankBranchName":"中国工商银行股份有限公司开封纪念塔支行"},{"bankCoupletNum":"102491092089","bankBranchName":"中国工商银行股份有限公司郑州紫东支行"},{"bankCoupletNum":"102491032176","bankBranchName":"中国工商银行股份有限公司郑州富田支行"},{"bankCoupletNum":"102491042207","bankBranchName":"中国工商银行股份有限公司郑州卡萨大厦支行"},{"bankCoupletNum":"102491002159","bankBranchName":"中国工商银行股份有限公司郑州陇海路支行"},{"bankCoupletNum":"102491012913","bankBranchName":"中国工商银行股份有限公司郑州福塔支行"},{"bankCoupletNum":"102491022179","bankBranchName":"中国工商银行股份有限公司郑州东风路支行"},{"bankCoupletNum":"102491082091","bankBranchName":"中国工商银行股份有限公司郑州桃源洋房支行"},{"bankCoupletNum":"102491012028","bankBranchName":"中国工商银行股份有限公司郑州城东路支行"},{"bankCoupletNum":"102491018153","bankBranchName":"中国工商银行股份有限公司郑州高新技术开发区支行"},{"bankCoupletNum":"102491052151","bankBranchName":"中国工商银行股份有限公司郑州陇海中路支行"},{"bankCoupletNum":"102491042065","bankBranchName":"中国工商银行股份有限公司郑州郑东新区支行"},{"bankCoupletNum":"102492002094","bankBranchName":"中国工商银行股份有限公司开封豪德支行"},{"bankCoupletNum":"102491016027","bankBranchName":"中国工商银行股份有限公司郑州中州支行"},{"bankCoupletNum":"102491062068","bankBranchName":"中国工商银行股份有限公司郑州黄委会支行"},{"bankCoupletNum":"102491002142","bankBranchName":"中国工商银行郑州市农业路支行"},{"bankCoupletNum":"102491042153","bankBranchName":"中国工商银行股份有限公司郑州万福支行"},{"bankCoupletNum":"102491022200","bankBranchName":"中国工商银行股份有限公司郑州二十一世纪支行"},{"bankCoupletNum":"102491002610","bankBranchName":"中国工商银行登封市支行"},{"bankCoupletNum":"102491062172","bankBranchName":"中国工商银行股份有限公司郑州信息学院路支行"},{"bankCoupletNum":"102491012680","bankBranchName":"中国工商银行股份有限公司河南省分行营业部营业中心"},{"bankCoupletNum":"102492402605","bankBranchName":"中国工商银行股份有限公司开封祥符支行"},{"bankCoupletNum":"102491002095","bankBranchName":"中国工商银行郑州市铁路支行"},{"bankCoupletNum":"102492302407","bankBranchName":"中国工商银行开封分行尉氏县支行"},{"bankCoupletNum":"102491012093","bankBranchName":"中国工商银行股份有限公司郑州福华支行"},{"bankCoupletNum":"102492032050","bankBranchName":"中国工商银行股份有限公司开封黄河路支行"},{"bankCoupletNum":"102491002556","bankBranchName":"中国工商银行股份有限公司济源分行"},{"bankCoupletNum":"102491002062","bankBranchName":"中国工商银行郑州市花园路支行"},{"bankCoupletNum":"102491002513","bankBranchName":"中国工商银行新密市支行"},{"bankCoupletNum":"102491017152","bankBranchName":"中国工商银行股份有限公司郑州陇西支行"},{"bankCoupletNum":"102491011084","bankBranchName":"中国工商银行股份有限公司郑州城市山水支行"},{"bankCoupletNum":"102491032205","bankBranchName":"中国工商银行股份有限公司郑州注协大厦支行"},{"bankCoupletNum":"102491013088","bankBranchName":"中国工商银行股份有限公司郑州顺河路支行"},{"bankCoupletNum":"102491072012","bankBranchName":"中国工商银行股份有限公司郑州京广南路支行"},{"bankCoupletNum":"102491013012","bankBranchName":"中国工商银行股份有限公司郑州西三马路支行"},{"bankCoupletNum":"102492022055","bankBranchName":"中国工商银行股份有限公司开封河南大学支行"},{"bankCoupletNum":"102491002915","bankBranchName":"中国工商银行郑州市行政区支行"},{"bankCoupletNum":"102491042024","bankBranchName":"中国工商银行股份有限公司郑州航海路支行"},{"bankCoupletNum":"102492002043","bankBranchName":"中国工商银行股份有限公司开封金明池支行"},{"bankCoupletNum":"102491010022","bankBranchName":"中国工商银行股份有限公司郑州经济技术开发区支行"},{"bankCoupletNum":"102491015083","bankBranchName":"中国工商银行股份有限公司郑州中苑名都支行"},{"bankCoupletNum":"102491052098","bankBranchName":"中国工商银行股份有限公司郑州长江路支行"},{"bankCoupletNum":"102491032078","bankBranchName":"中国工商银行股份有限公司郑州建文支行"},{"bankCoupletNum":"102491002280","bankBranchName":"中国工商银行郑州市中原路支行"},{"bankCoupletNum":"102492012049","bankBranchName":"中国工商银行股份有限公司开封万宝支行"},{"bankCoupletNum":"102491002087","bankBranchName":"中国工商银行股份有限公司郑州商都路支行"},{"bankCoupletNum":"102491002038","bankBranchName":"中国工商银行郑州市西大街支行"},{"bankCoupletNum":"102491072088","bankBranchName":"中国工商银行股份有限公司郑州互助路支行"},{"bankCoupletNum":"102491002907","bankBranchName":"中国工商银行河南省分行营业部"},{"bankCoupletNum":"102491052080","bankBranchName":"中国工商银行股份有限公司郑州大学路支行"},{"bankCoupletNum":"102492012032","bankBranchName":"中国工商银行股份有限公司开封分行香榭里分理处"},{"bankCoupletNum":"102491010071","bankBranchName":"中国工商银行股份有限公司票据营业部郑州分部"},{"bankCoupletNum":"102492202300","bankBranchName":"中国工商银行开封分行通许县支行"},{"bankCoupletNum":"102491010063","bankBranchName":"中国工商银行股份有限公司宝龙广场支行"},{"bankCoupletNum":"102491002175","bankBranchName":"中国工商银行郑州市南阳路支行"},{"bankCoupletNum":"102492002035","bankBranchName":"中国工商银行开封分行鼓楼街支行"},{"bankCoupletNum":"102491002923","bankBranchName":"中国工商银行郑州市紫荆支行"},{"bankCoupletNum":"102491000064","bankBranchName":"中国工商银行股份有限公司郑州航空港区支行"},{"bankCoupletNum":"102491002079","bankBranchName":"中国工商银行股份有限公司郑州金水支行"},{"bankCoupletNum":"102491012884","bankBranchName":"中国工商银行股份有限公司荥阳万山路支行"},{"bankCoupletNum":"102492502104","bankBranchName":"中国工商银行开封分行兰考县支行"},{"bankCoupletNum":"102492052042","bankBranchName":"中国工商银行股份有限公司开封大庆路支行"},{"bankCoupletNum":"102491042016","bankBranchName":"中国工商银行股份有限公司郑州新郑路支行"},{"bankCoupletNum":"102491052055","bankBranchName":"中国工商银行股份有限公司郑州嵩山路支行"},{"bankCoupletNum":"102492002027","bankBranchName":"中国工商银行股份有限公司开封城东支行"},{"bankCoupletNum":"102492012024","bankBranchName":"中国工商银行股份有限公司开封汴京路支行"},{"bankCoupletNum":"102491012069","bankBranchName":"中国工商银行股份有限公司郑州军区支行"},{"bankCoupletNum":"102492042053","bankBranchName":"中国工商银行股份有限公司开封金明东街支行"},{"bankCoupletNum":"102491002708","bankBranchName":"中国工商银行中牟县支行"},{"bankCoupletNum":"102491002214","bankBranchName":"中国工商银行郑州市上街区支行"},{"bankCoupletNum":"102491002271","bankBranchName":"中国工商银行股份有限公司郑州黄河支行"},{"bankCoupletNum":"102491032051","bankBranchName":"中国工商银行股份有限公司郑州工人路支行"},{"bankCoupletNum":"102492032017","bankBranchName":"中国工商银行股份有限公司开封分行五一路分理处"},{"bankCoupletNum":"102492052018","bankBranchName":"中国工商银行股份有限公司开封分行郑汴路分理处"},{"bankCoupletNum":"102491002319","bankBranchName":"中国工商银行巩义市支行"},{"bankCoupletNum":"102491016150","bankBranchName":"中国工商银行股份有限公司郑州卧龙花园支行"},{"bankCoupletNum":"102491082083","bankBranchName":"中国工商银行股份有限公司郑州棉纺东路支行"},{"bankCoupletNum":"102492062048","bankBranchName":"中国工商银行股份有限公司开封汉兴路支行"},{"bankCoupletNum":"102491014023","bankBranchName":"中国工商银行股份有限公司郑州国基路支行"},{"bankCoupletNum":"102491012132","bankBranchName":"中国工商银行股份有限公司郑州科学大道支行"},{"bankCoupletNum":"102491002134","bankBranchName":"中国工商银行郑州市桐柏路支行"},{"bankCoupletNum":"102491012010","bankBranchName":"中国工商银行股份有限公司郑州花都港湾支行"},{"bankCoupletNum":"102491002100","bankBranchName":"中国工商银行股份有限公司郑州兴华南街支行"},{"bankCoupletNum":"102491002298","bankBranchName":"中国工商银行郑州市未来支行"},{"bankCoupletNum":"102491072551","bankBranchName":"中国工商银行济源天坛路支行"},{"bankCoupletNum":"102491014015","bankBranchName":"中国工商银行股份有限公司郑州商鼎路支行"},{"bankCoupletNum":"102492002019","bankBranchName":"中国工商银行开封分行南关支行"},{"bankCoupletNum":"102492002086","bankBranchName":"中国工商银行开封分行中山路支行"},{"bankCoupletNum":"102491042813","bankBranchName":"中国工商银行股份有限公司郑州普罗旺世支行"},{"bankCoupletNum":"102491032086","bankBranchName":"中国工商银行股份有限公司郑州五里堡支行"},{"bankCoupletNum":"102491002011","bankBranchName":"中国工商银行郑州市解放路支行"},{"bankCoupletNum":"102491062156","bankBranchName":"中国工商银行股份有限公司郑州金海支行"},{"bankCoupletNum":"102491052217","bankBranchName":"中国工商银行股份有限公司郑州上街许昌路支行"},{"bankCoupletNum":"102491012157","bankBranchName":"中国工商银行股份有限公司郑州伏牛路支行"},{"bankCoupletNum":"102491082067","bankBranchName":"中国工商银行股份有限公司郑州东韩支行"},{"bankCoupletNum":"102491052063","bankBranchName":"中国工商银行股份有限公司郑州新华支行"},{"bankCoupletNum":"102491022912","bankBranchName":"中国工商银行股份有限公司郑州投资大厦支行"},{"bankCoupletNum":"102491002054","bankBranchName":"中国工商银行郑州市建设路支行"},{"bankCoupletNum":"102491042311","bankBranchName":"中国工商银行巩义市支行回郭镇分理处"},{"bankCoupletNum":"102492002060","bankBranchName":"中国工商银行股份有限公司开封分行"},{"bankCoupletNum":"102491000072","bankBranchName":"中国工商银行股份有限公司郑州地润路支行"},{"bankCoupletNum":"102491072414","bankBranchName":"中国工商银行股份有限公司新郑双湖大道支行"},{"bankCoupletNum":"102491002206","bankBranchName":"中国工商银行郑州市郑花路支行"},{"bankCoupletNum":"102491014154","bankBranchName":"中国工商银行股份有限公司郑州华淮支行"},{"bankCoupletNum":"102491072158","bankBranchName":"中国工商银行股份有限公司郑州洛河支行"},{"bankCoupletNum":"102492062013","bankBranchName":"中国工商银行股份有限公司开封繁塔支行"},{"bankCoupletNum":"102491022419","bankBranchName":"中国工商银行股份有限公司新郑车站支行"},{"bankCoupletNum":"102492002051","bankBranchName":"中国工商银行开封分行东大街支行"},{"bankCoupletNum":"102491072096","bankBranchName":"中国工商银行股份有限公司郑州嵩山南路支行"},{"bankCoupletNum":"102491007533","bankBranchName":"中国工商银行股份有限公司郑州新干线支行"},{"bankCoupletNum":"102491002685","bankBranchName":"中国工商银行股份有限公司河南省分行业务处理中心"},{"bankCoupletNum":"102491011558","bankBranchName":"中国工商银行股份有限公司济源三八支行"},{"bankCoupletNum":"102491032553","bankBranchName":"中国工商银行股份有限公司济源沁园路支行"},{"bankCoupletNum":"102491062558","bankBranchName":"中国工商银行股份有限公司济源北海路支行"},{"bankCoupletNum":"102491002417","bankBranchName":"中国工商银行新郑市支行"},{"bankCoupletNum":"102492002682","bankBranchName":"中国工商银行开封分行会计业务处理中心"},{"bankCoupletNum":"102491017021","bankBranchName":"中国工商银行股份有限公司郑州金苑支行"},{"bankCoupletNum":"102491002812","bankBranchName":"中国工商银行郑州市二七路支行"},{"bankCoupletNum":"102491002020","bankBranchName":"中国工商银行郑州市二里岗支行"},{"bankCoupletNum":"102491032027","bankBranchName":"中国工商银行股份有限公司郑州郑汴路支行"},{"bankCoupletNum":"102491062092","bankBranchName":"中国工商银行股份有限公司郑州庆丰街支行"},{"bankCoupletNum":"102492312409","bankBranchName":"中国工商银行股份有限公司尉氏支行东大街分理处"},{"bankCoupletNum":"102492102209","bankBranchName":"中国工商银行开封分行杞县支行"},{"bankCoupletNum":"102491002940","bankBranchName":"中国工商银行郑州市三八支行"},{"bankCoupletNum":"102491022099","bankBranchName":"中国工商银行股份有限公司郑州中原东路支行"},{"bankCoupletNum":"102492062021","bankBranchName":"中国工商银行股份有限公司开封劳动路支行"},{"bankCoupletNum":"102491092554","bankBranchName":"中国工商银行股份有限公司济源黄河路支行"},{"bankCoupletNum":"102492322403","bankBranchName":"中国工商银行股份有限公司尉氏滨河路支行"},{"bankCoupletNum":"102491052014","bankBranchName":"中国工商银行股份有限公司郑州航海华中支行"},{"bankCoupletNum":"102491002931","bankBranchName":"中国工商银行股份有限公司郑州财富广场支行"},{"bankCoupletNum":"102491012559","bankBranchName":"中国工商银行股份有限公司济源克井支行"},{"bankCoupletNum":"102492012016","bankBranchName":"中国工商银行股份有限公司开封前锋支行"},{"bankCoupletNum":"102491002888","bankBranchName":"中国工商银行荥阳市支行"},{"bankCoupletNum":"102491082552","bankBranchName":"中国工商银行股份有限公司济源济水大街支行"},{"bankCoupletNum":"102491002167","bankBranchName":"中国工商银行股份有限公司郑州东区支行"},{"bankCoupletNum":"102491015155","bankBranchName":"中国工商银行股份有限公司郑州阳光花苑支行"},{"bankCoupletNum":"102492052026","bankBranchName":"中国工商银行股份有限公司开封汴东支行"},{"bankCoupletNum":"102492072011","bankBranchName":"中国工商银行股份有限公司开封集英街支行"},{"bankCoupletNum":"102491022023","bankBranchName":"中国工商银行股份有限公司郑州康桥花园支行"},{"bankCoupletNum":"102491092152","bankBranchName":"中国工商银行股份有限公司郑州祥和支行"},{"bankCoupletNum":"102491000048","bankBranchName":"中国工商银行股份有限公司郑州翠竹街支行"},{"bankCoupletNum":"102491022058","bankBranchName":"中国工商银行股份有限公司郑州茜城支行"},{"bankCoupletNum":"102491000013","bankBranchName":"中国工商银行股份有限公司郑州玫瑰城支行"},{"bankCoupletNum":"102491010055","bankBranchName":"中国工商银行股份有限公司郑州橄榄城支行"}]
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
         * bankCoupletNum : 102492022014
         * bankBranchName : 中国工商银行股份有限公司开封纪念塔支行
         */

        private String bankCoupletNum;
        private String bankBranchName;

        public String getBankCoupletNum() {
            return bankCoupletNum;
        }

        public void setBankCoupletNum(String bankCoupletNum) {
            this.bankCoupletNum = bankCoupletNum;
        }

        public String getBankBranchName() {
            return bankBranchName;
        }

        public void setBankBranchName(String bankBranchName) {
            this.bankBranchName = bankBranchName;
        }
    }
}
