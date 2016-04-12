package com.aspros.enjoyword;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Aspros on 16/3/31.
 */
public class Fragment_Content_View extends Fragment {

    private TextView content_View_Title;
    private TextView content_View_Tag;
    private ImageView content_View_TagImg;
    private TextView content_View_Content;
    private View view;
    private static boolean mHandledPress = false;


    private GridView gridview;
    private GridView gridview1;

    private ArrayList<SkuItem> listItem = new ArrayList<SkuItem>();

    private String[] versionString = {"A", "B", "C"};
    private String[] typeString = {"a", "b", "c", "d"};

    private ArrayList<Bean> mVersion = new ArrayList<Bean>();
    private MyAdapter mVersionAdapter;
    private ArrayList<Bean> mType = new ArrayList<Bean>();
    private MyAdapter mTypeAdapter;

    private TextView numTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content_view, null);
        int wordContent_id = getArguments().getInt("wordContent_id");
        String wordContent_title = getArguments().getString("wordContent_title");
        String wordContent_tag = getArguments().getString("wordContent_tag");
        int wordContent_tagImg = getArguments().getInt("wordContent_tagImg");
        String wordContent_content = getArguments().getString("wordContent_content");

        initContent(wordContent_id, wordContent_title, wordContent_tag, wordContent_tagImg, wordContent_content);
        return view;
    }

    private void initContent(int id, String title, String tag, int tagImg, String content) {

        view.findViewById(R.id.add_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSku(v);
            }
        });
        view.findViewById(R.id.buy_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSku(v);
            }
        });
        content_View_Title = (TextView) view.findViewById(R.id.content_view_title);
        content_View_Tag = (TextView) view.findViewById(R.id.content_view_tag);
        content_View_TagImg = (ImageView) view.findViewById(R.id.content_view_tagImg);
        content_View_Content = (TextView) view.findViewById(R.id.content_view_content);

        content_View_Title.setText(title);
        content_View_Tag.setText(tag);
        content_View_TagImg.setImageResource(tagImg);
        content_View_Content.setText("\t\t\t\t" + content);
    }

    /**
     * 设置数据
     */
    private void setData() {
        listItem = new ArrayList<SkuItem>();
        mVersion = new ArrayList<Bean>();
        mType = new ArrayList<Bean>();
        for (int i = 0; i < versionString.length; i++) {
            Bean bean = new Bean();
            bean.setName(versionString[i]);
            bean.setStates("0");
            mVersion.add(bean);
        }
        for (int i = 0; i < typeString.length; i++) {
            Bean bean = new Bean();
            bean.setName(typeString[i]);
            bean.setStates("0");
            mType.add(bean);
        }

        for (int i = 0; i < mVersion.size(); i++) {
            for (int j = 0; j < mType.size(); j++) {
                SkuItem skuItem1 = new SkuItem();
                skuItem1.setNameVersion(versionString[i]);
                skuItem1.setNameType(typeString[j]);
                skuItem1.setNameNum(i + j + 1);
                skuItem1.setNamePrice((i+j+1)*10);
                listItem.add(skuItem1);
            }
        }

    }

    private void showSku(final View v1)
    {
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        // 引入窗口配置文件 - 即弹窗的界面
        final View view = inflater.inflate(R.layout.popwindow, null);

        // PopupWindow实例化
        final PopupWindow pop = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        //设置动画
        pop.setAnimationStyle(R.style.animation);

        numTxt = (TextView) view.findViewById(R.id.numText);

        gridview = (GridView) view.findViewById(R.id.my_gridview);
        gridview1 = (GridView) view.findViewById(R.id.my_gridview1);
        final TextView versionTxt = (TextView) view.findViewById(R.id.version_text);
        final TextView typeTxt = (TextView) view.findViewById(R.id.type_text);
        setData();

        mVersionAdapter = new MyAdapter(mVersion, view.getContext());
        gridview.setAdapter(mVersionAdapter);
        mVersionAdapter.setItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Bean bean, int position) {
//                        Toast.makeText(view.getContext(), bean.getNameState() + "", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < mVersion.size(); i++) {
                    if (i == position) {
                        bean.setStates("1");
                        versionTxt.setText(bean.getName());
                        if (typeTxt.getText().equals("")) {
                            //库存总数
                            int num = DataUtil.getNumByVersion(listItem, bean.getName());
                            numTxt.setText(num + "");
                        } else {
                            numTxt.setText(DataUtil.getNumByVersionAndType(listItem, versionTxt.getText().toString(), typeTxt.getText().toString()) + "");
                        }
                    } else {
                        if (mVersion.get(i).getStates() == "2") {

                        } else if (mVersion.get(i).getStates() == "1") {
                            mVersion.get(i).setStates("0");
                        }
                    }
                }
                mVersionAdapter.notifyDataSetChanged();
            }
        });

        mTypeAdapter = new MyAdapter(mType, view.getContext());
        gridview1.setAdapter(mTypeAdapter);
        mTypeAdapter.setItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Bean bean, int position) {
                for (int i = 0; i < mType.size(); i++) {
                    if (i == position) {
                        bean.setStates("1");
                        typeTxt.setText(bean.getName());
                        if (versionTxt.getText().equals("")) {
                            int num = DataUtil.getNumByType(listItem, bean.getName());
                            numTxt.setText(num + "");
                        } else {
                            numTxt.setText(DataUtil.getNumByVersionAndType(listItem, versionTxt.getText().toString(), typeTxt.getText().toString()) + "");

                        }
                    } else {
                        if (mType.get(i).getStates() == "2") {

                        } else if (mType.get(i).getStates() == "1") {
                            mType.get(i).setStates("0");
                        }
                    }
                }
                mTypeAdapter.notifyDataSetChanged();
            }
        });


        //设置SelectPicPopupWindow弹出窗体的背景
        pop.setBackgroundDrawable(new BitmapDrawable());

        if (pop.isShowing()) {
            pop.dismiss();
        } else {
            pop.showAtLocation(v1, Gravity.BOTTOM, 0, 0);
        }

        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v1.getId())
                {
                    case R.id.add_car:
                        pop.dismiss();
                        Toast.makeText(view.getContext(), "添加购物车成功", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.buy_car:
                        pop.dismiss();
                        Intent i=new Intent(view.getContext(),Confirm_Order.class);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//                        Toast.makeText(view.getContext(), "跳转购买页面", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
