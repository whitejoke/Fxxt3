package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

public class MyActivity extends BaseActivity{

    private ImageView avatarurl;
    private TextView username;
    private TextView jifen;
    private LinearLayout ll_jifen;
    private DisplayImageOptions options;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_my, null);
        layoutContent.addView(convertView);
    }



    protected void findViews() {
        avatarurl=(ImageView)findViewById(R.id.avatarurl);
        username=(TextView)findViewById(R.id.username);
        jifen=(TextView)findViewById(R.id.jifen);
        ll_jifen= (LinearLayout) findViewById(R.id.ll_jifen);
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
    }

    protected  void setSelecttab() {
        SELECTTAB=4;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        Member member=getMemberSharedPreference();
        if (member.getUsername()!="") {
            if (member.getTruename().equals("")){
                username.setText(member.getUsername());
            }else {
                username.setText(member.getTruename());
            }
            jifen.setText(String.valueOf(member.getCredit()));
        }else {
            ll_jifen.setVisibility(View.INVISIBLE);
            username.setText("点击登陆");
        }
        if(null!= member.getAvatarUrl() &&!member.getAvatarUrl().equals("null")&&!member.getAvatarUrl().equals("")) {
            //Log.i("susu",getMemberSharedPreference().getAvatarUrl()+"++++++++++");
            ImageLoader.getInstance().displayImage(VariablesOfUrl.imagePath+"/file/head/"+member.getAvatarUrl(), avatarurl, options, null);
            Picasso.with(context).load(VariablesOfUrl.imagePath+"/file/head/"+member.getAvatarUrl()).into(avatarurl);
        }else{
            Log.i("susu",member.getAvatarUrl());
            avatarurl.setImageResource(R.mipmap.ic_user);
        }
    }


    public void click(View view) {
        Intent intent;
        switch (view.getId()){
        case R.id.jifenrl:
            if(Data().equals("")) {
                intent = new Intent();
                intent.setClass(context, HostActivity.class);
                startActivity(intent);
                finish();
            }else {
                intent = new Intent();
                intent.setClass(context, MyJifenActivity.class);
                startActivity(intent);
            }
         break;
        case  R.id.fenxiaorl:
            if(Data().equals("")) {
                intent = new Intent();
                intent.setClass(context, HostActivity.class);
                startActivity(intent);
                finish();
            }else{
            intent = new Intent();
            intent.setClass(context, DistributionHostActivity.class);
            startActivity(intent);}
        break;
            case  R.id.myyouhuirl:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                intent = new Intent();
                intent.setClass(context, YouhuiActivity.class);
                    intent.putExtra("type","main");
                    intent.putExtra("term",9999999);
                startActivity(intent);}
         break;
        case  R.id.avatarurl:
            if(Data().equals("")) {
                intent = new Intent();
                intent.setClass(context, HostActivity.class);
                startActivity(intent);
                finish();
            }else{
            intent = new Intent();
            intent.setClass(context, UserCenterActivity.class);
            startActivity(intent);}
            break;
        case  R.id.usercenteriv:
            intent = new Intent();
            intent.setClass(context, MySettingActivity.class);
            startActivity(intent);
            break;
        case  R.id.message:
            if(Data().equals("")) {
                intent = new Intent();
                intent.setClass(context, HostActivity.class);
                startActivity(intent);
                finish();
            }else{
            intent = new Intent();
            intent.setClass(context, MymessageActivity.class);
            startActivity(intent);}
            break;
            case  R.id.myguanzu:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                intent = new Intent();
                intent.setClass(context, MyguanzuActivity.class);
                startActivity(intent);}
                break;
            case R.id.myorders:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                intent = new Intent();
                intent.setClass(context,MyOderActivity.class);
                startActivity(intent);}
                break;
            case R.id.myCars:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                intent = new Intent();
                intent.setClass(context,MyCartActivity.class);
                startActivity(intent);}
                break;
//            case R.id.help:
//                intent = new Intent();
//                intent.setClass(context,HelpActivity.class);
//                startActivity(intent);
//                break;
            case R.id.llAdress:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    intent = new Intent();
                    intent.setClass(context,AddressActivity.class);
                    intent.putExtra("type","main");
                    startActivity(intent);}
             break;
            case R.id.llShare:
                showShare(this, null, false);
                break;
        }
    }

    /**
     * 演示调用ShareSDK执行分享
     *
     * @param context
     * @param platformToShare  指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit  是否显示编辑页
     */
    public static void showShare(Context context, String platformToShare, boolean showContentEdit) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
        oks.setTitle("蓝车世界");
        oks.setTitleUrl("http://www.laiworld.com");
        oks.setText("蓝车世界是一个经营自行车、电瓶车、电动汽车等代步工具的平台，集商城、资讯和论坛于一身.我们提供优质商品，100%正品保障。\\n    我们的目标：让出行更安全、更环保、更健康、更快乐！.");
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl("http://114.215.238.0:9001/fxxt/file/head/52_member.png");
        oks.setUrl("http://www.laiworld.com"); //微信不绕过审核分享链接
        //oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        oks.setComment("分享"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite(String.valueOf(R.string.app_name));  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl("http://www.laiworld.com");//QZone分享参数
        oks.setVenueName("ShareSDK");
        oks.setVenueDescription("This is a beautiful place!");
//         将快捷分享的操作结果将通过OneKeyShareCallback回调
//        oks.setCallback(new OneKeyShareCallback());
//         去自定义不同平台的字段内容
//        oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
//         在九宫格设置自定义的图标
//        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon);
//        String label = "蓝车世界";
//        View.OnClickListener listener = new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        };
//        oks.setCustomerLogo(logo, label, listener);
//         启动分享
        oks.show(context);
    }

    public static String[] randomPic() {
        String url = "http://git.oschina.net/alexyu.yxj/MyTmpFiles/raw/master/kmk_pic_fld/";
        String urlSmall = "http://git.oschina.net/alexyu.yxj/MyTmpFiles/raw/master/kmk_pic_fld/small/";
        String[] pics = new String[] {
                "120.JPG",
                "127.JPG",
                "130.JPG",
                "18.JPG",
                "184.JPG",
                "22.JPG",
                "236.JPG",
                "237.JPG",
                "254.JPG",
                "255.JPG",
                "263.JPG",
                "265.JPG",
                "273.JPG",
                "37.JPG",
                "39.JPG",
                "IMG_2219.JPG",
                "IMG_2270.JPG",
                "IMG_2271.JPG",
                "IMG_2275.JPG",
                "107.JPG"
        };
        int index = (int) (System.currentTimeMillis() % pics.length);
        return new String[] {
                url + pics[index],
                urlSmall + pics[index]
        };
    }

}
