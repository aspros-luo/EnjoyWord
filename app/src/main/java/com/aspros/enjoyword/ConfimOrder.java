package com.aspros.enjoyword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ConfimOrder extends AppCompatActivity {

    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confim_order);
        layout= (LinearLayout) findViewById(R.id.order_info);
        layout.setBackgroundResource(R.xml.shape2);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfimOrder.this, "aaa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
