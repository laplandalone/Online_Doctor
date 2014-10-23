package com.yx.online.view.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.adapter.ImgViewPager;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.order.ExpertListActivity;

public class UserMainActivity extends BaseActivity
{
	@ViewInject(R.id.title)
	private TextView title;
	
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
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub 2131493633
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_main);
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
		
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

	@OnClick(R.id.user_info_detail)
	public void updateUser(View v)
	{
//		Intent intent = new Intent(UserMainActivity.this, UserUpdateActivity.class);
//		startActivityForResult(intent, 0);
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
//		if("男".equals(user.getSex()))
//		{
//			photo.setBackgroundResource(R.drawable.male);
//		}else
//		{
//			photo.setBackgroundResource(R.drawable.female);
//		}
	}

	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub

	}

}
