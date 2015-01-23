package com.yx.online.view.user;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
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
import com.yx.online.adapter.ImgViewPager;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.other.CheckNewVersion;

public class LoginActivity extends BaseActivity
{
	@ViewInject(R.id.back)
	private ImageView back;
	
	@ViewInject(R.id.title)
	private TextView title;
	
	@ViewInject(R.id.password_find)
	private TextView pswFind;
	
	@ViewInject(R.id.sign_in)
	private ImageButton loginBtn;
	@ViewInject(R.id.rember_psw)
	private ImageButton remberPsw;
	@ViewInject(R.id.login_auto)
	private ImageButton loginAuto;

	@ViewInject(R.id.userName)
	private EditText userName;

	@ViewInject(R.id.password)
	private EditText password;

	private Boolean closeFlag = false;
	private User user;
	private boolean remberPswFlag = false;
	private boolean loginAutoFlag = false;

	@ViewInject(R.id.imgViewPager)
	ImgViewPager myPager; // 图片容器

	@ViewInject(R.id.vb)
	LinearLayout ovalLayout; // 圆点容器
	
	private List<View> listViews; // 图片组
	private BitmapUtils bitmapUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_sign_in);
		bitmapUtils = new BitmapUtils(this);
		bitmapUtils.closeCache();
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
		bindPush();
		Intent intent = new Intent(this, CheckNewVersion.class);
		intent.putExtra("flag", "auto");
		startService(intent);
	}

	/**
	 * bind push server 
	 * @param 
	 * @return void
	 * author xuchun
	 */
    private void bindPush() {
    	if (!HealthUtil.readBindPush()) {
    		HealthUtil.LOG_D(getClass(), "bind push server ing...");
    		PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, HealthConstant.PUSH_KEY);
    	}
    }
	
	/**
	 * 初始化图片
	 */
	private void initViewPager()
	{
		
		listViews = new ArrayList<View>();
		
		int[] imageResId = new int[]
		{ R.drawable.login_a, R.drawable.login_b, R.drawable.login_c};
		for (int i = 0; i < imageResId.length; i++)
		{
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}
	
	
	@OnClick(R.id.rember_psw)
	public void remberPsw(View v)
	{
		if (remberPswFlag)
		{
			HealthUtil.writeUserPassword("");
			remberPsw.setBackgroundResource(R.drawable.symptom_select_false);
			remberPswFlag = false;
		} else
		{
			HealthUtil.writeUserName(userName.getText().toString().trim());
			HealthUtil.writeUserPassword(password.getText().toString().trim());
			remberPsw.setBackgroundResource(R.drawable.symptom_select_true);
			remberPswFlag = true;
		}

	}
	
	@OnClick(R.id.password_find)
	public void pswFind(View v)
	{
		String telephone=userName.getText().toString().trim();
		if (!HealthUtil.isMobileNum(telephone))
		{
			HealthUtil.infoAlert(LoginActivity.this, "手机号码为空或格式错误!");
			return;
		}
		dialog.setMessage("密码重置中,请稍候...");
		dialog.show();
		RequestParams param = webInterface.getAuthCode(telephone,"set_psw");
		invokeWebServer(param, SET_PSW);
	}

	@OnClick(R.id.login_auto)
	public void loginAuto(View v)
	{
		if (loginAutoFlag)
		{
			HealthUtil.writeLoginAuto("");
			loginAuto.setBackgroundResource(R.drawable.symptom_select_false);
			loginAutoFlag = false;
		} else
		{
			HealthUtil.writeLoginAuto("auto");
			loginAuto.setBackgroundResource(R.drawable.symptom_select_true);
			loginAutoFlag = true;
		}
	}

	@OnClick(R.id.registration)
	public void userRegister(View v)
	{
//		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//		startActivity(intent);
	}

	@OnClick(R.id.back)
	public void toHome(View v)
	{
//		Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
//		startActivity(intent);
//		exit();
	}

	@Override
	protected void initView()
	{
		// TODO Auto-generated method stub
		title.setText("亚心在线");
		back.setVisibility(View.GONE);
		initViewPager();// 初始化图片
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.pager_select, R.drawable.pager_item);
	}

	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub
		if (!"".equals(HealthUtil.readUserName()) && !"".equals(HealthUtil.readUserPassword()))
		{
			this.userName.setText(HealthUtil.readUserName());
			this.password.setText(HealthUtil.readUserPassword());
			this.remberPsw.setBackgroundResource(R.drawable.symptom_select_true);
			this.remberPswFlag = true;
		}

		String login=HealthUtil.readLoginAuto();
		if("auto".equals(login))
		{
			this.loginAuto.setBackgroundResource(R.drawable.symptom_select_true);
			Login();
		}
	}

	@OnClick(R.id.sign_in)
	public void userLogin(View v)
	{
		Login();
	}

	public void Login()
	{
		String telephone = userName.getText().toString().trim();
		String passwordT = password.getText().toString().trim();
		if ("".equals(telephone))
		{
			HealthUtil.infoAlert(LoginActivity.this, "用户名为空");
			return;
		}
		
		if ("".equals(passwordT))
		{
			HealthUtil.infoAlert(LoginActivity.this, "密码为空");
			return;
		}
		dialog.setMessage("登录中,请稍后...");
		dialog.show();
		if (remberPswFlag)
		{
			HealthUtil.writeUserName(telephone);
			HealthUtil.writeUserPassword(passwordT);
		} else
		{
			HealthUtil.writeUserName("");
			HealthUtil.writeUserPassword("");
		}

		RequestParams param = webInterface.doctorLogin(telephone, passwordT);
		invokeWebServer(param, USER_LOGIN);
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
			httpHandler.cancel();
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

			HealthUtil.infoAlert(LoginActivity.this, "信息加载失败，请检查网络后重试");
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
			    	HealthUtil.infoAlert(LoginActivity.this, "用户名或密码错误,请重试");
			    	return;
			    }
			   
				this.user = HealthUtil.json2Object(returnMsg.toString(), User.class);
				if (this.user != null)
				{
					HealthUtil.writeUserInfo(returnMsg.toString());
					User user = HealthUtil.getUserInfo();
					HealthUtil.writeDoctorId(user.getDoctor_id());
					HealthUtil.infoAlert(LoginActivity.this, "登录成功");
				    Intent intent = new Intent(LoginActivity.this,UserMainActivity.class);
				    intent.putExtra("channel", "login");
				    startActivity(intent);
					break;
				}else
				{
					HealthUtil.infoAlert(LoginActivity.this, "用户名或密码错误,请重试");
			    	return;
				}
			    
			}
		} catch (Exception e)
		{
			HealthUtil.infoAlert(LoginActivity.this, "处理失败，请重试...");
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
