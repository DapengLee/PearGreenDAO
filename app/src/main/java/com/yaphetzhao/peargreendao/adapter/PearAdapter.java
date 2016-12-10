package com.yaphetzhao.peargreendao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.util.DensityUtil;

import java.util.List;

/**
 * Created by YaphetZhao
 * on 2016/12/10.
 */

public class PearAdapter extends BaseAdapter {

    private List list;
    private Context context;

    public PearAdapter(Context context) {
        this.context = context;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null == list ? 0 : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return null == list ? 0 : i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == list || list.size() == 0) {
            return null;
        }

        PearAdapterHolder holder;
        if (null == view) {
            LinearLayout layout = new LinearLayout(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(lp);
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView tv_id = new TextView(context);
            tv_id.setPadding(
                    DensityUtil.dip2px(context, 10),
                    DensityUtil.dip2px(context, 2),
                    DensityUtil.dip2px(context, 10),
                    DensityUtil.dip2px(context, 2));
            TextView tv_key = new TextView(context);
            tv_key.setPadding(
                    DensityUtil.dip2px(context, 10),
                    0,
                    DensityUtil.dip2px(context, 10),
                    DensityUtil.dip2px(context, 2));
            TextView tv_value = new TextView(context);
            tv_value.setPadding(
                    DensityUtil.dip2px(context, 10),
                    0,
                    DensityUtil.dip2px(context, 10),
                    DensityUtil.dip2px(context, 2));
            TextView tv_data = new TextView(context);
            tv_data.setPadding(
                    DensityUtil.dip2px(context, 10),
                    0,
                    DensityUtil.dip2px(context, 10),
                    DensityUtil.dip2px(context, 2));

            layout.addView(tv_id);
            layout.addView(tv_key);
            layout.addView(tv_value);
            layout.addView(tv_data);

            view = layout;

            holder = new PearAdapterHolder();
            holder.tv_id = tv_id;
            holder.tv_key = tv_key;
            holder.tv_value = tv_value;
            holder.tv_data = tv_data;

            view.setTag(holder);

        } else {
            holder = (PearAdapterHolder) view.getTag();
        }

        PearNote pearNote = (PearNote) list.get(i);
        holder.tv_id.setText("ID:" + String.valueOf(pearNote.getId()));
        holder.tv_key.setText("KEY:" + pearNote.getKey());
        holder.tv_value.setText("VALUE:" + pearNote.getValue());
        holder.tv_data.setText("DATE:" + pearNote.getPearNoteDAO().toString());

        return view;
    }

}
