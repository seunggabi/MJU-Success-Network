package com.seunggabi.mju_success_network.view.chatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

import java.util.ArrayList;

/**
 * Created by seunggabi on 2016-11-23.
 */

public class ChattingUserViewAdapter extends BaseAdapter {
    private Context context;
    private ChattingUserViewHolder holder;
    public ArrayList<ChattingUserData> dataList;


    public ChattingUserViewAdapter(Context context) {
        super();
        this.context = context;
        dataList = new ArrayList<ChattingUserData>();
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
            convertView = inflater.inflate(R.layout.chatting_user_item, null);

            holder = new ChattingUserViewHolder();
            holder.u_name = (TextView) convertView.findViewById(R.id.u_name);
            holder.ban = (TextView) convertView.findViewById(R.id.ban);
            holder.allow = (TextView) convertView.findViewById(R.id.allow);
            convertView.setTag(holder);
        } else {
            holder = (ChattingUserViewHolder) convertView.getTag();
        }

        ChattingUserData data = dataList.get(position);
        holder.u_name.setText(data.getU_name());
        if(data.getJ_status() == 'Y')
            holder.allow.setVisibility(View.INVISIBLE);
        else
            holder.ban.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public void addItem(ChattingUserData data) {
        dataList.add(data);
    }
    public void clear() {
        dataList.clear();
    }
}
