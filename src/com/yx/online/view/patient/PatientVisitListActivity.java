package com.yx.online.view.patient;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yx.online.adapter.PatientVisitListAdapter;
import com.yx.online.base.BaseActivity;
import com.yx.online.doctor.R;
import com.yx.online.model.PatientVisitT;
import com.yx.online.model.User;
import com.yx.online.tools.HealthConstant;
import com.yx.online.tools.HealthUtil;
import com.yx.online.view.user.UserMainActivity;

public class PatientVisitListActivity extends BaseActivity implements OnItemClickListener
{
	@ViewInject(R.id.contentnull)
	private RelativeLayout layout;
	
	@ViewInject(R.id.title)
	private TextView title;
	
	@ViewInject(R.id.ans_line)
	ImageView ans_line;
	
	@ViewInject(R.id.noans_line)
	ImageView noans_line;
	
	@ViewInject(R.id.text1)
	private TextView text1;
	
	@ViewInject(R.id.text2)
	private TextView text2;
	
	private ListView list;
	private List<PatientVisitT> patientVisitTs ;
	
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_visit_list);
		this.list = (ListView) findViewById(R.id.comlist);
		ViewUtils.inject(this);
		addActivity(this);
		initView();
		initValue();
	}

	@OnClick(R.id.back)
	public void toBack(View v)
	{
		Intent intent = new Intent(PatientVisitListActivity.this,UserMainActivity.class);
		startActivity(intent);
		exit();
	}
	
	@OnClick(R.id.submit)
	public void health1(View v)
	{

	}
	
	@OnClick(R.id.noans)
	public void questionAns(View v)
	{
		HealthUtil.visit_flag=false;
		text1.setTextColor(this.getResources().getColor(R.color.black));
		text2.setTextColor(this.getResources().getColor(R.color.TextColorGreen));
		ans_line.setVisibility(View.GONE);
		noans_line.setVisibility(View.VISIBLE);
		dialog.setMessage("正在加载,请稍后...");
		dialog.show();
		RequestParams param = webInterface.getPatientVisits("N");
		invokeWebServer(param, GET_LIST);
	}

	@OnClick(R.id.ans)
	public void questionNoAns(View v)
	{
		HealthUtil.visit_flag=true;
		text1.setTextColor(this.getResources().getColor(R.color.TextColorGreen));
		text2.setTextColor(this.getResources().getColor(R.color.black));
		ans_line.setVisibility(View.VISIBLE);
		noans_line.setVisibility(View.GONE);
		dialog.setMessage("正在加载,请稍后...");
		dialog.show();
		RequestParams param = webInterface.getPatientVisits("Y");
		invokeWebServer(param, GET_LIST);
	}

	@Override
	protected void initView()
	{
		// TODO Auto-generated method stub
		title.setText("患者随访");
		user=HealthUtil.getUserInfo();
	}
	
	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub
		
		text1.setTextColor(this.getResources().getColor(R.color.black));
		text2.setTextColor(this.getResources().getColor(R.color.TextColorGreen));
		ans_line.setVisibility(View.GONE);
		noans_line.setVisibility(View.VISIBLE);
		dialog.setMessage("正在加载,请稍后...");
		dialog.show();
		// TODO Auto-generated method stub
		String visitFlag="N";
		if(HealthUtil.visit_flag)
		{
			visitFlag="Y";
			text1.setTextColor(this.getResources().getColor(R.color.TextColorGreen));
			text2.setTextColor(this.getResources().getColor(R.color.black));
			ans_line.setVisibility(View.VISIBLE);
			noans_line.setVisibility(View.GONE);
		}else
		{
			text1.setTextColor(this.getResources().getColor(R.color.black));
			text2.setTextColor(this.getResources().getColor(R.color.TextColorGreen));
			ans_line.setVisibility(View.GONE);
			noans_line.setVisibility(View.VISIBLE);
		}
		RequestParams param = webInterface.getPatientVisits(visitFlag);
		invokeWebServer(param, GET_LIST);
	}

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
			if (list != null)
			{
				// list.stopLoadMore();
			}
			HealthUtil.infoAlert(PatientVisitListActivity.this, "信息加载失败，请检查网络后重试");
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
			case GET_LIST:
				returnMsg(arg0.result, GET_LIST);
				break;
			case GET_LIST_MORE:
				returnMsg(arg0.result, GET_LIST_MORE);
				break;
			}
		}

	}

	
	private void returnMsg(String json, int code)
	{
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(json);
		
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("returnMsg");
		Gson gson = new Gson();
		this.patientVisitTs = gson.fromJson(jsonArray, new TypeToken<List<PatientVisitT>>()
		{
		}.getType());
		PatientVisitListAdapter adapter = new PatientVisitListAdapter(PatientVisitListActivity.this, patientVisitTs);
		this.list.setAdapter(adapter);
		this.list.setOnItemClickListener(this);
		if(!HealthUtil.visit_flag)
		{
			user.setVisit_num(patientVisitTs.size()+"");
		}
		if( patientVisitTs.size()==0)
		{
			layout.setVisibility(View.VISIBLE);
			list.setVisibility(View.GONE);
		}else
		{
			layout.setVisibility(View.GONE);
			list.setVisibility(View.VISIBLE);
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent(PatientVisitListActivity.this,VisitDetailActivity.class);
		PatientVisitT patientVisitT = patientVisitTs.get(position);
		String visitId=patientVisitT.getVisitId();
		String copyFlag=patientVisitT.getCopyFlag();
		String patientId=patientVisitT.getPatientId()+"";
		String operType="无";
		String userName=patientVisitT.getVisitName();
		if(patientId!=null && patientId.length()>6)
		{
			patientId=patientId.substring(patientId.length()-6,patientId.length());
		}
		String url="http://123.57.78.38:10841/visit/visit.jsp?visitId="+visitId+"&copyFlag="+copyFlag+"&name="+userName+"&patientId="+patientId+"&operType="+operType;
//	 	String url="http://192.168.137.1:7001/visit/visit.jsp?visitId="+visitId+"&copyFlag="+copyFlag+"&name="+userName+"&patientId="+patientId+"&operType="+operType;
		intent.putExtra("url", url);
		intent.putExtra("visitId", visitId);
		intent.putExtra("patientId", patientVisitT.getPatientId());
		intent.putExtra("userId", patientVisitT.getUserId());
		intent.putExtra("title", patientVisitT.getName());
		startActivity(intent);
	}
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		initView();
		initValue();
	}
}
