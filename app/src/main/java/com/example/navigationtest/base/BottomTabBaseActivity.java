package com.example.navigationtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.navigationtest.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ysx on 2017/5/15.
 */

public abstract class BottomTabBaseActivity extends AppCompatActivity {

    ViewPager mViewPager;

    FragmentPagerAdapter mAdapter;

    BottomNavigationView mBottomNavigationView;

    List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab_base);
        initViews();
    }

    protected abstract int getMenuSourceId();

    protected abstract List<Fragment> getFragmentList();

    protected abstract Map<Integer, Integer> getMenuIdMap();

    protected abstract Map<Integer, Integer> getViewPagerPositionMap();


    private void initViews() {

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        int menuSourceId = getMenuSourceId();
        if (menuSourceId != 0) {
            mBottomNavigationView.inflateMenu(menuSourceId);
        }
        /**
         * disableShiftMode() works only when it is called after inflateMenu()
         * */
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Map<Integer, Integer> map = getMenuIdMap();
                mViewPager.setCurrentItem(map.get(item.getItemId()), true);
                return true;
            }
        });

        mFragmentList = getFragmentList();
        if (mFragmentList != null && mFragmentList.size() > 0) {
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Map<Integer, Integer> viewpagerPositionMap = getViewPagerPositionMap();
                    mBottomNavigationView.setSelectedItemId(viewpagerPositionMap.get(position));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mFragmentList.size();
                }
            };
            mViewPager.setAdapter(mAdapter);
        }
    }
}
