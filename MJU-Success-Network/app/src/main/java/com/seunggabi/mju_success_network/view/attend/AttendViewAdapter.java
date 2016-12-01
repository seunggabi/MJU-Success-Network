package com.seunggabi.mju_success_network.view.attend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

import java.util.ArrayList;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class AttendViewAdapter extends BaseAdapter {
    private Context context;
    private AttendViewHolder holder;
    public ArrayList<AttendData> dataList;


    public AttendViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<AttendData>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.attend_item, null);

            holder = new AttendViewHolder();
            holder.u_name = (TextView) convertView.findViewById(R.id.u_name);
            holder.a_time = (TextView) convertView.findViewById(R.id.a_time);
            convertView.setTag(holder);
        } else {
            holder = (AttendViewHolder) convertView.getTag();
        }

        AttendData data = dataList.get(position);
        holder.u_name.setText(data.getU_name());
        holder.a_time.setText(Tool.getInstance().dateToString(data.getA_time()));

        return convertView;
    }

    public void addItem(AttendData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
