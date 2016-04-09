package com.aspros.enjoyword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspros on 16/4/2.
 */
public class Guide extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> viewList;

    private ImageView[] dots;
    private int[] ids ={R.id.guide_img1,R.id.guide_img2,R.id.guide_img3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        initView();
        initDots();
    }

    private void initView()
    {
        LayoutInflater inflater=LayoutInflater.from(this);
        viewList=new ArrayList<View>();
        viewList.add(inflater.inflate(R.layout.guide_one,null));
        viewList.add(inflater.inflate(R.layout.guide_two,null));
        viewList.add(inflater.inflate(R.layout.guide_three,null));

        viewPagerAdapter=new ViewPagerAdapter(viewList,this);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        viewList.get(2).findViewById(R.id.login).setOnClickListener(this);
    }

    private void initDots()
    {
        dots=new ImageView[viewList.size()];
        for (int i=0;i<viewList.size();i++)
        {
            dots[i]= (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i=0;i<ids.length;i++)
        {
            if (position==i)
            {
                dots[i].setImageResource(R.drawable.dot_select);
            }
            else
            {
                dots[i].setImageResource(R.drawable.dot_unselect);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(Guide.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
