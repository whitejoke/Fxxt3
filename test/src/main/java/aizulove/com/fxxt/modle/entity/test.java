package aizulove.com.fxxt.modle.entity;

/**
 * Created by joker on 2016/8/5.
 */
public class test {

    /**
     * code : 1000
     * info : {"itemid":993,"catid":59,"mycatid":0,"areaid":119,"level":true,"elite":false,"title":"伟力兰Q 新款儿童自行车成长之星4-8岁16寸男女宝宝小孩子单车脚踏车","style":"","fee":0,"introduce":"","brand":"伟力兰Q","price":360,"step":"a:7:{s:2:\"a1\";s:1:\"1\";s:2:\"p1\";s:6:\"360.00\";s:2:\"a2\";s:1:\"0\";s:2:\"p2\";s:4:\"0.00\";s:2:\"a3\";s:1:\"0\";s:2:\"p3\";s:1:\"0\";s:2:\"is\";s:1:\"N\";}","amount":10,"unit":"件","tag":"","keyword":"伟力兰Q 新款儿童自行车成长之星4-8岁16寸男女宝宝小孩子单车脚踏车,伟力兰Q,自行车,自行车","pptword":"O78:伟力兰Q;O246:儿童自行车;O77:0-500;O187:单速;O87:其他;O89:16寸;O86:高碳钢;","hits":22,"orders":0,"sales":0,"comments":0,"thumb":"http://www.laiworld.com/file/upload/201606/03/1144275714.jpg.thumb.jpg","thumb1":"http://www.laiworld.com/file/upload/201606/03/1144412914.jpg.thumb.jpg","thumb2":"http://www.laiworld.com/file/upload/201606/03/1144462214.jpg.thumb.jpg","relate_name":"","relate_id":"","relate_title":"","n1":"颜色","n2":"","n3":"","v1":"粉红|翠绿","v2":"","v3":"","express_1":0,"express_name_1":"","fee_start_1":0,"fee_step_1":0,"express_2":0,"express_name_2":"","fee_start_2":0,"fee_step_2":0,"express_3":0,"express_name_3":"","fee_start_3":0,"fee_step_3":0,"cod":false,"username":"xiaoli","groupid":1,"company":"厉江园","vip":0,"validated":false,"truename":"厉江园","telephone":"","mobile":"","address":"","email":"","msn":"","qq":"","ali":"","skype":"","editor":"xiaoli","edittime":1465202318,"editdate":"2016-06-06T00:00:00","addtime":1464925363,"adddate":"2016-06-03T00:00:00","ip":"183.133.91.179","template":"","status":true,"linkurl":"show.php?itemid=993","filepath":"","note":"","yuanjia":0}
     */

    private String code;
    /**
     * itemid : 993
     * catid : 59
     * mycatid : 0
     * areaid : 119
     * level : true
     * elite : false
     * title : 伟力兰Q 新款儿童自行车成长之星4-8岁16寸男女宝宝小孩子单车脚踏车
     * style :
     * fee : 0.0
     * introduce :
     * brand : 伟力兰Q
     * price : 360.0
     * step : a:7:{s:2:"a1";s:1:"1";s:2:"p1";s:6:"360.00";s:2:"a2";s:1:"0";s:2:"p2";s:4:"0.00";s:2:"a3";s:1:"0";s:2:"p3";s:1:"0";s:2:"is";s:1:"N";}
     * amount : 10
     * unit : 件
     * tag :
     * keyword : 伟力兰Q 新款儿童自行车成长之星4-8岁16寸男女宝宝小孩子单车脚踏车,伟力兰Q,自行车,自行车
     * pptword : O78:伟力兰Q;O246:儿童自行车;O77:0-500;O187:单速;O87:其他;O89:16寸;O86:高碳钢;
     * hits : 22
     * orders : 0
     * sales : 0
     * comments : 0
     * thumb : http://www.laiworld.com/file/upload/201606/03/1144275714.jpg.thumb.jpg
     * thumb1 : http://www.laiworld.com/file/upload/201606/03/1144412914.jpg.thumb.jpg
     * thumb2 : http://www.laiworld.com/file/upload/201606/03/1144462214.jpg.thumb.jpg
     * relate_name :
     * relate_id :
     * relate_title :
     * n1 : 颜色
     * n2 :
     * n3 :
     * v1 : 粉红|翠绿
     * v2 :
     * v3 :
     * express_1 : 0
     * express_name_1 :
     * fee_start_1 : 0.0
     * fee_step_1 : 0.0
     * express_2 : 0
     * express_name_2 :
     * fee_start_2 : 0.0
     * fee_step_2 : 0.0
     * express_3 : 0
     * express_name_3 :
     * fee_start_3 : 0.0
     * fee_step_3 : 0.0
     * cod : false
     * username : xiaoli
     * groupid : 1
     * company : 厉江园
     * vip : 0
     * validated : false
     * truename : 厉江园
     * telephone :
     * mobile :
     * address :
     * email :
     * msn :
     * qq :
     * ali :
     * skype :
     * editor : xiaoli
     * edittime : 1465202318
     * editdate : 2016-06-06T00:00:00
     * addtime : 1464925363
     * adddate : 2016-06-03T00:00:00
     * ip : 183.133.91.179
     * template :
     * status : true
     * linkurl : show.php?itemid=993
     * filepath :
     * note :
     * yuanjia : 0
     */

