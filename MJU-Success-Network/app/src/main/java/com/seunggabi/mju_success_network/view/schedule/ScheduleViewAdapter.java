package com.seunggabi.mju_success_network.view.schedule;

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

public class ScheduleViewAdapter extends BaseAdapter {
    private Context context;
    private ScheduleViewHolder holder;
    public ArrayList<ScheduleData> dataList;


    public ScheduleViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<ScheduleData>();
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
            convertView = inflater.inflate(R.layout.schedule_item, null);

            holder = new ScheduleViewHolder();
            holder.s_name = (TextView) convertView.findViewById(R.id.s_name);
            holder.s_content = (TextView) convertView.findViewById(R.id.s_content);
            holder.s_datetime = (TextView) convertView.findViewById(R.id.s_datetime);
            convertView.setTag(holder);
        } else {
            holder = (ScheduleViewHolder) convertView.getTag();
        }

        ScheduleData data = dataList.get(position);
        holder.s_name.setText(data.getS_name());
        holder.s_content.setText(data.getS_content());
        holder.s_datetime.setText(Tool.getInstance().dateToString(data.getS_datetime()));

        return convertView;
    }

    public void addItem(ScheduleData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
