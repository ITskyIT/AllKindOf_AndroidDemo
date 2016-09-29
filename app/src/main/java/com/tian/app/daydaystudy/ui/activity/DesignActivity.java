package com.tian.app.daydaystudy.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.adapter.MyFragmentAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_design)
public class DesignActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @ViewInject(R.id.vps)
    ViewPager viewPager;
    @ViewInject(R.id.tabs)
    TabLayout tabLayout;
    @ViewInject(R.id.navi_view)
    NavigationView navigationView;
    private MyFragmentAdapter adapter;
    @Override
    protected void init() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        /*    配置 打开和关闭侧边栏  */
        ActionBarDrawerToggle adt=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(adt);
        adt.syncState();
        /*   侧边栏点击监听     */
        navigationView.setNavigationItemSelectedListener(this);
        adapter=new MyFragmentAdapter(getSupportFragmentManager(),this);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    /**
     * 退出时 关闭侧边栏
     */
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Event(value = {R.id.fab})
    private void desginOnClick(View v){
        switch (v.getId()){
            case R.id.fab:
                //点击FloatingActionButton
                //snackbar 类似吐司
                Snackbar.make(v, "确定要赞一个吗", Snackbar.LENGTH_SHORT)
                        .setAction("Sure", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mToast.showToast("66哒☺");
                            }
                        }).show();
                break;
        }

    }
    /**
     * 侧边栏选中的item
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
