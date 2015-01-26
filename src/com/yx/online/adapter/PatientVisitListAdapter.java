package com.yx.online.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yx.online.doctor.R;
import com.yx.online.model.PatientVisitT;

public class PatientVisitListAdapter  extends BaseAdapter
{
	private Context mContext;

	private List<PatientVisitT> patientVisitTs;

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
		 textView.setText(patientVisitT.getVisitName());
		 textView3.setText(patientVisitT.getName());
		 textView4.setText(patientVisitT.getPatientId());
		 hander.setText(patientVisitT.getCreateDate());
	 
		return convertView;
	}

}
