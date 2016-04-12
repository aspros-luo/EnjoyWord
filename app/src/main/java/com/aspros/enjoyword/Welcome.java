package com.aspros.enjoyword;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Aspros on 16/4/3.
 */
public class Welcome extends Activity {

    private static boolean isFirstInit = false;
    private static final int GUIDE_MESSAGE = 0;
    private static final int MAIN_MESSAGE = 1;
    private static final int DISPLAY_TIME = 3000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GUIDE_MESSAGE:
                    go_guide();
                    break;
                case MAIN_MESSAGE:
                    go_mainActivity();
                    break;
                default:
                    break;
            }
        }
    };

    private void init() {
        SharedPreferences sharedPreferences = getSharedPreferences("initWelcome", MODE_PRIVATE);
        isFirstInit = sharedPreferences.getBoolean("isFirstIn", true);
        Message message = new Message();
        if (!isFirstInit) {
            handler.sendEmptyMessageDelayed(MAIN_MESSAGE, DISPLAY_TIME);
        } else {
            Intent intent=new Intent(this,LoadDataService.class);
            startService(intent);
            handler.sendEmptyMessageDelayed(GUIDE_MESSAGE, DISPLAY_TIME);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }

    private void go_guide() {
        Intent i = new Intent(Welcome.this, Guide.class);
        startActivity(i);
        finish();
    }

    private void go_mainActivity() {
        Intent i = new Intent(Welcome.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
