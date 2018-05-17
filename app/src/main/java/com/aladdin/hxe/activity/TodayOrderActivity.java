package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.aladdin.hxe.R;
import com.aladdin.hxe.factory.FragmentFactory;

public class TodayOrderActivity extends BaseActivity {
    String[] dataArray = new String[]{"今日订单", "全部"};
    private ImageButton imb_back;
    private TabLayout tablayout_todayOrder;
    private ViewPager vp_todayOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_order);
        initView();
        initData();
    }

    private void initData() {
        tablayout_todayOrder.addTab(tablayout_todayOrder.newTab().setText(dataArray[0]), true);
        for (int i = 1; i < dataArray.length; i++) {
            tablayout_todayOrder.addTab(tablayout_todayOrder.newTab().setText(dataArray[i]), false);
        }
        tablayout_todayOrder.setupWithViewPager(vp_todayOrder);
        vp_todayOrder.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = FragmentFactory.getFragment(position+8);
                return fragment;
            }

            @Override
            public int getCount() {
                return dataArray.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position ==0) {
                    return "今日订单";
                }else{
                    return "全部";
                }
            }
        });
        vp_todayOrder.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout_todayOrder.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tablayout_todayOrder = (TabLayout) findViewById(R.id.tablayout_todayOrder);
        vp_todayOrder = (ViewPager) findViewById(R.id.vp_todayOrder);
        //退出
        imb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
