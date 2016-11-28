package com.seunggabi.mju_success_network.view.chatting;

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

public class ChattingViewAdapter extends BaseAdapter {
    private Context context;
    private ChattingViewHolder holder;
    public ArrayList<ChattingData> dataList;


    public ChattingViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<ChattingData>();
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
            convertView = inflater.inflate(R.layout.chatting_item, null);

            holder = new ChattingViewHolder();
            holder.u_name = (TextView) convertView.findViewById(R.id.u_name);
            holder.l_time = (TextView) convertView.findViewById(R.id.l_time);
            holder.l_content = (TextView) convertView.findViewById(R.id.l_content);
            convertView.setTag(holder);
        } else {
            holder = (ChattingViewHolder) convertView.getTag();
        }

        ChattingData data = dataList.get(position);
        holder.u_name.setText(data.getU_name());
        holder.l_time.setText(Tool.getInstance().getDateString(data.getL_time()));
        holder.l_content.setText(data.getL_content());

        return convertView;
    }

    public void addItem(ChattingData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
