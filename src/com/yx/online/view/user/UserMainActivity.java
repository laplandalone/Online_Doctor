package com.yx.online.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.order.ExpertListActivity;

public class UserMainActivity extends BaseActivity
{
	@ViewInject(R.id.title)
	private TextView title;
	
	@ViewInject(R.id.item_layout1)
	private LinearLayout itemLayout1;

	@ViewInject(R.id.item_layout2)
	private LinearLayout itemLayout2;

	@ViewInject(R.id.userName)
	private TextView loginNameTV;

	@ViewInject(R.id.photo)
	private ImageView photo;
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
		title.setText("个人中心");
		// TODO Auto-generated method stub
		User user = HealthUtil.getUserInfo();
		loginNameTV.setText("欢迎,"+user.getName()+"医生");
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
