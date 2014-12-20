package com.yx.online.view.patient;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	private String userId="";
	private User user;
	private ListView list;
	private List<PatientVisitT> patientVisitTs ;
	
	PatientVisitListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_visit_list);
		list = (ListView) findViewById(R.id.comlist);
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
	
	@Override
	protected void initView()
	{
		// TODO Auto-generated method stub
		title.setText("患者随访");
	}
	
	@Override
	protected void initValue()
	{
		// TODO Auto-generated method stub
		

		dialog.setMessage("正在加载,请稍后...");
		dialog.show();
		// TODO Auto-generated method stub
		String hospitalId=HealthUtil.readHospitalId();
		RequestParams param = webInterface.getPatientVisits();
		invokeWebServer(param, GET_LIST);
	}

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
		adapter = new PatientVisitListAdapter(PatientVisitListActivity.this, patientVisitTs);
		this.list.setAdapter(adapter);
		this.list.setOnItemClickListener(this);
	
		if( patientVisitTs.size()==0)
		{
			layout.setVisibility(View.VISIBLE);
			list.setVisibility(View.GONE);
		}
		this.list.setAdapter(adapter);
		this.list.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// TODO Auto-generated method stub
//		Intent intent = new Intent(ExpertListActivity.this,ExpertDetailActivity.class);
//		OrderExpert expert =expertList.getOrders().get(position-1);
//		Bundle bundle = new Bundle();
//		bundle.putSerializable("expert", expert);
//		intent.putExtras(bundle);
//		startActivity(intent);
	}
}
