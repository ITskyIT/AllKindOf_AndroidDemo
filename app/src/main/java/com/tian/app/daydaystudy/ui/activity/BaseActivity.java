package com.tian.app.daydaystudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tian.app.daydaystudy.util.Tool;

import org.xutils.x;

/**
 * Created by Administrator on 2016/9/2.
 */
public abstract class BaseActivity extends AppCompatActivity{
    Tool mToast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mToast=new Tool(this);
        init();
    }

    protected abstract void init();


}
