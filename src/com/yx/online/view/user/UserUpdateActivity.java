package com.yx.online.view.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
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
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;
import com.yx.online.tools.IDCard;
import com.yx.online.tools.StringUtil;

public class UserUpdateActivity extends BaseActivity
{
	@ViewInject(R.id.title)
	private TextView title;

	@ViewInject(R.id.telephone)
	private TextView telephone;
	
	@ViewInject(R.id.real_name)
	private EditText realNameET;

	@ViewInject(R.id.tel)
	private EditText telephoneET;

	@ViewInject(R.id.idcard)
	private EditText idCardET;

	@ViewInject(R.id.check_btn)
	private RadioGroup group;

	@ViewInject(R.id.male)
	private RadioButton maleRadio;

	@ViewInject(R.id.female)
	private RadioButton femaleRadio;
	

	
	private User user;

	private User userT = new User();
	
	private String sex;

	private String updateUserStr;
	
	private boolean noticeFlag=true;
	
	private String userNameChange="";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_people_info);
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
	}

	@Override
	protected void initView()
	{
		title.setText("设置");
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}

	@OnClick(R.id.my_password)
	public void toPassword(View v)
	{
		Intent intent = new Intent(UserUpdateActivity.this, UserPasswordActivity.class);
		startActivity(intent);
	}
	
	@OnClick(R.id.back)
	public void toHome(View v)
	{
		Intent intent = new Intent(UserUpdateActivity.this,UserMainActivity.class);
		startActivity(intent);
		exit();
	}
	
	
	@Override
	protected void initValue()
	{

	}

	 
}