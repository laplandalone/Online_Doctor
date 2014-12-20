package com.yx.online.view.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.view.user.QuestionActivity;
import com.yx.online.view.user.UserMainActivity;

public class MyPatientActivity extends BaseActivity
{
	@ViewInject(R.id.title)
	private TextView title;
	
	@ViewInject(R.id.item_layout1)
	private LinearLayout itemLayout1;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub 2131493633
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_main);
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
	}

	@OnClick(R.id.item_layout1)
	public void toMyQuestion(View v)
	{
		Intent intent = new Intent(MyPatientActivity.this, PatientVisitListActivity.class);
		startActivity(intent);
	}


	@OnClick(R.id.back)
	public void toHome(View v)
	{
		Intent intent = new Intent(MyPatientActivity.this, UserMainActivity.class);
		startActivity(intent);
		exit();
	}


	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub

	}
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		title.setText("病人管理");
	}
}
