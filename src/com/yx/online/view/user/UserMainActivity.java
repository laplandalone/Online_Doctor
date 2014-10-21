package com.yx.online.view.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

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

	@ViewInject(R.id.photo)
	private ImageView photo;
	
	@ViewInject(R.id.imgViewPager)
	ImgViewPager myPager; // 图片容器

	@ViewInject(R.id.vb)
	LinearLayout ovalLayout; // 圆点容器

	private List<View> listViews; // 图片组
	
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
		
		initViewPager();
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.pager_select, R.drawable.pager_item);
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
//			bitmapUtils.configDefaultLoadingImage(R.drawable.default_loading_img);
//			bitmapUtils.configDefaultLoadFailedImage(R.drawable.load_failure);
			imageView.setBackgroundResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
			
		}
	}
	@OnClick(R.id.health_data_lay)
	public void toMyHealth(View v)
	{
//		Intent intent = new Intent(UserMainActivity.this, MyHealthActivity.class);
//		startActivity(intent);
	}

	@OnClick(R.id.item_layout1)
	public void toMyOrder(View v)
	{
		Intent intent = new Intent(UserMainActivity.this, ExpertListActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.item_layout2)
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

	@OnClick(R.id.back)
	public void toHome(View v)
	{
//		Intent intent = new Intent(UserMainActivity.this, MainPageActivity.class);
//		startActivity(intent);
//		exit();
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
		loginNameTV.setText("欢迎您,"+user.getName()+"医生");
		register_num.setText(user.getRegister_num());
		ques_num.setText(user.getQues_num());
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
