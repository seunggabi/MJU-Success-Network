package com.seunggabi.mju_success_network.view.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.seunggabi.mju_success_network.R;

import java.util.ArrayList;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class GroupViewAdapter extends BaseAdapter {
    private Context context;
    private GroupViewHolder holder;
    public ArrayList<GroupData> dataList;

    public GroupViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<GroupData>();
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
            convertView = inflater.inflate(R.layout.group_list_item, null);

            holder = new GroupViewHolder();
            holder.u_name = (TextView) convertView.findViewById(R.id.u_name);
            holder.g_name = (TextView) convertView.findViewById(R.id.g_nameText);
            holder.g_intro = (TextView) convertView.findViewById(R.id.g_intro);
            holder.g_tag = (TextView) convertView.findViewById(R.id.g_tag);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        GroupData data = dataList.get(position);
        holder.u_name.setText(data.getU_name());
        holder.g_name.setText(data.getG_name());
        holder.g_intro.setText(data.getG_intro());
        holder.g_tag.setText(data.getG_tag());

        return convertView;
    }

    public void addItem(GroupData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
