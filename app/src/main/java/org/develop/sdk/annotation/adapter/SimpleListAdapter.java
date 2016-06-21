package org.develop.sdk.annotation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.develop.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:SimpleListAdapter
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月24日18:28
 * <br/>
 * 功能描述：
 * <br/>
 */
public class SimpleListAdapter extends BaseAdapter{

    private List<String> list=new ArrayList<String>();

    private Context context;
    public SimpleListAdapter(Context context,List<String> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.simple_item,null);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) convertView.findViewById(R.id.simple_item_text);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }

}