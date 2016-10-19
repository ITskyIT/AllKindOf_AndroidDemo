package com.tian.app.daydaystudy.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.adapter.DesginAdapter;
import com.tian.app.daydaystudy.widget.StatusBarCompat;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;

@ContentView(R.layout.activity_open_close)
public class OpenCloseActivity extends BaseActivity {


    private ArrayList<String> mlistData;

    @Override
    protected void init() {
       StatusBarCompat.translucentStatusBar(this);
        mlistData=new ArrayList<String>();
        for (int i='A';i<'J';i++){
            mlistData.add("cardView"+(char)i);
        }
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("CollapsingToolbarLayout");
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        RecyclerView mRecyclerView= (RecyclerView) findViewById(R.id.open_rv);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DesginAdapter adapter=new DesginAdapter(this,mlistData);
        mRecyclerView.setAdapter(adapter);
        adapter.setmListener(new DesginAdapter.mainOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mToast.showToast("我爱Android");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
