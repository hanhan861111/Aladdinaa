package com.aladdin.hxe.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.factory.FragmentFactory;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import java.lang.reflect.Field;

/**
 * Created by ${韩永光} on on 2018/1/23 0023 14:50..
 */

public class PendingFragment extends Fragment {
    private ViewPager vp_pending;
    private TabLayout tablayout_pending;
    private View view;
    String[] dataArray = new String[]{"新订单", "叫号", "取消订单","退款"};
    private Field tabStrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pending, null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initEvent() {
        tablayout_pending.addTab(tablayout_pending.newTab().setText(dataArray[0]), true);
        for (int i = 1; i < dataArray.length; i++) {
            tablayout_pending.addTab(tablayout_pending.newTab().setText(dataArray[i]), false);
        }
        tablayout_pending.setupWithViewPager(vp_pending);
        vp_pending.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = FragmentFactory.getFragment(position+4);
                return fragment;
            }

            @Override
            public int getCount() {
                return dataArray.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position ==0) {
                    return "新订单";
                }else if(position ==1){
                    return "叫号";
                }else if(position==2){
                    return "取消订单";
                }else{
                    return "退款";
                }
            }
        });
        vp_pending.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
           tablayout_pending.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView(View view) {
        vp_pending = (ViewPager) view.findViewById(R.id.vp_pending);
        tablayout_pending = (TabLayout) view.findViewById(R.id.tablayout_pending);
        vp_pending.setOffscreenPageLimit(4);
        String token =  SharedPreferencesUtils.getString(getActivity(), "token", "");
        requestData(token);
    }
    public void requestData(String token) {
        RequestManager.postJson().addParams("token", token)
                .setUrl(Url.findbytokenUrl)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderInfoBean>() {
                    @Override
                    public void onSuccess(OrderInfoBean baen) {
                        OrderInfoBean.DataBean data = baen.getData();
                        int userId = data.getId();
                        com.aladdin.hxe.utils.SharedPreferencesUtils.saveString(getActivity(), "userId", String.valueOf(userId));
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        tablayout_pending.post(new Runnable() {
            @Override
            public void run() {

                setIndicator(tablayout_pending,  CommonUtils.dip2px(5),  CommonUtils.dip2px(5));
            }
        });
    }
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }
}
