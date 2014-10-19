package com.yx.online.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.online.doctor.R;
import com.yx.online.model.OrderExpert;
import com.yx.online.model.OrderExpertList;

public class OrderExpertAdapter  extends BaseAdapter
{
	private Context mContext;

	private List<OrderExpert> orders;

	private String day="";
	public OrderExpertAdapter(Context context, OrderExpertList expertList)
	{
		this.mContext=context;
		this.orders = expertList.getOrders();
	}

	@Override
	public int getCount()
	{
		if (orders == null)
		{
			return 0;
		}
		return orders.size();
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
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		 View view = convertView;
		 ViewHolder viewHolder = null;

		 viewHolder = new ViewHolder();
		 view = LayoutInflater.from(mContext).inflate(R.layout.user_order_list_item, null);		
		 viewHolder.textView = (TextView)view.findViewById( R.id.text1);
		 viewHolder.dateView = (TextView)view.findViewById( R.id.text2);
		 viewHolder.weekView = (TextView)view.findViewById( R.id.text4);
		 viewHolder.teamView = (TextView)view.findViewById( R.id.text3);
		 OrderExpert  expert=orders.get(position);
		 String value=expert.getDoctorName();
		 String weekDay=expert.getDay();
		 String week=expert.getWeek();
		 String teamName=expert.getTeamName();
		 viewHolder.teamView.setText(teamName);
		 viewHolder.textView.setText(weekDay);
	
		 viewHolder.dateView.setText("星期"+week);
		
		return view;
	}
	

	private class ViewHolder
	{
		public TextView textView;
		public TextView dateView;
		public TextView weekView;
		public TextView teamView;
	}
}
