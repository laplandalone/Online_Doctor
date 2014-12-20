package com.yx.online.view.user;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.adapter.ImgViewPager;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.order.ExpertListActivity;
import com.yx.online.view.patient.MyPatientActivity;
import com.yx.online.view.user.LoginActivity.MineRequestCallBack;

public class UserMainActivity extends BaseActivity
{
	@ViewInject(R.id.title)
	private TextView title;
	
	@ViewInject(R.id.editUser)
	private TextView editUser;
	
	@ViewInject(R.id.register_num)
	private TextView register_num;
	
	@ViewInject(R.id.ques_num)
	private TextView ques_num;
	
	@ViewInject(R.id.item_layout1)
	private LinearLayout itemLayout1;

	@ViewInject(R.id.item_layout2)
	private LinearLayout itemLayout2;

	@ViewInject(R.id.userName)
	private TextView loginNameTV;

	@ViewInject(R.id.back)
	private ImageView back;
	
	
	
	@ViewInject(R.id.imgViewPager)
	ImgViewPager myPager; // 图片容器

	@ViewInject(R.id.vb)
	LinearLayout ovalLayout; // 圆点容器

	private List<View> listViews; // 图片组
	
	
	@ViewInject(R.id.lineout1)
	private LinearLayout layout1;

	@ViewInject(R.id.lineout2)
	private LinearLayout layout2;

	@ViewInject(R.id.lineout3)
	private LinearLayout layout3;

	@ViewInject(R.id.lineout4)
	private LinearLayout layout4;

	int  imgPagerHeigth=0;
	int wwSpace=13;
	int space720 =30;
	int space480 =30;
	int space=0;
	int imgSpace=0;
	
