package com.yx.online.view.user;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lidroid.xutils.BitmapUtils;
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
import com.yx.online.model.UserQuestionT;
import com.yx.online.model.WakeT;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;

public class VisitTalkActivity extends BaseActivity
{
	@ViewInject(R.id.input_img)
	private  Button inputImg;
	
	@ViewInject(R.id.title)
	private TextView title;

	@ViewInject(R.id.content)
	private EditText contentET;

	@ViewInject(R.id.ask_again_text)
	private EditText askAgain;
	
	@ViewInject(R.id.back)
	private ImageView back;

	String userId;
	String doctorId;
	String userTelephone;
	String visitId="";
	User user;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visit_talk);
		// TODO Auto-generated method stub
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
	}
	
	@Override
	protected void initView()
	{
		title.setText("诊断");
		this.visitId=getIntent().getStringExtra("visitId");
		this.userId=getIntent().getStringExtra("userId");
		back.setBackgroundResource(R.drawable.back);
		inputImg.setVisibility(View.GONE);
		askAgain.setVisibility(View.VISIBLE);
	}

	

	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub
		this.doctorId = getIntent().getStringExtra("doctorId");
		this.user = HealthUtil.getUserInfo();
		
	}

	@OnClick(R.id.back)
	public void toHome(View v)
	{
		finish();
	}

	@OnClick(R.id.submit_question)
	public void submitQuestion(View v)
	{
		String content = askAgain.getText() + "";
		
		
		if("".equals(content))
		{
			HealthUtil.infoAlert(this, "回复内容为空");
			return;
		}

		if(content.length()>100)
		{
			HealthUtil.infoAlert(this, "回复内容过长,最多100字");
			return;
		}
		
		dialog.setMessage("正在提交,请稍后...");
		dialog.show();
		WakeT wakeT = new WakeT();
		wakeT.setWakeContent(content);
		wakeT.setUserId(userId);
		wakeT.setWakeValue(visitId);
		Gson gson = new Gson();
		String wakeTstr=gson.toJson(wakeT);
		RequestParams param = webInterface.addWakeT(wakeTstr);
		invokeWebServer(param, ADD_QUESTION);

		
	}

	private void showSuccessDialog()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).setPositiveButton("确定", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				finish();
			}
		}).setTitle("提示").setMessage("提交成功").create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
	}
	
	private void showFailureDialog(String msg)
	{

		AlertDialog alertDialog = new AlertDialog.Builder(this).setPositiveButton("确定", null).setTitle("失败提醒").setMessage(msg).create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
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

			HealthUtil.infoAlert(VisitTalkActivity.this, "信息加载失败，请检查网络后重试");
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
			case ADD_QUESTION:
				returnMsg(arg0.result, ADD_QUESTION);
				break;
			}
		}

		/*
		 * 处理返回结果数据
		 */
		private void returnMsg(String json, int code)
		{
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(json);

			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String executeType = jsonObject.get("executeType").getAsString();
			if ("success".equals(executeType))
			{
				HealthUtil.infoAlert(VisitTalkActivity.this, "处理成功.");
				finish();
			} else
			{
				HealthUtil.infoAlert(VisitTalkActivity.this, "处理失败请重试.");
			}

		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initView();
		initValue();
	}
}