    private InfoBean info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private int itemid;
        private int catid;
        private int mycatid;
        private int areaid;
        private boolean level;
        private boolean elite;
        private String title;
        private String style;
        private double fee;
        private String introduce;
        private String brand;
        private double price;
        private String step;
        private int amount;
        private String unit;
        private String tag;
        private String keyword;
        private String pptword;
        private int hits;
        private int orders;
        private int sales;
        private int comments;
        private String thumb;
        private String thumb1;
        private String thumb2;
        private String relate_name;
        private String relate_id;
        private String relate_title;
        private String n1;
        private String n2;
        private String n3;
        private String v1;
        private String v2;
        private String v3;
        private int express_1;
        private String express_name_1;
        private double fee_start_1;
        private double fee_step_1;
        private int express_2;
        private String express_name_2;
        private double fee_start_2;
        private double fee_step_2;
        private int express_3;
        private String express_name_3;
        private double fee_start_3;
        private double fee_step_3;
        private boolean cod;
        private String username;
        private int groupid;
        private String company;
        private int vip;
        private boolean validated;
        private String truename;
        private String telephone;
        private String mobile;
        private String address;
        private String email;
        private String msn;
        private String qq;
        private String ali;
        private String skype;
        private String editor;
        private int edittime;
        private String editdate;
        private int addtime;
        private String adddate;
        private String ip;
        private String template;
        private boolean status;
        private String linkurl;
        private String filepath;
        private String note;
        private int yuanjia;

        public int getItemid() {
            return itemid;
        }

        public void setItemid(int itemid) {
            this.itemid = itemid;
        }

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public int getMycatid() {
            return mycatid;
        }

        public void setMycatid(int mycatid) {
            this.mycatid = mycatid;
        }

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public boolean isLevel() {
            return level;
        }

        public void setLevel(boolean level) {
            this.level = level;
        }

        public boolean isElite() {
            return elite;
        }

        public void setElite(boolean elite) {
            this.elite = elite;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getPptword() {
            return pptword;
        }

        public void setPptword(String pptword) {
            this.pptword = pptword;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumb1() {
            return thumb1;
        }

        public void setThumb1(String thumb1) {
            this.thumb1 = thumb1;
        }

        public String getThumb2() {
            return thumb2;
        }

        public void setThumb2(String thumb2) {
            this.thumb2 = thumb2;
        }

        public String getRelate_name() {
            return relate_name;
        }

        public void setRelate_name(String relate_name) {
            this.relate_name = relate_name;
        }

        public String getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(String relate_id) {
            this.relate_id = relate_id;
        }

        public String getRelate_title() {
            return relate_title;
        }

        public void setRelate_title(String relate_title) {
            this.relate_title = relate_title;
        }

        public String getN1() {
            return n1;
        }

        public void setN1(String n1) {
            this.n1 = n1;
        }

        public String getN2() {
            return n2;
        }

        public void setN2(String n2) {
            this.n2 = n2;
        }

        public String getN3() {
            return n3;
        }

        public void setN3(String n3) {
            this.n3 = n3;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }

        public String getV2() {
            return v2;
        }

        public void setV2(String v2) {
            this.v2 = v2;
        }

        public String getV3() {
            return v3;
        }

        public void setV3(String v3) {
            this.v3 = v3;
        }

        public int getExpress_1() {
            return express_1;
        }

        public void setExpress_1(int express_1) {
            this.express_1 = express_1;
        }

        public String getExpress_name_1() {
            return express_name_1;
        }

        public void setExpress_name_1(String express_name_1) {
            this.express_name_1 = express_name_1;
        }

        public double getFee_start_1() {
            return fee_start_1;
        }

        public void setFee_start_1(double fee_start_1) {
            this.fee_start_1 = fee_start_1;
        }

        public double getFee_step_1() {
            return fee_step_1;
        }

        public void setFee_step_1(double fee_step_1) {
            this.fee_step_1 = fee_step_1;
        }

        public int getExpress_2() {
            return express_2;
        }

        public void setExpress_2(int express_2) {
            this.express_2 = express_2;
        }

        public String getExpress_name_2() {
            return express_name_2;
        }

        public void setExpress_name_2(String express_name_2) {
            this.express_name_2 = express_name_2;
        }

        public double getFee_start_2() {
            return fee_start_2;
        }

        public void setFee_start_2(double fee_start_2) {
            this.fee_start_2 = fee_start_2;
        }

        public double getFee_step_2() {
            return fee_step_2;
        }

        public void setFee_step_2(double fee_step_2) {
            this.fee_step_2 = fee_step_2;
        }

        public int getExpress_3() {
            return express_3;
        }

        public void setExpress_3(int express_3) {
            this.express_3 = express_3;
        }

        public String getExpress_name_3() {
            return express_name_3;
        }

        public void setExpress_name_3(String express_name_3) {
            this.express_name_3 = express_name_3;
        }

        public double getFee_start_3() {
            return fee_start_3;
        }

        public void setFee_start_3(double fee_start_3) {
            this.fee_start_3 = fee_start_3;
        }

        public double getFee_step_3() {
            return fee_step_3;
        }

        public void setFee_step_3(double fee_step_3) {
            this.fee_step_3 = fee_step_3;
        }

        public boolean isCod() {
            return cod;
        }

        public void setCod(boolean cod) {
            this.cod = cod;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public boolean isValidated() {
            return validated;
        }

        public void setValidated(boolean validated) {
            this.validated = validated;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getAli() {
            return ali;
        }

        public void setAli(String ali) {
            this.ali = ali;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public int getEdittime() {
            return edittime;
        }

        public void setEdittime(int edittime) {
            this.edittime = edittime;
        }

        public String getEditdate() {
            return editdate;
        }

        public void setEditdate(String editdate) {
            this.editdate = editdate;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public String getAdddate() {
            return adddate;
        }

        public void setAdddate(String adddate) {
            this.adddate = adddate;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getYuanjia() {
            return yuanjia;
        }

        public void setYuanjia(int yuanjia) {
            this.yuanjia = yuanjia;
        }
    }
}
