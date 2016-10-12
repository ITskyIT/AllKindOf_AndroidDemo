package com.tian.app.daydaystudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.view.CornerLabelView;

public class CustomViewActivity extends AppCompatActivity {
    CornerLabelView cornerLabelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        cornerLabelView= (CornerLabelView) findViewById(R.id.sdd);
        cornerLabelView.setText1("6666");

    }
}
