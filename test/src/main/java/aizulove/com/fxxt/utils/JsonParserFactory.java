package aizulove.com.fxxt.utils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.modle.entity.Activity;
import aizulove.com.fxxt.modle.entity.Address;
import aizulove.com.fxxt.modle.entity.Advert;
import aizulove.com.fxxt.modle.entity.City;
import aizulove.com.fxxt.modle.entity.CityList;
import aizulove.com.fxxt.modle.entity.Comment;
import aizulove.com.fxxt.modle.entity.Coupon;
import aizulove.com.fxxt.modle.entity.Information;
import aizulove.com.fxxt.modle.entity.Integration;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.modle.entity.Percentage;
import aizulove.com.fxxt.modle.entity.Pinglun;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.modle.entity.Recharge;
import aizulove.com.fxxt.modle.entity.Withdraw;

public class JsonParserFactory {

    protected static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //protected static SimpleDateFormat dateFormatHMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 车标列表解析
     */
    public static List<Logo> jsonParserLogoList(String jsonStr)throws JSONException {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Logo> list = new ArrayList<Logo>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Logo message = new Logo();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setLogoImg(jo.getString("logoImg"));
            if (!"null".equals(jo.getString("zan"))) {
                message.setZan(Integer.parseInt(jo.getString("zan")));//zan
            }
            message.setQuality(jo.getInt("quality"));//zan
            list.add(message);
        }
        return list;
    }

    public static List<Percentage> jsonParserPercentageList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1=object.getJSONObject("info");
        JSONArray ja = object1.getJSONArray("percentage");
        int jaSize = ja.length();
        List<Percentage> list = new ArrayList<Percentage>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Percentage message = new Percentage();
            message.setId(jo.getInt("id"));
            message.setAmont(Float.parseFloat(jo.getString("amont")));
            message.setName(jo.getString("name"));
            message.setLevel(jo.getString("level"));//
           // message.setCreateDate(dateFormat.parse(jo.getString("createDate")));
            message.setOrderId(jo.getString("orderId"));//
            message.setTruename(jo.getString("truename"));
            message.setTitle(jo.getString("title"));
            message.setProportion(jo.getString("point"));
            message.setCreateDate(dateFormat.parse(jo.getString("createDate")));
            list.add(message);
        }
        return list;
    }


    public static List<Recharge> jsonParserRechargeList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1=object.getJSONObject("info");
        JSONArray ja = object1.getJSONArray("percentage");
        int jaSize = ja.length();
        List<Recharge> list = new ArrayList<Recharge>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Recharge message = new Recharge();
            message.setId(jo.getInt("id"));
            message.setAmont(Float.parseFloat(jo.getString("amont")));
            message.setName(jo.getString("name"));
            message.setLevel(jo.getString("level"));//
            // message.setCreateDate(dateFormat.parse(jo.getString("createDate")));
            message.setOrderId(jo.getString("orderId"));//
            message.setTruename(jo.getString("truename"));
            message.setTitle(jo.getString("title"));
            message.setCreateDate(dateFormat.parse(jo.getString("createDate")));
            list.add(message);
        }
        return list;
    }

    public static List<Withdraw> jsonParserWithdrawList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Withdraw> list = new ArrayList<Withdraw>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Withdraw message = new Withdraw();
            message.setId(jo.getInt("id"));
            message.setAmont(Float.parseFloat(jo.getString("amont")));
            message.setName(jo.getString("name"));
            message.setBankCard(jo.getString("bankCard"));//
            message.setBankCardNo(jo.getString("bankCardNo"));
            if (!"null".equals(jo.getString("confirmDate"))) {
                message.setConfirmDate(dateFormat.parse(jo.getString("confirmDate")));//
            }
            message.setCreateDate(dateFormat.parse(jo.getString("createDate")));
            message.setReason(jo.getString("reason"));
            message.setStatus(jo.getInt("status"));//
            message.setTrade(jo.getString("trade"));
            message.setTruename(jo.getString("truename"));
            message.setUserId(jo.getInt("userId"));
            message.setWithdrawId(jo.getString("withdrawId"));
            list.add(message);
        }
        return list;
    }

    /**
     * 俱乐部列表解析
     */
    public static List<Post> jsonParserPostList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Post> list = new ArrayList<Post>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Post message = new Post();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setContent(jo.getString("content"));
            message.setCount(jo.getInt("count"));
            message.setImg(jo.getString("img"));
            message.setSource(jo.getString("source"));
            JSONObject oj= jo.getJSONObject("member");
            if(null!=oj){
                Member member=new Member();
                member.setAvatarUrl(oj.getString("avatarUrl"));
                message.setMember(member);
            }
            message.setCreatTime(dateFormat.parse(jo.getString("creatTime")));
            JSONArray commentList = jo.getJSONArray("commentList");
            if (null!=commentList) {
                List<Comment> l = new ArrayList<Comment>();
                for (int j = 0; j < commentList.length(); j++) {
                    JSONObject json = commentList.getJSONObject(j);
                    Comment comment = new Comment();
                    comment.setId(json.getInt("id"));
                    comment.setTitle(json.getString("title"));
                    comment.setContent(json.getString("content"));
                    comment.setCreatTime(dateFormat.parse(json.getString("creatTime")));
                    String member1=json.getString("member");
                    if (null != member1&&!"null".equals(member1)) {
                        JSONObject oj1 = json.getJSONObject("member");
                        Member member = new Member();
                        member.setAvatarUrl(oj1.getString("avatarUrl"));
                        member.setUsername(oj1.getString("username"));
                        comment.setMember(member);
                    }
                    l.add(comment);
                }
                message.setCommentList(l);
            }
            if (!"null".equals(jo.getString("zan"))) {
                message.setZan(Integer.parseInt(jo.getString("zan")));//zan
            }else{

            }
            if (!"null".equals(jo.getString("visit"))){
                message.setVisit(Integer.parseInt(jo.getString("visit")));
            }
            list.add(message);
        }
        return list;
    }

    /**
     * 俱乐部详情解析
     */
    public static Logo getPostListByLogoId(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject ja = object.getJSONObject("info");
        JSONObject jo=ja.getJSONObject("logo");
            Logo message = new Logo();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setContent(jo.getString("content"));
            message.setLogoImg(jo.getString("logoImg"));
            if (!"null".equals(jo.getString("zan"))) {
                message.setZan(Integer.parseInt(jo.getString("zan")));//zan
            }
            message.setQuality(jo.getInt("quality"));//zan
            message.setIsAttention(jo.getInt("isAttention"));
            //设置post
            JSONArray ja1 = ja.getJSONArray("postList");
            List<Post> temp=new ArrayList<Post>();
            int jaSize1 = ja1.length();
            for (int j=0;j<jaSize1;j++){
                JSONObject jo1 = ja1.getJSONObject(j);
                Post post=new Post();
                post.setId(jo1.getInt("id"));
                post.setCount(jo1.getInt("count"));
                post.setSource(jo1.getString("source"));
                post.setTitle(jo1.getString("title"));
                post.setContent(jo1.getString("content"));
                post.setImg(jo1.getString("img"));
                if (!"null".equals(jo1.getString("zan"))) {
                    post.setZan(Integer.parseInt(jo1.getString("zan")));
                }
                if (!"null".equals(jo1.getString("visit"))) {
                    post.setVisit(Integer.parseInt(jo1.getString("visit")));
                }
                post.setCreatTime(dateFormat.parse(jo1.getString("creatTime")));
                //JSONArray comment =jo1.getJSONArray("commentList");
                //post.setSize(null==comment?0:comment.length());
                JSONObject oj= jo1.getJSONObject("member");
                if(null!=oj){
                    Member member=new Member();
                    member.setAvatarUrl(oj.getString("avatarUrl"));
                    post.setMember(member);
                }
                temp.add(post);
            }
            message.setPostList(temp);
//        }
        return message;
    }
    /**
     *
     */
    public static Post getPostById(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1 = object.getJSONObject("info");
        JSONObject ja=object1.getJSONObject("post");
        Post post=new Post();
        post.setCount(object1.getInt("count"));
        post.setId(ja.getInt("id"));
        post.setSource(ja.getString("source"));
        post.setTitle(ja.getString("title"));
        post.setContent(ja.getString("content"));
        post.setImg(ja.getString("img"));
        if (!"null".equals(ja.getString("zan"))) {
            post.setZan(Integer.parseInt(ja.getString("zan")));
        }
        if (!"null".equals(ja.getString("visit"))) {
            post.setVisit(Integer.parseInt(ja.getString("visit")));
        }
        post.setCreatTime(dateFormat.parse(ja.getString("creatTime")));
        JSONObject oj= ja.getJSONObject("member");
        if(null!=oj){
            Member member=new Member();
            member.setAvatarUrl(oj.getString("avatarUrl"));
            post.setMember(member);
        }
        JSONArray ja1 = ja.getJSONArray("commentList");
        List<Comment> temp=new ArrayList<Comment>();
        int jaSize1 = ja1.length();
        for (int j=0;j<jaSize1;j++){
            JSONObject jo1 = ja1.getJSONObject(j);
            Comment comment=new Comment();
            comment.setId(jo1.getInt("id"));
            comment.setPostId(jo1.getInt("postId"));
            comment.setTitle(jo1.getString("title"));
            comment.setContent(jo1.getString("content"));
            comment.setCreatTime(dateFormat.parse(jo1.getString("creatTime")));
            JSONObject oj1= jo1.getJSONObject("member");
            if(null!=oj1){
                Member member=new Member();
                member.setAvatarUrl(oj1.getString("avatarUrl"));
                member.setUsername(oj1.getString("username"));
                comment.setMember(member);
            }
            temp.add(comment);
        }
        post.setCommentList(temp);
//        }
        return post;
    }

    /**
     * 促销列表解析
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static Product getJsonCuXiaoById(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject jo = object.getJSONObject("info");
        Product message = new Product();
        message.setItemid(jo.getInt("itemid"));
        message.setTitle(jo.getString("title"));

        message.setAmount(jo.getInt("amount"));
        message.setBrand(jo.getString("brand"));
        message.setV1(jo.getString("v1"));
        message.setV2(jo.getString("v2"));
        message.setV3(jo.getString("v3"));
        message.setN1(jo.getString("n1"));
        message.setN2(jo.getString("n2"));
        message.setN3(jo.getString("n3"));
        message.setYuanjia(jo.getInt("yuanjia"));
        //Log.i("susu", String.valueOf(jo.getInt("yuanjia")));
        message.setPrice(jo.getInt("price"));

        message.setThumb(jo.getString("thumb"));
        message.setThumb1(jo.getString("thumb1"));
        message.setThumb2(jo.getString("thumb2"));
        message.setContent(jo.getString("content"));
        return message;
    }

    /**
     * 促销详情
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static List<Product> jsonParserCuXiaoMallList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject jsonObject1=object.getJSONObject("info");
        JSONArray ja = jsonObject1.getJSONArray("mallList");
        int jaSize = ja.length();
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Product message = new Product();
            message.setItemid(jo.getInt("itemid"));
            message.setTitle(jo.getString("title"));
            message.setPrice(jo.getInt("price"));
            message.setThumb(jo.getString("thumb"));
            list.add(message);
        }
        return list;
    }
    /**
     * 评论列表
     */
    public static List<Pinglun> getCommentListByMallId(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray array=object.getJSONArray("info");
        int jaSize = array.length();
        List<Pinglun> list = new ArrayList<Pinglun>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = array.getJSONObject(i);
            Pinglun message = new Pinglun();
            message.setSeller_comment(jo.getString("seller_comment"));
            message.setBuyer(jo.getString("buyer"));
            message.setBuyer_comment(jo.getString("buyer_comment"));
            message.setSeller(jo.getString("seller"));
            list.add(message);
        }
        return list;
    }
    /**
     * 省份列表
     */
    public static List<City> getProvinceList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja=object.getJSONArray("info");
        int jaSize = ja.length();
        List<City> list = new ArrayList<City>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            City message = new City();
            message.setAreaid(jo.getInt("areaid"));
            message.setAreaname(jo.getString("areaname"));
            message.setParentid(jo.getInt("parentid"));
            message.setArrparentid(jo.getString("arrparentid"));
            message.setChild(jo.getBoolean("child"));
            message.setArrchildid( jo.getString("arrchildid"));
            message.setListorder(jo.getInt("listorder"));
            list.add(message);
        }
        return list;
    }
    /**
     * 城市列表
     */
    public static List<CityList> getCityList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja=object.getJSONArray("info");
        int jaSize = ja.length();
        //Log.i("susu", String.valueOf(jaSize));
        List<CityList> list = new ArrayList<CityList>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            CityList message = new CityList();
            message.setCityId(jo.getString("areaid"));
            message.setCityName(jo.getString("areaname"));
            list.add(message);
        }
        return list;
    }

    /**
     * 分类列表
     */
    public static List<Product> getMallListByKeyword(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja=object.getJSONArray("info");
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            Product message = new Product();
            message.setItemid(jo.getInt("itemid"));
            message.setTitle(jo.getString("title"));
            message.setPrice(jo.getInt("price"));
            message.setThumb(jo.getString("thumb"));
            list.add(message);
        }
        return list;
    }
    /**
     * 忘记密码
     */
    public static  String forgetMember(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        return code;
    }


    /**
     * 购物车列表
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static List<Product> getCartListByUserId(String jsonStr)throws Exception {
        List<Product> list = new ArrayList<Product>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray=object.getJSONArray("info");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            JSONArray ja=jo.getJSONArray("cartItemList");
            for (int j=0;j<ja.length();j++){
                Product message = new Product();
                JSONObject jo2=ja.getJSONObject(j);
                message.setData(jo2.getString("data"));
                message.setI(jo2.getInt("i"));
                message.setOut_v1(jo2.getString("v1"));
                message.setOut_v2(jo2.getString("v2"));
                message.setOut_v3(jo2.getString("v3"));
                JSONObject jo3=jo2.getJSONObject("mall");
                message.setTitle(jo3.getString("title"));
                message.setPrice(jo3.getInt("price"));
                message.setItemid(jo3.getInt("itemid"));
                message.setThumb(jo3.getString("thumb"));
                message.setThumb1(jo3.getString("thumb1"));
                message.setThumb2(jo3.getString("thumb2"));
                message.setV1(jo3.getString("v1"));
                message.setV2(jo3.getString("v2"));
                message.setV3(jo3.getString("v3"));
                message.setN1(jo3.getString("n1"));
                message.setN2(jo3.getString("n2"));
                message.setN3(jo3.getString("n3"));
                message.setEditor(jo3.getString("editor"));
                message.setFee(jo3.getDouble("fee"));
                message.setNote(jo3.getString("note"));
                list.add(message);
            }
        }
        return list;
    }
    /**
     *获取订单号
     */
    public static String getMallOrderCode(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject jo=object.getJSONObject("info");
        String list=jo.getString("tradeNo");
        return list;
    }
    /**
     * 上传图片
     */
    public static String uploadPortraits(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "上传成功";
        }
    }
    /**
     * 修改支付状态
     */
    public static String modifyMallOrderStatus(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "修改成功";
        }
    }
    /**
     * 地址管理
     */
    public static List<Address> getAddressListByUsername(String jsonStr)throws Exception {
        List<Address> list = new ArrayList<Address>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray=object.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            Address message = new Address();
            message.setName(jo.getString("truename"));
            message.setItemid(jo.getInt("itemid"));
            message.setAddress(jo.getString("address"));
            message.setPostcode(jo.getString("postcode"));
            message.setPhone(jo.getString("telephone"));
            message.setAreaid(jo.getInt("areaid"));
            message.setEditor(jo.getString("editor"));
            message.setEdittime(jo.getString("edittime"));
            message.setAddtime(jo.getString("addtime"));
            message.setListorder(jo.getInt("listorder"));
            message.setAreaid(jo.getInt("areaid"));
            message.setAreaname(jo.getString("areaname"));
            message.setMobile(jo.getString("mobile"));
            message.setUsername(jo.getString("username"));
            message.setNote(jo.getString("note"));
            message.setCityid(jo.getInt("cityid"));
            message.setCityname(jo.getString("name"));
            list.add(message);
        }
        return list;
    }

    /**
     * 积分
     */
    public static List<Integration> getCreditListByUsername(String jsonStr)throws Exception {
        List<Integration> list = new ArrayList<Integration>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray=object.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            Integration message = new Integration();
            message.setBalance(jo.getInt("balance"));
            message.setAddtime(jo.getString("addtime"));
            message.setReason(jo.getString("reason"));
            list.add(message);
        }
        return list;
    }
    /**
     * 消息
     */
    public static List<Integration> getMessageListByUsername(String jsonStr)throws Exception {
        List<Integration> list = new ArrayList<Integration>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray=object.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            Integration message = new Integration();
            message.setTitle(jo.getString("title"));
            message.setAddtime(jo.getString("addtime"));
            message.setContent(jo.getString("content"));
            list.add(message);
        }
        return list;
    }

    /**
     * 订单
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static List<Product> getOrderListByUsername(String jsonStr)throws Exception {
        List<Product> list = new ArrayList<Product>();
        JSONObject object = new JSONObject(jsonStr);
        JSONArray jsonArray=object.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            Product message = new Product();
            message.setTitle(jo.getString("title"));
            message.setPrice(jo.getInt("price"));
            message.setThumb(jo.getString("thumb"));
            message.setNumber(jo.getInt("number"));
            message.setAmount(jo.getInt("amount"));
            message.setTrade_no(jo.getString("trade_no"));
            message.setNote(jo.getString("note"));
            message.setStatus(jo.getInt("status"));
            list.add(message);
        }
        return list;
    }


    public static Member jsonParserMember(String jsonStr)throws JSONException {
        JSONObject object = new JSONObject(jsonStr);
        if (object.getString("code").equals("1000")) {
            Member member=new Member();
            JSONObject object1 = object.getJSONObject("info");
            JSONObject jo=object1.getJSONObject("member");
            member.setUserid(jo.getInt("userid"));
            member.setUsername(jo.getString("username"));
            member.setPassword(jo.getString("password"));
            member.setToken(jo.getString("token"));
            member.setMobile(jo.getString("mobile"));
            member.setCredit(jo.getInt("credit"));
            member.setEmail(jo.getString("email"));
            member.setAvatarUrl(jo.getString("avatarUrl"));
            member.setGender(jo.getInt("gender"));

            member.setLevel(jo.getString("level"));
            member.setReferralCode(jo.getString("referralCodeSelf"));


            member.setTruename(jo.getString("truename"));
            member.setSkype(jo.getString("skype"));
            member.setQq(jo.getString("qq"));
            member.setAli(jo.getString("ali"));
            member.setDepartment(jo.getString("department"));
            member.setSound(jo.getInt("sound"));
            member.setCareer(jo.getString("career"));
            member.setCity(jo.getString("city"));
            member.setProvince(jo.getString("province"));
            member.setAreaid(jo.getInt("areaid"));

            member.setLogintimes(jo.getInt("logintimes"));
            member.setMoney(Float.parseFloat(jo.getString("money")));

            member.setAttentionids(jo.getString("attentionids"));
            return member;
        }
        return null;
    }

    public static Member getMemberById(String jsonStr)throws JSONException {
        JSONObject object = new JSONObject(jsonStr);
        if (object.getString("code").equals("1000")) {
            Member member=new Member();
            JSONObject jo=object.getJSONObject("info");
            member.setUserid(jo.getInt("userid"));
            member.setUsername(jo.getString("username"));
            member.setPassword(jo.getString("password"));
            member.setToken(jo.getString("token"));
            member.setMobile(jo.getString("mobile"));
            member.setCredit(jo.getInt("credit"));
            member.setEmail(jo.getString("email"));
            member.setAvatarUrl(jo.getString("avatarUrl"));
            member.setGender(jo.getInt("gender"));

            member.setLevel(jo.getString("level"));
            member.setReferralCode(jo.getString("referralCodeSelf"));


            member.setTruename(jo.getString("truename"));
            member.setSkype(jo.getString("skype"));
            member.setQq(jo.getString("qq"));
            member.setAli(jo.getString("ali"));
            member.setDepartment(jo.getString("department"));
            member.setSound(jo.getInt("sound"));
            member.setCareer(jo.getString("career"));
            member.setCity(jo.getString("city"));
            member.setProvince(jo.getString("province"));
            member.setAreaid(jo.getInt("areaid"));

            member.setLogintimes(jo.getInt("logintimes"));
            member.setMoney(Float.parseFloat(jo.getString("money")));

            member.setAttentionids(jo.getString("attentionids"));
            return member;
        }
        return null;
    }
    /**
     * 第三方登陆
     */

    public static Member thirdloginMember(String jsonStr)throws JSONException {
        JSONObject object = new JSONObject(jsonStr);
        if (object.getString("code").equals("1000")) {
            Member member=new Member();
            member.setCode(object.getString("code"));
            JSONObject jo=object.getJSONObject("info");
            member.setUserid(jo.getInt("userid"));
            member.setUsername(jo.getString("username"));
            member.setPassword(jo.getString("password"));
            member.setToken(jo.getString("token"));
            member.setMobile(jo.getString("mobile"));
            member.setCredit(jo.getInt("credit"));
            member.setEmail(jo.getString("email"));
            member.setAvatarUrl(jo.getString("avatarUrl"));

            member.setLevel(jo.getString("level"));

            member.setTruename(jo.getString("truename"));
            member.setSkype(jo.getString("skype"));
            member.setQq(jo.getString("qq"));
            member.setAli(jo.getString("ali"));
            member.setDepartment(jo.getString("department"));
            member.setSound(jo.getInt("sound"));
            member.setCareer(jo.getString("career"));
            member.setAreaid(jo.getInt("areaid"));

            member.setLogintimes(jo.getInt("logintimes"));
            member.setMoney(Float.parseFloat(jo.getString("money")));
            return member;
        }else if (object.getString("code").equals("1003")){
            Member member=new Member();
            member.setCode(object.getString("code"));
            return member;
        }
        return null;
    }
    /**
     * 车标列表解析
     */
    public static List<Activity> jsonParserActivityList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Activity> list = new ArrayList<Activity>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Activity message = new Activity();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setImg(jo.getString("img"));
            message.setAddress(jo.getString("address"));//zan
            message.setContent(jo.getString("content"));
            message.setCreateTime(dateFormat.parse(jo.getString("createTime")));
            message.setStartdate(dateFormat.parse(jo.getString("startdate")));//zan
            message.setEnddate(dateFormat.parse(jo.getString("enddate")));
            message.setLishi(jo.getString("lishi"));
            message.setPreside(jo.getString("preside"));
            message.setUserName(jo.getString("userName"));
            list.add(message);
        }
        return list;
    }

    public static List<Product> jsonParserProductList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1 = object.getJSONObject("info");
        List<Product> list = new ArrayList<Product>();
        JSONArray ja=object1.getJSONArray("mallList");
        int jaSize = ja.length();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Product product=new Product();
            product.setItemid(jo.getInt("itemid"));
            product.setTitle(jo.getString("title"));
            product.setPrice(jo.getInt("price"));
            product.setThumb(jo.getString("thumb"));
            list.add(product);
        }

        return list;
    }
    public static List<Product> getHotAttentionList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        List<Product> list = new ArrayList<Product>();
        int jaSize = ja.length();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Product product=new Product();
            product.setItemid(jo.getInt("itemid"));
            product.setTitle(jo.getString("title"));
            product.setPrice(jo.getInt("price"));
            product.setThumb(jo.getString("thumb"));
            list.add(product);
        }

        return list;
    }


    public static List<Advert> jsonParserAdvertList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1 = object.getJSONObject("info");
        JSONArray ja=object1.getJSONArray("picList");
        int jaSize = ja.length();
        System.out.println("jaSize==="+jaSize);
        List<Advert> list = new ArrayList<Advert>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Advert advert=new Advert();
            advert.setAid(jo.getInt("aid"));
            advert.setTitle(jo.getString("title"));
            advert.setUrl(jo.getString("url"));
            advert.setImageSrc(jo.getString("image_src"));
            list.add(advert);
        }

        return list;
    }


    public static List<Coupon> jsonParserCouponList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONObject object1 = object.getJSONObject("info");
        JSONArray ja=object1.getJSONArray("myCouponList");
        int jaSize = ja.length();
        List<Coupon> list = new ArrayList<Coupon>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo1 = ja.getJSONObject(i);
            JSONObject jo = jo1.getJSONObject("coupon");
            Coupon message = new Coupon();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setPromoImage(jo.getString("promoImage"));
            message.setPromoCode(jo.getString("promoCode"));//zan
            message.setPromoPrice(jo.getDouble("promoPrice"));
            message.setExpiryStatus(jo.getInt("expiryStatus"));
            message.setStartTime(dateFormat.parse(jo.getString("startTime")));
            message.setOverTime(dateFormat.parse(jo.getString("overTime")));
            message.setBrief(jo.getString("brief"));
            message.setTerm(jo.getInt("term"));

            list.add(message);
        }
        return list;
    }

    public static List<Logo> jsonParserGuanzhuList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Logo> list = new ArrayList<Logo>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Logo message = new Logo();
            message.setId(jo.getInt("id"));
            message.setTitle(jo.getString("title"));
            message.setLogoImg(jo.getString("logoImg"));
            message.setQuality(jo.getInt("quality"));//zan
            message.setContent(jo.getString("content"));
            message.setZan(jo.getInt("zan"));
            message.setCreateTime(dateFormat.parse(jo.getString("createTime")));
            list.add(message);
        }
        return list;
    }

    /**
     * 车标列表解析
     */
    public static List<Information> jsonParserInformationList(String jsonStr)throws Exception {
        JSONObject object = new JSONObject(jsonStr);
        JSONArray ja = object.getJSONArray("info");
        int jaSize = ja.length();
        List<Information> list = new ArrayList<Information>();
        for (int i = 0; i < jaSize; i++) {
            JSONObject jo = ja.getJSONObject(i);
            Information message = new Information();
            message.setId(jo.getInt("id"));
            //message.setTitle(jo.getString("title"));
            message.setImg(jo.getString("img"));
            message.setCreatName(jo.getString("creatName"));//creatName
            message.setCreatTime(dateFormat.parse(jo.getString("creatTime")));
            message.setCreatUser(jo.getString("creatUser"));
            message.setDescription(jo.getString("description"));
            message.setSource(jo.getString("source"));
            if(!"null".equals(jo.getString("visit"))) {
                message.setVisit(Integer.parseInt(jo.getString("visit")));
            }
            list.add(message);
        }
        return list;
    }

    public static  String addZanStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "点赞成功";
        }
    }

    /**
     * 修改密码
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static  String modifyPassword(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "修改成功";
        }
    }
    public static  String getReferralCodeByUserId(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("info");
        return code;
    }

    public static  String TxianStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "提现申请可能需要几个工作日,请耐心等待！";
        }
    }

    public static  String ChongzhiStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "充值失败！";
        }
    }

    public static  String UpoladStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        Log.i("susu",code+"  "+message);
        if (code.equals("1000")){
            return message;
        }else{
            return "上传失败！";
        }
    }

    public static  String RechargeCodeStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        JSONObject message=object.getJSONObject("info");
       return  message.getString("orderCode");

    }


    public static  String addLogoZanStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "点赞成功";
        }
    }

    public static  String addAttentionLogoStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return message;
        }
    }

    public static  String cancelAttentionStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "已取消关注";
        }
    }

    /**
     * 商品移除购物车
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static  String delOperate(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "删除成功";
        }
    }
    /**
     * 余额支付
     */
    public static  String moneyPay(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "成功";
        }
    }
    /**
     * 修改地址
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static  String editAddress(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")){
            return message;
        }else{
            return "成功！";
        }
    }
    public static  String registMember(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String info="注册成功";
        if (code.equals("1001")){
            String message=object.getString("message");
            return message;
        }else{
            return info;
        }
    }


    public static  String addCommentStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")) {
            return message;
        }else{
            return "评论成功";
        }
    }


    public static  String addActivietStr(String jsonStr)throws JSONException{
        JSONObject object = new JSONObject(jsonStr);
        String code=object.getString("code");
        String message=object.getString("message");
        if (code.equals("1001")) {
            return message;
        }else{
            return "成功报名";
        }
    }

}
