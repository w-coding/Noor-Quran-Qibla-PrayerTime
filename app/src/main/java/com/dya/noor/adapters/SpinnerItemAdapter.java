package com.dya.noor.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dya.noor.R;

public class SpinnerItemAdapter extends BaseAdapter {

    Context context;
    String [] nameList;
    String[] linkList;

    public SpinnerItemAdapter(Context context, String[] nameList, String[] linkList) {
        this.context = context;
        this.nameList = nameList;
        this.linkList = linkList;
    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.spinner_item,viewGroup,false);

        TextView Name = view.findViewById(R.id.name);
        TextView Link = view.findViewById(R.id.link);

        Name.setText(nameList[i]);
        Link.setText(linkList[i]);

        return view;
    }
}

