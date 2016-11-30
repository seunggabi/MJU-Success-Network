package com.seunggabi.mju_success_network.view.notice;

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

public class NoticeViewAdapter extends BaseAdapter {
    private Context context;
    private NoticeViewHolder holder;
    public ArrayList<NoticeData> dataList;


    public NoticeViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<NoticeData>();
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
            convertView = inflater.inflate(R.layout.notice_item, null);

            holder = new NoticeViewHolder();
            holder.m_name = (TextView) convertView.findViewById(R.id.m_name);
            holder.n_time = (TextView) convertView.findViewById(R.id.n_time);
            holder.n_content = (TextView) convertView.findViewById(R.id.n_content);
            convertView.setTag(holder);
        } else {
            holder = (NoticeViewHolder) convertView.getTag();
        }

        NoticeData data = dataList.get(position);
        holder.m_name.setText(data.getM_name());
        holder.n_time.setText(Tool.getInstance().dateToString(data.getN_time()));
        holder.n_content.setText(data.getN_content());

        return convertView;
    }

    public void addItem(NoticeData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
