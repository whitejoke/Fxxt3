package aizulove.com.fxxt.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.modle.page.PercentagePage;
import aizulove.com.fxxt.modle.page.RechargePage;
import aizulove.com.fxxt.modle.page.WithdrawPage;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.SlidingTabLayout;
import aizulove.com.fxxt.view.ToastSingle;

public class DistributionHostActivity extends BaseActivity implements View.OnClickListener{

	private TextView jiner,tv_fenxiaocode,tv_erweima;
	private LinearLayout fenxiaoma;
	private TextView cz;
	private TextView tx;
	private ImageView erweimaimageView;
	ClipboardManager myClipboard;

	public void setContentViews(){
		View convertView = mInflater.inflate(R.layout.activity_distribution, null);
		layoutContent.addView(convertView);
		sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
		findViews();
		DataTask();
		HiddenMeun();
	}

	protected void findViews() {
		myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
		jiner=(TextView)findViewById(R.id.jiner);
		cz=(TextView)findViewById(R.id.cz);
		tx=(TextView)findViewById(R.id.tx);
		rightimageView=(ImageView)findViewById(R.id.rightimageView);
		((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
		blakimageView= ((ImageView)findViewById(R.id.blakimageView));
		blakimageView .setVisibility(View.VISIBLE);
		blakimageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((TextView)findViewById(R.id.head_title)).setText("我的分销");
		mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
		mTabLayout = (SlidingTabLayout) findViewById(R.id.id_tab) ;
		fenxiaoma= (LinearLayout) findViewById(R.id.ll_fenxiaoma);
		tv_fenxiaocode= (TextView) findViewById(R.id.tv_fenxiaocode);
		tv_erweima= (TextView) findViewById(R.id.tv_erweima);
		tv_erweima.setOnClickListener(this);
		tv_fenxiaocode.setOnClickListener(this);
		cz.setOnClickListener(this);
		tx.setOnClickListener(this);
	}

	/**
	 *
	 *
	 */
	protected  void setSelecttab() {
		SELECTTAB=4;
	}

	/**
	 *
	 * 逻辑处理方法
	 */
	protected void DataTask(){
		mTab.clear();
		mTab.add(new PercentagePage("佣金")) ;
		mTab.add(new RechargePage("充值")) ;
		mTab.add(new WithdrawPage("取现")) ;
		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
		mTabLayout.setViewPager(mViewPager);
		jiner.setText(String.valueOf(getMemberSharedPreference().getMoney()));
		Log.i("susu",getMemberSharedPreference().getLevel());
		Log.i("susu",getMemberSharedPreference().getReferralCode());
		if (!getMemberSharedPreference().getLevel().equals("普通会员")&&!getMemberSharedPreference().getLevel().equals("")){
			fenxiaoma.setVisibility(View.VISIBLE);
			if (!getMemberSharedPreference().getReferralCode().equals("")&&!getMemberSharedPreference().getReferralCode().equals("null")&&!getMemberSharedPreference().getReferralCode().equals(null)){
				tv_erweima.setVisibility(View.VISIBLE);
				tv_fenxiaocode.setText("我的分销码:"+getMemberSharedPreference().getReferralCode());
			}
		}

		Map<String,String>map=new HashMap<String,String>();
		map.put("userId",getMemberSharedPreference().getUserid()+"");
		new  MemberUpdateTask(context,map).execute();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()){
			case R.id.cz:
				intent = new Intent();
				intent.setClass(context, ChongZhiActivity.class);
				startActivityForResult(intent, 1);
				break;
			case  R.id.tx:
				intent = new Intent();
				intent.putExtra("money",String.valueOf(getMemberSharedPreference().getMoney()));
				intent.setClass(context, TiXianActivity.class);
				startActivityForResult(intent, 21);
				break;
			case R.id.tv_fenxiaocode:
				if (tv_fenxiaocode.getText().equals("获取分销码")){
					Map<String,String> map=new HashMap<>();
					map.put("userid", String.valueOf(getMemberSharedPreference().getUserid()));
					new GetReferralCodeTask(context,map).execute();
				}else {
					ClipData myClip;
					myClip = ClipData.newPlainText("referralCode", getMemberSharedPreference().getReferralCode());
					myClipboard.setPrimaryClip(myClip);
					new AlertDialog.Builder(DistributionHostActivity.this)
							.setTitle("已复制分销码到剪切板")
							.setPositiveButton("确定", null)
							.create().show();
				}
				break;
			case R.id.tv_erweima:
				String input=getMemberSharedPreference().getReferralCode().trim();
				//生成二维码，然后为二维码增加logo
				Bitmap bitmap= EncodingUtils.createQRCode(input, 500, 500,
						BitmapFactory.decodeResource(getResources(),
								R.mipmap.icon)
				);
				View view= LayoutInflater.from(context).inflate(R.layout.erweima,null);
				ImageView img= (ImageView) view.findViewById(R.id.erweima_img);
				img.setImageBitmap(bitmap);
				new AlertDialog.Builder(DistributionHostActivity.this)
						.setView(view)
						.create().show();
				break;
		}
	}
	class GetReferralCodeTask extends AsyncTask<Void, Void, String> {
		private Context context;
		private boolean judgeInternet;
		private boolean typeFlag=true;
		private Map<String, String> map;
		public GetReferralCodeTask(Context context,Map<String, String> map){
			super();
			this.map=map;
			this.context=context;
		}

		@Override
		protected String doInBackground(Void... params) {
			String result = null;
			judgeInternet = NetWork.checkNetWorkStatus(context);
			try {
				if (judgeInternet) {
					String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETREFERRALCODEBYUSERID;
					StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
					if (jsonStr.toString().equals("[]")) {
						typeFlag = false;
					}
					result = JsonParserFactory.getReferralCodeByUserId(jsonStr.toString());

				}
			} catch (Exception e) {
				result = null;
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (judgeInternet) {
				if (typeFlag) {// 返回有数据
					if (result != null) {
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putString("referralCode",result);
						editor.commit();
						new AlertDialog.Builder(DistributionHostActivity.this)
								.setTitle("已获取到分销码为:"+result)
								.setPositiveButton("确定", null)
								.create().show();
						tv_fenxiaocode.setText("我的分销码:"+result);
					}
				}
			}
		}
	}

	class MemberUpdateTask extends AsyncTask<Void, Void, Member> {
		private Context context;
		private boolean judgeInternet;
		private boolean typeFlag=true;
		private Map<String, String> map;
		public MemberUpdateTask(Context context,Map<String, String> map){
			super();
			this.map=map;
			this.context=context;
		}

		@Override
		protected aizulove.com.fxxt.modle.entity.Member doInBackground(Void... params) {
			aizulove.com.fxxt.modle.entity.Member result = null;
			judgeInternet = NetWork.checkNetWorkStatus(context);
			try {
				if (judgeInternet) {
					String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETMEMBERBYID;
					System.out.println("url=="+url);
					StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
					if (jsonStr.toString().equals("[]")) {
						typeFlag = false;
					}
					result = JsonParserFactory.jsonParserMember(jsonStr.toString());
					}
			} catch (Exception e) {
				result = null;
			}
			return result;
		}

		@Override
		protected void onPostExecute(aizulove.com.fxxt.modle.entity.Member result) {
			super.onPostExecute(result);
			if (judgeInternet) {
				if (typeFlag) {// 返回有数据
					if (result != null) {
						setMemberSharedPreference(result);
						jiner.setText(String.valueOf(getMemberSharedPreference().getMoney()));
					}
				}
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode,Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			Map<String,String>map=new HashMap<String,String>();
			map.put("userId",getMemberSharedPreference().getUserid()+"");
			switch (requestCode) {
				case 1:
					new  MemberUpdateTask(context,map).execute();
					refresh();
				break;
				case  2:
					new  MemberUpdateTask(context,map).execute();
					refresh();
				break;
			}
		}
		if (resultCode==RESULT_OK){
			Bundle bundle=data.getExtras();
			String result= bundle.getString("result");
			ToastSingle.showToast(context, result);
		} if(resultCode == RESULT_CANCELED) {
			ToastSingle.showToast(context,"扫描出错！");
		}
	}
	private void refresh() {
		finish();
		Intent intent = new Intent(DistributionHostActivity.this, DistributionHostActivity.class);
		startActivity(intent);
	}
}
