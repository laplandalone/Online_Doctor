package com.yx.online.view.patient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.User;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.user.UserMainActivity;
import com.yx.online.view.user.VisitTalkActivity;

public class VisitDetailActivity extends BaseActivity
{
	
	@ViewInject(R.id.title)
	private TextView title;
	WebView web;
	private Context mContext;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visit_detail_webview);
		web = (WebView) findViewById(R.id.webview);  
		web.getSettings().setJavaScriptEnabled(true);   
		web.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
		mContext=this;
	}

	@OnClick(R.id.back)
	public void toBack(View v)
	{
		Intent intent = new Intent(VisitDetailActivity.this, UserMainActivity.class);
		startActivity(intent);
		exit();
	}
	
	@JavascriptInterface
	public void visitTalk()
	{
		Intent intent = new Intent(VisitDetailActivity.this,VisitTalkActivity.class);
		String visitId=getIntent().getStringExtra("visitId");
		String userId=getIntent().getStringExtra("userId");
		intent.putExtra("visitId",visitId);
		intent.putExtra("userId",userId);
		startActivity(intent);
		finish();
	}
	
	@JavascriptInterface
	public void showImg(String imgUrl)
	{
		Uri mUri = Uri.parse("http://www.hiseemedical.com:10821/"+imgUrl);
		Intent it = new Intent(Intent.ACTION_VIEW);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.setDataAndType(mUri, "image/*");
		mContext.startActivity(it);
	}
	
	@JavascriptInterface
	public void alert(String msg)
	{
		HealthUtil.infoAlert(VisitDetailActivity.this,msg);
	}
	
	@JavascriptInterface
	@Override
	protected void initView()
	{
		String titleT = getIntent().getStringExtra("title");
		// TODO Auto-generated method stub
		title.setText(titleT);
		String url = getIntent().getStringExtra("url");
	
		 if(web != null) 
	        { 
			    web.addJavascriptInterface(this, "javatojs");

	            web.setWebViewClient(new WebViewClient() 
	            { 
	            	@Override
	                public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	                    view.loadUrl(url); 
	                    return true; 
	                } 
	            	
	            	@Override
	            	public void onPageStarted(WebView view, String url,
	            			Bitmap favicon) {
//	            		dialog.setMessage("正在加载,请稍后...");
//	      	    		dialog.show();
	            		super.onPageStarted(view, url, favicon);
	            	}
	                @Override 
	                public void onPageFinished(WebView view,String url) 
	                { 
//	                	 try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}   
//	                    dialog.dismiss(); 
	                }

					@Override
					public void onReceivedError(WebView view, int errorCode,
							String description, String failingUrl) 
					{
						HealthUtil.infoAlert(VisitDetailActivity.this, "加载失败,请重试...");
						web.setVisibility(View.GONE);
						super.onReceivedError(view, errorCode, description, failingUrl);
					} 
	                
	            }); 
	             
	            loadUrl(url); 
	        } 
	}

	  public void loadUrl(String url) 
	    { 
	        if(web != null) 
	        { 
	            web.loadUrl(url); 
	          
	        } 
	    } 

	@Override
	protected void initValue()
	{
		
	}

	public void addVisit(String josn,String visitType)
	{
		dialog.setMessage("正在加载,请稍后...");
  		dialog.show();
		User user = HealthUtil.getUserInfo();
//		RequestParams param = webInterface.addVisit(josn, user.getUserId(), visitType);
//		invokeWebServer(param, ADD_VISIT);
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

			HealthUtil.infoAlert(VisitDetailActivity.this, "信息加载失败，请检查网络后重试");
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
				HealthUtil.infoAlert(VisitDetailActivity.this, "添加随访成功.");
				finish();
			} else
			{
				HealthUtil.infoAlert(VisitDetailActivity.this, "添加随访失败.");
			}

		}
	}
}
