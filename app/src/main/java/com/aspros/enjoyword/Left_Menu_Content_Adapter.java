package com.aspros.enjoyword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * Created by Aspros on 16/3/31.
 */
public class Left_Menu_Content_Adapter extends ArrayAdapter<Left_Menu_Content> {

    private int resourceId;
    private View view;

    private ImageView left_menu_img;
    private TextView left_menu_text;
    public Left_Menu_Content_Adapter(Context context, int textViewResourceId, List<Left_Menu_Content> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get object left_menu_content
        Left_Menu_Content left_menu_content=getItem(position);
        //init view
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        //get ImageView,TextView form view
        left_menu_img= (ImageView) view.findViewById(R.id.left_menu_img);
        left_menu_text= (TextView) view.findViewById(R.id.left_menu_text);
        //set ImageView,TextView form object left_menu_content
        left_menu_img.setImageResource(left_menu_content.getLeft_Img());
        left_menu_text.setText(left_menu_content.getLeft_Content());
        //return view
        return view;
    }
}
