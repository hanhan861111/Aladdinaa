package com.aladdin.hxe.bean;

import java.util.List;

/**
 * Created by ${韩永光} on on 2018/4/8 0008 15:12..
 */

public class BankBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"bankId":1,"bankName":"中国工商银行"},{"bankId":2,"bankName":"中国银行"},{"bankId":3,"bankName":"中国建设银行"},{"bankId":4,"bankName":"中国农业银行"},{"bankId":5,"bankName":"招商银行"},{"bankId":6,"bankName":"中国邮政储蓄银行"},{"bankId":7,"bankName":"中国光大银行"},{"bankId":8,"bankName":"上海浦东发展银行"},{"bankId":10,"bankName":"兴业银行"},{"bankId":11,"bankName":"平安银行"},{"bankId":12,"bankName":"广东发展银行"},{"bankId":13,"bankName":"中信银行"},{"bankId":14,"bankName":"交通银行"},{"bankId":15,"bankName":"中国民生银行"},{"bankId":42,"bankName":"杭州银行"},{"bankId":44,"bankName":"苏州银行"},{"bankId":45,"bankName":"农村商业银行"},{"bankId":46,"bankName":"华夏银行"},{"bankId":47,"bankName":"南京银行"},{"bankId":48,"bankName":"东莞银行"},{"bankId":49,"bankName":"北京银行"},{"bankId":61,"bankName":"贵阳银行"},{"bankId":62,"bankName":"宁波银行"},{"bankId":101,"bankName":"汉口银行"},{"bankId":102,"bankName":"杭州联合农村商业银行"},{"bankId":121,"bankName":"宁夏银行股份有限公司"},{"bankId":122,"bankName":"廊坊银行"},{"bankId":125,"bankName":"台州银行股份有限公司"},{"bankId":141,"bankName":"上海银行"},{"bankId":142,"bankName":"徽商银行"},{"bankId":161,"bankName":"葫芦岛银行"},{"bankId":162,"bankName":"农村合作信用社"},{"bankId":163,"bankName":"长沙银行"},{"bankId":181,"bankName":"浙江泰隆商业银行"},{"bankId":182,"bankName":"锦州银行"},{"bankId":183,"bankName":"广东华兴银行"},{"bankId":201,"bankName":"乐山市商业银行"},{"bankId":221,"bankName":"渤海银行"},{"bankId":241,"bankName":"江苏银行"},{"bankId":261,"bankName":"成都银行"},{"bankId":262,"bankName":"洛阳银行"},{"bankId":281,"bankName":"青岛银行"},{"bankId":301,"bankName":"富滇银行"},{"bankId":321,"bankName":"包商银行"},{"bankId":381,"bankName":"天津银行"},{"bankId":382,"bankName":"桂林银行"},{"bankId":383,"bankName":"晋中银行"},{"bankId":401,"bankName":"邯郸银行"},{"bankId":402,"bankName":"香港上海汇丰银行"},{"bankId":403,"bankName":"齐鲁银行"},{"bankId":422,"bankName":"中原银行"},{"bankId":441,"bankName":"郑州银行"},{"bankId":442,"bankName":"盛京银行"},{"bankId":461,"bankName":"甘肃银行"},{"bankId":481,"bankName":"香港星展銀行"},{"bankId":501,"bankName":"日照银行"},{"bankId":502,"bankName":"承德银行"},{"bankId":503,"bankName":"河北银行"},{"bankId":504,"bankName":"广东南粤银行"},{"bankId":505,"bankName":"内蒙古银行"},{"bankId":506,"bankName":"广州银行"},{"bankId":507,"bankName":"营口银行"},{"bankId":521,"bankName":"邢台银行"},{"bankId":522,"bankName":"乌海银行"},{"bankId":523,"bankName":"晋商银行"},{"bankId":524,"bankName":"东亚银行"},{"bankId":526,"bankName":"哈尔滨银行股份有限公司"},{"bankId":527,"bankName":"潍坊银行"},{"bankId":531,"bankName":"龙江银行"},{"bankId":533,"bankName":"长安银行"},{"bankId":534,"bankName":"晋城银行"},{"bankId":536,"bankName":"保定银行"},{"bankId":561,"bankName":"恒丰银行"},{"bankId":581,"bankName":"朝阳银行"},{"bankId":601,"bankName":"西安银行"},{"bankId":621,"bankName":"金华银行"},{"bankId":641,"bankName":"齐商银行"},{"bankId":10000003,"bankName":"渣打银行"},{"bankId":10000007,"bankName":"兰州银行"},{"bankId":10000008,"bankName":"广西北部湾银行股份有限公司"},{"bankId":10000016,"bankName":"江西银行"},{"bankId":10000021,"bankName":"浙商银行"},{"bankId":10000026,"bankName":"武汉农村商业银行"},{"bankId":10000032,"bankName":"江苏长江商业银行"},{"bankId":10000033,"bankName":"沧州银行"},{"bankId":10000039,"bankName":"南充市商业银行"},{"bankId":10000040,"bankName":"洋县农村信用合作联社"},{"bankId":10000042,"bankName":"泉州银行"},{"bankId":10000063,"bankName":"长沙银行股份有限公司"},{"bankId":10000087,"bankName":"成都农商银行"},{"bankId":10000089,"bankName":"鞍山银行"},{"bankId":10000090,"bankName":"奥地利中央合作银行"},{"bankId":10000091,"bankName":"澳门清算行"},{"bankId":10000092,"bankName":"澳新银行"},{"bankId":10000097,"bankName":"比利时联合银行"},{"bankId":10000099,"bankName":"城市商业银行"},{"bankId":10000100,"bankName":"城市信用社"},{"bankId":10000101,"bankName":"村镇银行"},{"bankId":10000102,"bankName":"达州市商业银行"},{"bankId":10000103,"bankName":"大华银行"},{"bankId":10000104,"bankName":"大连银行"},{"bankId":10000105,"bankName":"大同银行"},{"bankId":10000106,"bankName":"大新银行"},{"bankId":10000108,"bankName":"丹东银行"},{"bankId":10000111,"bankName":"德国商业银行"},{"bankId":10000114,"bankName":"德意志银行"},{"bankId":10000117,"bankName":"东京三菱银行"},{"bankId":10000119,"bankName":"东营银行"},{"bankId":10000121,"bankName":"鄂尔多斯银行"},{"bankId":10000124,"bankName":"法国外贸银行"},{"bankId":10000125,"bankName":"法国兴业银行"},{"bankId":10000126,"bankName":"丰业银行"},{"bankId":10000127,"bankName":"福建海峡银行"},{"bankId":10000128,"bankName":"抚顺银行"},{"bankId":10000129,"bankName":"阜新银行"},{"bankId":10000130,"bankName":"富邦华一银行"},{"bankId":10000131,"bankName":"赣州银行"},{"bankId":10000136,"bankName":"贵州银行"},{"bankId":10000137,"bankName":"国家开发银行"},{"bankId":10000139,"bankName":"国泰君安证券股份有限公司"},{"bankId":10000141,"bankName":"哈密市商业银行"},{"bankId":10000143,"bankName":"韩国产业银行"},{"bankId":10000144,"bankName":"韩国企业银行"},{"bankId":10000146,"bankName":"韩亚银行"},{"bankId":10000147,"bankName":"荷兰合作银行"},{"bankId":10000148,"bankName":"荷兰商业银行"},{"bankId":10000149,"bankName":"恒生银行"},{"bankId":10000150,"bankName":"衡水银行"},{"bankId":10000151,"bankName":"湖北银行"},{"bankId":10000153,"bankName":"花旗银行"},{"bankId":10000155,"bankName":"华侨银行"},{"bankId":10000156,"bankName":"华融湘江银行"},{"bankId":10000157,"bankName":"华商银行"},{"bankId":10000158,"bankName":"吉林银行"},{"bankId":10000159,"bankName":"集友银行"},{"bankId":10000160,"bankName":"济宁银行"},{"bankId":10000161,"bankName":"嘉兴银行"},{"bankId":10000162,"bankName":"建东银行"},{"bankId":10000167,"bankName":"焦作中旅银行"},{"bankId":10000168,"bankName":"九江银行"},{"bankId":10000170,"bankName":"昆仑银行"},{"bankId":10000172,"bankName":"莱商银行"},{"bankId":10000173,"bankName":"凉山州商业银行"},{"bankId":10000174,"bankName":"辽阳银行"},{"bankId":10000175,"bankName":"廖创兴银行"},{"bankId":10000176,"bankName":"临商银行"},{"bankId":10000178,"bankName":"泸州市商业银行"},{"bankId":10000181,"bankName":"马来亚银行"},{"bankId":10000182,"bankName":"美国银行"},{"bankId":10000183,"bankName":"蒙特利尔银行"},{"bankId":10000184,"bankName":"绵阳市商业银行"},{"bankId":10000185,"bankName":"摩根大通银行"},{"bankId":10000188,"bankName":"南洋商业银行"},{"bankId":10000189,"bankName":"宁波东海银行"},{"bankId":10000190,"bankName":"宁波通商银行"},{"bankId":10000191,"bankName":"宁夏黄河农村商业银行"},{"bankId":10000192,"bankName":"农村合作银行"},{"bankId":10000194,"bankName":"攀枝花市商业银行"},{"bankId":10000196,"bankName":"平顶山银行"},{"bankId":10000200,"bankName":"青海银行"},{"bankId":10000202,"bankName":"曲靖市商业银行"},{"bankId":10000207,"bankName":"瑞士信贷第一波士顿银行"},{"bankId":10000209,"bankName":"瑞穗银行"},{"bankId":10000210,"bankName":"三井住友银行"},{"bankId":10000212,"bankName":"厦门国际银行"},{"bankId":10000213,"bankName":"厦门银行"},{"bankId":10000214,"bankName":"山口银行"},{"bankId":10000216,"bankName":"上海\u2014巴黎国际银行"},{"bankId":10000218,"bankName":"上海商业银行"},{"bankId":10000219,"bankName":"上饶银行"},{"bankId":10000220,"bankName":"绍兴银行"},{"bankId":10000222,"bankName":"石嘴山银行"},{"bankId":10000223,"bankName":"首都银行"},{"bankId":10000227,"bankName":"泰安银行"},{"bankId":10000228,"bankName":"泰华农民银行"},{"bankId":10000229,"bankName":"唐山银行"},{"bankId":10000230,"bankName":"天津滨海农村商业银行"},{"bankId":10000233,"bankName":"铁岭银行"},{"bankId":10000234,"bankName":"威海市商业银行"},{"bankId":10000235,"bankName":"温州银行"},{"bankId":10000237,"bankName":"乌鲁木齐银行"},{"bankId":10000239,"bankName":"西藏银行"},{"bankId":10000243,"bankName":"新韩银行"},{"bankId":10000245,"bankName":"新疆汇和银行"},{"bankId":10000248,"bankName":"雅安市商业银行"},{"bankId":10000250,"bankName":"烟台银行"},{"bankId":10000251,"bankName":"阳泉市商业银行"},{"bankId":10000252,"bankName":"宜宾市商业银行"},{"bankId":10000254,"bankName":"意大利联合圣保罗银行"},{"bankId":10000260,"bankName":"友利银行"},{"bankId":10000261,"bankName":"玉溪市商业银行"},{"bankId":10000262,"bankName":"枣庄银行"},{"bankId":10000263,"bankName":"湛江市商业银行"},{"bankId":10000265,"bankName":"张家口银行"},{"bankId":10000268,"bankName":"浙江稠州商业银行"},{"bankId":10000270,"bankName":"浙江民泰商业银行"},{"bankId":10000273,"bankName":"支付业务收费专户"},{"bankId":10000275,"bankName":"中国进出口银行"},{"bankId":10000276,"bankName":"中国农业发展银行"},{"bankId":10000277,"bankName":"中国银行间外汇交易中心"},{"bankId":10000278,"bankName":"中国银联"},{"bankId":10000280,"bankName":"中央国债登记结算有限责任公司"},{"bankId":10000282,"bankName":"重庆农村商业银行"},{"bankId":10000285,"bankName":"重庆银行"},{"bankId":10000289,"bankName":"自贡市商业银行"},{"bankId":10000292,"bankName":"深圳农村商业银行"},{"bankId":10000293,"bankName":"秦皇岛银行"},{"bankId":10000302,"bankName":"福建农村信用社农商银行"},{"bankId":10000307,"bankName":"营口沿海银行"},{"bankId":10000309,"bankName":"内蒙古自治区农村信用社联合社"},{"bankId":10000310,"bankName":"辽宁省农村信用社联合社"},{"bankId":10000311,"bankName":"黑龙江省农村信用社"},{"bankId":10000313,"bankName":"江西省农村信用社联合社"},{"bankId":10000315,"bankName":"广东省农村信用社联合社"},{"bankId":10000316,"bankName":"云南省农村信用社联合社"},{"bankId":10000317,"bankName":"浙江省农村信用社联合社"},{"bankId":10000318,"bankName":"陕西省农村信用社联合社"},{"bankId":10000319,"bankName":"河北省农村信用社联合社"},{"bankId":10000320,"bankName":"山西省农村信用社联合社"},{"bankId":10000333,"bankName":"安徽省农村信用社联合社"},{"bankId":10000335,"bankName":"广西农村信用社"},{"bankId":10000336,"bankName":"海南省农村信用社"},{"bankId":10000346,"bankName":"河南省农村信用社"},{"bankId":10000372,"bankName":"甘肃农村信用社"},{"bankId":10000373,"bankName":"贵州省农村信用社联合社"},{"bankId":10000374,"bankName":"湖南省农村信用社联合社"},{"bankId":10000376,"bankName":"新疆维吾尔自治区农村信用社联合社"},{"bankId":10000387,"bankName":"农村信用合作社"},{"bankId":10000450,"bankName":"湖南省农村商业银行"},{"bankId":10000451,"bankName":"海南银行"},{"bankId":10000455,"bankName":"内蒙古农村商业银行"},{"bankId":10000457,"bankName":"德州银行"},{"bankId":10000458,"bankName":"裕信银行"},{"bankId":10000459,"bankName":"摩根士丹利国际银行"},{"bankId":10000461,"bankName":"常州市农村信用社"},{"bankId":10000463,"bankName":"东方汇理银行"},{"bankId":10000464,"bankName":"常德市农村商业银行"},{"bankId":10000472,"bankName":"宝鸡市农村信用社"},{"bankId":10000473,"bankName":"湖州银行"},{"bankId":10000474,"bankName":"大众银行"},{"bankId":10000475,"bankName":"库尔勒银行"},{"bankId":10000483,"bankName":"本溪市商业银行"},{"bankId":10000484,"bankName":"华美银行"},{"bankId":10000631,"bankName":"四川省农村信用社联合社"}]
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
         * bankId : 1
         * bankName : 中国工商银行
         */

        private int bankId;
        private String bankName;

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
