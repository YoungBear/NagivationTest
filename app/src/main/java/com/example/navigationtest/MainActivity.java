package com.example.navigationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.navigationtest.base.BottomTabBaseActivity;
import com.example.navigationtest.fragment.Fragment1;
import com.example.navigationtest.fragment.Fragment2;
import com.example.navigationtest.fragment.Fragment3;
import com.example.navigationtest.fragment.Fragment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BottomTabBaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getMenuSourceId() {
        return R.menu.menu_bottom_nagivation;
    }

    @Override
    protected List<Fragment> getFragmentList() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        return fragments;
    }

    @Override
    protected Map<Integer, Integer> getMenuIdMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(R.id.navigation_home, 0);
        map.put(R.id.navigation_dashboard, 1);
        map.put(R.id.navigation_notifications, 2);
        map.put(R.id.navigation_settings, 3);
        return map;
    }

    @Override
    protected Map<Integer, Integer> getViewPagerPositionMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, R.id.navigation_home);
        map.put(1, R.id.navigation_dashboard);
        map.put(2, R.id.navigation_notifications);
        map.put(3, R.id.navigation_settings);
        return map;
    }
}
