package com.tian.app.daydaystudy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tian.app.daydaystudy.ui.fragment.DesginFragment;

/**
 * Created by jiujiu on 2016/6/1.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter{

    private String[] titles = new String[]{"自定义1", "自定义2", "自定义3", "自定义4", "自定义5", "自定义6", "自定义7", "自定义8", "自定义9", "自定义10"};
    final int COUNT = titles.length;
    private Context context;
    public MyFragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return DesginFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
