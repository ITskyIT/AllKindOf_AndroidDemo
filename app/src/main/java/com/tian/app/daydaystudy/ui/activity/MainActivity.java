package com.tian.app.daydaystudy.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.adapter.MainAdapter;
import com.tian.app.daydaystudy.view.DividerItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.main_rv)
    RecyclerView recyclerView;
    List<String> data=new ArrayList<>();
    MainAdapter adapter;

    @Override
    protected void init() {
        initRecycle();
    }



    /**
     * 初始化recycleview
     */
    private void initRecycle() {
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data.add("spannablestring");
        data.add("Retrofit_RxAndroid");
        data.add("Android与JS交互");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        adapter=new MainAdapter(this,data);
        recyclerView.setAdapter(adapter);
        adapter.setmListener(new MainAdapter.mainOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this,SpanStringActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,Retrofit_RxAndroidActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,WebViewJSActivity.class));
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


}
