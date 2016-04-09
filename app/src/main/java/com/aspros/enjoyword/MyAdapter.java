package com.aspros.enjoyword;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 适配器
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Bean> list;
    private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
    public onItemClickListener itemClickListener;// 接口回调

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MyAdapter(ArrayList<Bean> list, Context context) {
        super();
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.gridview_item, null);
            /** 得到各个控件的对象 */
            holder.title = (TextView) convertView.findViewById(R.id.gridview_ItemText);

            holder.layout = (LinearLayout) convertView.findViewById(R.id.gridView_Layout);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
        }
        /** 设置TextView显示的内容，即我们存放在动态数组中的数据 */
        final Bean skuItem=list.get(position);
        switch (skuItem.getStates()) {
            case "0":
                holder.layout.setBackgroundResource(R.xml.shape1);
                holder.title.setTextColor(Color.BLACK);
                break;
            case "1":
                holder.layout.setBackgroundResource(R.xml.shape2);
                holder.title.setTextColor(Color.WHITE);
                break;
            case "2":
                holder.layout.setBackgroundResource(R.xml.shape1);
                holder.title.setTextColor(Color.parseColor("#999999"));
                break;
            default:
                break;
        }
        holder.title.setText(skuItem.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    if(skuItem.getStates()!="2"){
                        itemClickListener.onItemClick(skuItem, position);
                    }
                }
            }
        });
//         点击改变选中listItem的背景色
//        if (list.get(position).getNameIsSelect()) {
//            holder.layout.setBackgroundResource(R.xml.shape2);
//        } else {
//            holder.layout.setBackgroundResource(R.xml.shape1);
//        }
        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public LinearLayout layout;
    }
    public interface onItemClickListener
    {
        public void  onItemClick(Bean bean, int position);
    }
}
