package com.aspros.enjoyword;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspros on 16/4/10.
 */
public class Address_Manager extends Activity implements View.OnClickListener {

    private DatabaseHelper dbHelp;
    private SQLiteDatabase db;

    private List<BaseInfo> provinceList = new ArrayList<BaseInfo>();
    private List<BaseInfo> cityList = new ArrayList<BaseInfo>();
    private List<BaseInfo> areaList = new ArrayList<BaseInfo>();

    private WheelView province_Wheel;
    private WheelView city_Wheel;
    private WheelView area_Wheel;

    private String provinceId = null;
    private String cityId = null;

    private TextView tv_city;

    private EditText et_Name;
    private EditText et_PhoneNum;
    private EditText et_PostCode;
    private EditText et_DetailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);

        tv_city = (TextView) findViewById(R.id.tv_city);
        //监听地址选择点击事件
        findViewById(R.id.tv_city).setOnClickListener(this);

        findViewById(R.id.btn_saveAddress).setOnClickListener(this);

        et_Name = (EditText) findViewById(R.id.et_Name);
        et_PhoneNum = (EditText) findViewById(R.id.et_PhoneNum);
        et_PostCode = (EditText) findViewById(R.id.et_PostCode);
        et_DetailAddress = (EditText) findViewById(R.id.et_DetailAddress);

        dbHelp = new DatabaseHelper(this, "countryList.db", null, 1);
        db = dbHelp.getWritableDatabase();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:

                LayoutInflater inflater = LayoutInflater.from(Address_Manager.this);
                // 引入窗口配置文件 - 即弹窗的界面
                final View view = inflater.inflate(R.layout.activity_address_select, null);

                province_Wheel = (WheelView) view.findViewById(R.id.province_Wheel);
                city_Wheel = (WheelView) view.findViewById(R.id.city_Wheel);
                area_Wheel = (WheelView) view.findViewById(R.id.area_Wheel);

                //设置地区选择为隐藏
                area_Wheel.setVisibility(View.INVISIBLE);

                province_Wheel.setOffset(0);
                province_Wheel.setItems(getProvinceList());
                province_Wheel.setSelection(0);
                province_Wheel.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(BaseInfo p) {
                        provinceId = p.getId();
                        city_Wheel.setOffset(0);
                        city_Wheel.setItems(getCityList(p.getId()));
                        city_Wheel.setSelection(0);
                        city_Wheel.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                            @Override
                            public void onSelected(BaseInfo bi) {
                                cityId = bi.getId();

                                if (getAreaList(provinceId, cityId).size() > 0) {
                                    area_Wheel.setVisibility(View.VISIBLE);
                                    area_Wheel.setOffset(0);
                                    area_Wheel.setItems(getAreaList(provinceId, cityId));
                                    area_Wheel.setSelection(0);
                                } else {
                                    area_Wheel.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                    }

                });


                // PopupWindow实例化
                final PopupWindow pop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                //设置动画
                pop.setAnimationStyle(R.style.animation);
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);

                //监听地址确定按钮点击事件
                view.findViewById(R.id.back_address).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (area_Wheel.getVisibility() == View.VISIBLE) {
                            tv_city.setText(province_Wheel.getSelectedItem() + "|" + city_Wheel.getSelectedItem() + "|" + area_Wheel.getSelectedItem());
                        } else {
                            tv_city.setText(province_Wheel.getSelectedItem() + "|" + city_Wheel.getSelectedItem());
                        }
                        pop.dismiss();
                    }
                });

                break;
            case R.id.btn_saveAddress:
//                Toast.makeText(Address_Manager.this, et_Name.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("intentExtra_name", et_Name.getText()+"");
                intent.putExtra("intentExtra_phoneNum", et_PhoneNum.getText()+"");
                intent.putExtra("intentExtra_postCode", et_PostCode.getText()+"");
                intent.putExtra("intentExtra_address", tv_city.getText()+"|"+et_DetailAddress.getText());
                Address_Manager.this.setResult(RESULT_OK, intent);
                finish();

                break;
            default:
                break;
        }
    }

    //加载城市数据
    private List<BaseInfo> getProvinceList() {
        Cursor cursor = db.rawQuery("select * from province", null);
        while (cursor.moveToNext()) {
            String provinceName = cursor.getString(cursor.getColumnIndex("provinceName"));
            String provinceId = cursor.getString(cursor.getColumnIndex("id"));
            BaseInfo pi = new BaseInfo();
            pi.setId(provinceId);
            pi.setShowName(provinceName);
            provinceList.add(pi);
        }
        cursor.close();
        return provinceList;
    }

    //根据省份id获取城市
    private List<BaseInfo> getCityList(String provinceId) {
        cityList = new ArrayList<BaseInfo>();
        Cursor cursor = db.rawQuery("select * from city where provinceId=?", new String[]{provinceId});
        while (cursor.moveToNext()) {
            String cityName = cursor.getString(cursor.getColumnIndex("cityName"));
            String cityId = cursor.getString(cursor.getColumnIndex("id"));
            BaseInfo ci = new BaseInfo();
            ci.setId(cityId);
            ci.setShowName(cityName);
            cityList.add(ci);
        }
        cursor.close();
        Log.d("getCityList", "citySize:" + cityList.size() + "|provinceId:" + provinceId);
        return cityList;
    }

    //根据省份id和城市id获取区县
    private List<BaseInfo> getAreaList(String provinceId, String cityId) {
        areaList = new ArrayList<BaseInfo>();
        if (provinceId != null && cityId != null) {

            Cursor cursor = db.rawQuery("select * from area where provinceId=? and cityId=?", new String[]{provinceId, cityId});
            while (cursor.moveToNext()) {
                String areaName = cursor.getString(cursor.getColumnIndex("areaName"));
                String areaId = cursor.getString(cursor.getColumnIndex("id"));
                BaseInfo ai = new BaseInfo();
                ai.setId(areaId);
                ai.setShowName(areaName);
                areaList.add(ai);
            }
            cursor.close();
        } else {
        }
        return areaList;
    }
}
