package com.yx.online.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yx.online.doctor.R;
import com.yx.online.model.PatientVisitT;
import com.yx.online.view.user.VisitTalkActivity;

public class PatientVisitListAdapter  extends BaseAdapter
{
	private Context mContext;

	private List<PatientVisitT> patientVisitTs;

	private BitmapUtils bitmapUtils;
 
	

	public List<PatientVisitT> getPatientVisitTs() {
		return patientVisitTs;
	}

	public void setPatientVisitTs(List<PatientVisitT> patientVisitTs) {
		this.patientVisitTs = patientVisitTs;
	}

	public PatientVisitListAdapter(Context context, List<PatientVisitT> patientVisitTs)
	{
		this.mContext=context;
		this.patientVisitTs = patientVisitTs;
	}

	@Override
	public int getCount()
	{
		if (patientVisitTs == null)
		{
			return 0;
		}
		return patientVisitTs.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub  my_online_select btn_my_online
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		 convertView = LayoutInflater.from(mContext).inflate(R.layout.patient_visit_item, null);
		 TextView textView =  (TextView)convertView.findViewById( R.id.text1);
		 TextView hander = (TextView)convertView.findViewById( R.id.hander);
		 TextView textView3 = (TextView)convertView.findViewById( R.id.text3);
		 TextView textView4 = (TextView)convertView.findViewById( R.id.text4);
		
		 PatientVisitT patientVisitT = patientVisitTs.get(position);
		 String visitType = patientVisitT.getVisitType();
		 String  copyFlag=patientVisitT.getCopyFlag();
		 textView.setText(patientVisitT.getVisitName());
		 textView3.setText(patientVisitT.getName());
		 textView4.setText(patientVisitT.getVisitId());
		 hander.setText(patientVisitT.getCreateDate());
		/*
		 if("Y".equals(copyFlag))
		 {
			 hander.setText("已诊断");
		 }else
		 {
			 hander.setTag(patientVisitT);
			 hander.setOnClickListener(new OnClickListener()
			 {
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent it = new Intent(mContext,VisitTalkActivity.class);
					PatientVisitT pVT =(PatientVisitT) v.getTag();
					it.putExtra("visitId",pVT.getVisitId());
					it.putExtra("userId",pVT.getPatientId());
					mContext.startActivity(it);
				}
			});
		 }*/
		return convertView;
	}

}
