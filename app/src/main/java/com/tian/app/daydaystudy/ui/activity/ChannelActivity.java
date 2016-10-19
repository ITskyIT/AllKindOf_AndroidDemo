package com.tian.app.daydaystudy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tian.app.daydaystudy.R;

/**
 * 频道添加
 * 资源来自https://github.com/jaydenxiao2016/AndroidFire
 */
public class ChannelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

    }
    public static void startAction(Context context){
        Intent intent = new Intent(context, ChannelActivity.class);
        context.startActivity(intent);
    }
}