	private User user;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub 2131493633
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_main);
		ViewUtils.inject(this);
		addActivity(this);
		initValue();
		initView();
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int screenWidth = display.getWidth();
		int scrrenHeight = display.getHeight();
		Log.e("screenWidth",screenWidth+"");
		Log.e("scrrenHeight",scrrenHeight+"");
		
		if(screenWidth==480)
		{
			imgSpace=dip2px(this, space480);
			space=dip2px(this, space480+wwSpace);
			imgPagerHeigth=dip2px(this, 40);//40：title高度+间隔高度
			
		}else
		{
			imgSpace=dip2px(this, space720);
			space=dip2px(this, space720+wwSpace);
			imgPagerHeigth=dip2px(this, 30);//30:title高度+间隔高度
		}
		int imgWhith=(screenWidth-space)/2;
		int imgHeight=imgWhith*200/310;
		
		LinearLayout.LayoutParams layoutLayoutParam = new LinearLayout.LayoutParams(imgWhith, imgHeight);
		
		LinearLayout.LayoutParams layoutLayoutParam1 = new LinearLayout.LayoutParams(imgWhith, imgHeight);
		
		LinearLayout.LayoutParams layoutLayoutParam3 = new LinearLayout.LayoutParams(imgWhith, imgHeight);
		
		LinearLayout.LayoutParams layoutLayoutParam4 = new LinearLayout.LayoutParams(imgWhith, imgHeight);
		
		layoutLayoutParam1.setMargins(imgSpace/2,0,0, 0);//设置边距
		layoutLayoutParam3.setMargins(0,imgSpace/2,0, 0);//设置边距
		layoutLayoutParam4.setMargins(imgSpace/2,imgSpace/2,0, 0);//设置边距
		
		layout1.setLayoutParams(layoutLayoutParam);
		layout2.setLayoutParams(layoutLayoutParam1);
		layout3.setLayoutParams(layoutLayoutParam3);
		layout4.setLayoutParams(layoutLayoutParam4);
		
		initViewPager();
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.pager_select, R.drawable.pager_item);
	}

	public static int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	
	/**
	 * 初始化图片
	 */
	private void initViewPager()
	{
		listViews = new ArrayList<View>();
		int[] imageResId = new int[]
		{ R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
		String hospitalId=HealthUtil.readHospitalId();
		for(int i = 0; i < imageResId.length; i++)
		{
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
			
		}
	}
	
	@OnClick(R.id.lineout1)
	public void toMyOrder(View v)
	{
		Intent intent = new Intent(UserMainActivity.this, ExpertListActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.lineout2)
	public void toMyQuestion(View v)
	{
		Intent intent = new Intent(UserMainActivity.this, QuestionActivity.class);
		intent.putExtra("questionType", "user");
		startActivity(intent);
	}
	
	@OnClick(R.id.lineout3)
	public void toMyWork(View v)
	{
		HealthUtil.infoAlert(UserMainActivity.this, "正在建设中...");
	}
	
	@OnClick(R.id.lineout4)
	public void toMyPatient(View v)
	{
		Intent intent = new Intent(UserMainActivity.this, MyPatientActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.editUser)
	public void updateUser(View v)
	{
		Intent intent = new Intent(UserMainActivity.this, UserUpdateActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.outLogin)
	public void loginOut(View v)
	{
		HealthUtil.writeUserInfo("");
		HealthUtil.writeLoginAuto("");
		HealthUtil.writeHospitalTs("");
		Intent intent = new Intent(UserMainActivity.this, LoginActivity.class);
		startActivity(intent);
		exit();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		switch (resultCode)
		{
		case 0:
			User user = HealthUtil.getUserInfo();
			if (user != null && !"".equals(user))
			{
				loginNameTV.setText(user.getName());
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void initView()
	{
		title.setText("亚心在线");
		editUser.setVisibility(View.VISIBLE);
		// TODO Auto-generated method stub
		User user = HealthUtil.getUserInfo();
		loginNameTV.setText("欢迎您,"+user.getName()+"医生!");
		String num1=user.getRegister_num();
		String num2=user.getQues_num();
		if(num1==null || "".equals(num1))
		{
			num1="0";
		}
		if(num2==null || "".equals(num2))
		{
			num2="0";
		}
		register_num.setText(num1);
		ques_num.setText(num2);
		back.setVisibility(View.GONE);
 
	}

	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub
		User user = HealthUtil.getUserInfo();
		String channel=getIntent().getStringExtra("channel");
		if(!"login".equals(channel))
		{
			RequestParams param = webInterface.doctorLogin(user.getName(), user.getPassword());
			invokeWebServer(param, USER_LOGIN);
		}
	}

	/**
	 * 链接web服务
	 * 
	 * @param param
	 */
	private void invokeWebServer(RequestParams param, int responseCode)
	{
		HealthUtil.LOG_D(getClass(), "connect to web server");
		MineRequestCallBack requestCallBack = new MineRequestCallBack(responseCode);
		if (httpHandler != null)
		{
			httpHandler.stop();
		}
		httpHandler = mHttpUtils.send(HttpMethod.POST, HealthConstant.URL, param, requestCallBack);
	}

	/**
	 * 获取后台返回的数据
	 */
	class MineRequestCallBack extends RequestCallBack<String>
	{

		private int responseCode;

		public MineRequestCallBack(int responseCode)
		{
			super();
			this.responseCode = responseCode;
		}

		@Override
		public void onFailure(HttpException error, String msg)
		{
			HealthUtil.LOG_D(getClass(), "onFailure-->msg=" + msg);
			if (dialog.isShowing())
			{
				dialog.cancel();
			}

			HealthUtil.infoAlert(UserMainActivity.this, "信息加载失败，请检查网络后重试");
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0)
		{
			// TODO Auto-generated method stub
			HealthUtil.LOG_D(getClass(), "result=" + arg0.result);
			if (dialog.isShowing())
			{
				dialog.cancel();
			}
			switch (responseCode)
			{
			case USER_LOGIN:
				returnMsg(arg0.result, USER_LOGIN);
				break;
			case SET_PSW:
				returnMsg(arg0.result, SET_PSW);
				break;
			}
		}

	}

	/*
	 * 处理返回结果数据
	 */
	private void returnMsg(String json, int responseCode)
	{
		try
		{
 			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(json);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			
			switch (responseCode)
			{
			    case USER_LOGIN:
			    String executeType = jsonObject.get("executeType").getAsString();
			    String returnMsg = jsonObject.get("returnMsg").toString();
			    if("success".equals(executeType) && "null".equals(returnMsg))
			    {
			    	HealthUtil.infoAlert(UserMainActivity.this, "用户名或密码错误,请重试");
			    	return;
			    }
			   
				this.user = HealthUtil.json2Object(returnMsg.toString(), User.class);
				if (this.user != null)
				{
					HealthUtil.writeUserInfo(returnMsg.toString());
					User user = HealthUtil.getUserInfo();
					HealthUtil.writeDoctorId(user.getDoctor_id());
					String num1=user.getRegister_num();
					String num2=user.getQues_num();
					if(num1==null || "".equals(num1))
					{
						num1="0";
					}
					if(num2==null || "".equals(num2))
					{
						num2="0";
					}
					register_num.setText(num1);
					ques_num.setText(num2);
					break;
				} 
			    
			}
		} catch (Exception e)
		{
			 
		}

	}

	private void showSuccessDialog()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setPositiveButton("确定", new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub

					}
				}).setTitle("提示").setMessage("密码已重置请查收").create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
	}
}
