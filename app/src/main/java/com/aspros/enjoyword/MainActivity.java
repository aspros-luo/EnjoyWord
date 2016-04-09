package com.aspros.enjoyword;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    private ListView listView;

    private ArrayList<Left_Menu_Content> arrayList=new ArrayList<Left_Menu_Content>();

    private Left_Menu_Content_Adapter arrayAdapter;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private LinearLayout leftLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        leftLayout = (LinearLayout) findViewById(R.id.drawer_LeftView);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);

        listView = (ListView) findViewById(R.id.left_show);

        initLeft_menu();
        arrayAdapter = new Left_Menu_Content_Adapter(this,R.layout.left_menu_item , arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.syncState();

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        Fragment fragment = new Fragment_Content_List();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_layout,fragment)
//                .addToBackStack(null)
                .commit();


    }

    private void initLeft_menu()
    {
        Left_Menu_Content left_menu_content1=new Left_Menu_Content(R.drawable.house,"My Index");
        arrayList.add(left_menu_content1);
        Left_Menu_Content left_menu_content2=new Left_Menu_Content(R.drawable.skull,"My Skull");
        arrayList.add(left_menu_content2);
        Left_Menu_Content left_menu_content3=new Left_Menu_Content(R.drawable.heart,"My Heart");
        arrayList.add(left_menu_content3);
        Left_Menu_Content left_menu_content4=new Left_Menu_Content(R.drawable.bug,"My Bug");
        arrayList.add(left_menu_content4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent i=new Intent(MainActivity.this,Car_List.class);

                startActivity(i);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        drawerLayout.closeDrawer(leftLayout);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
