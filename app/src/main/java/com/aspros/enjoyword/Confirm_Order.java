package com.aspros.enjoyword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Confirm_Order extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layout;
    private TextView tv_orderName;
    private TextView tv_orderPhoneNum;
    private TextView tv_orderPostCode;
    private TextView tv_orderAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        layout = (LinearLayout) findViewById(R.id.order_Info);
        tv_orderName = (TextView) findViewById(R.id.order_Name);
        tv_orderPhoneNum = (TextView) findViewById(R.id.order_PhoneNum);
        tv_orderPostCode = (TextView) findViewById(R.id.order_PostCode);
        tv_orderAddress = (TextView) findViewById(R.id.order_Address);

        layout.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {

                    String nameStr = data.getStringExtra("intentExtra_name");
                    String phoneNumStr = data.getStringExtra("intentExtra_phoneNum");
                    String postCodeStr = data.getStringExtra("intentExtra_postCode");
                    String addressStr = data.getStringExtra("intentExtra_address");

//                    Toast.makeText(Confirm_Order.this, "3:"+nameStr+"|"+addressStr, Toast.LENGTH_SHORT).show();

                    tv_orderName.setText(nameStr);
                    tv_orderPhoneNum.setText(phoneNumStr);
                    tv_orderPostCode.setText(postCodeStr);
                    tv_orderAddress.setText(addressStr);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_Info:
                Intent intent = new Intent(Confirm_Order.this, Address_Manager.class);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            default:
                break;
        }
    }
}
