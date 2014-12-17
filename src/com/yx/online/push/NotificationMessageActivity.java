package com.yx.online.push;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.view.user.UserMainActivity;

public class NotificationMessageActivity extends BaseActivity {
	private TextView titleTV;
	private TextView messageTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_message);
		ViewUtils.inject(this);
		addActivity(this);
		titleTV = (TextView) findViewById(R.id.title);
		messageTV = (TextView) findViewById(R.id.notification_message);
		
		String title = getIntent().getStringExtra("title");
		String message = getIntent().getStringExtra("message");
		
		titleTV.setText(title);
		messageTV.setText(message);
	}

	@OnClick(R.id.back)
	public void toHome(View v)
	{
		Intent intent = new Intent(NotificationMessageActivity.this, UserMainActivity.class);
		startActivity(intent);
		exit();
	}
	
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
		
	}
}
