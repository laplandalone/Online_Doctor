package com.yx.online.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yx.online.doctor.R;
import com.yx.online.model.UserQuestionT;
import com.yx.online.model.WakeT;
import com.yx.online.tools.HealthConstant;
import com.yx.online.view.user.AskQuestionMsgActivity;

public class WakeAdapter extends BaseAdapter
{

	private Context mContext;

	private List<WakeT> wakeTs;
	public WakeAdapter(Context context, List<WakeT> wakeTs)
	{
		this.mContext = context;
		this.wakeTs = wakeTs;
	}

	@Override
	public int getCount()
	{
		if (wakeTs == null)
		{
			return 0;
		}
		return wakeTs.size();
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.wake_list_item, null);
			TextView textView = (TextView) convertView.findViewById(R.id.comtext1);
			WakeT wakeT = wakeTs.get(position);
			textView.setText(wakeT.getWakeContent());
			return convertView;
					
	}

}
