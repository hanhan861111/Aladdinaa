package com.aladdin.hxe.fragment;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.MessageEventTime;
import com.aladdin.hxe.factory.FragmentFactory;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * Created by ${韩永光} on on 2018/4/26 0026 16:55..
 */

public class InquireFragment extends Fragment implements View.OnClickListener {

    private View view;
    String[] dataArray = new String[]{"全部", "进行中", "已完成", "取消/退款"};
    private ViewPager vp_inquire;
    private TabLayout tablayout_inquire;
    private TextView tv_today;
    private TextView tv_date;
    private Calendar calender;
    private String todayStr;
    private String token;
    private String dateStr, mouth1, day1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_inquire, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initEvent() {
        tv_date.setOnClickListener(this);
        tv_today.setOnClickListener(this);
        tablayout_inquire.addTab(tablayout_inquire.newTab().setText(dataArray[0]), true);
        for (int i = 1; i < dataArray.length; i++) {
            tablayout_inquire.addTab(tablayout_inquire.newTab().setText(dataArray[i]), false);
        }
        tablayout_inquire.setupWithViewPager(vp_inquire);
        vp_inquire.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = FragmentFactory.getFragment(position + 10);
                return fragment;
            }

            @Override
            public int getCount() {
                return dataArray.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "全部";
                } else if (position == 1) {
                    return "进行中";
                } else if (position == 2) {
                    return "已完成";
                } else {
                    return "取消/退款";
                }
            }
        });
        vp_inquire.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout_inquire.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView(View view) {
        todayStr = TimeUtils.GetCurrentTime();
        calender = Calendar.getInstance();
        tv_today = (TextView) view.findViewById(R.id.tv_today);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        vp_inquire = (ViewPager) view.findViewById(R.id.vp_inquire);
        tablayout_inquire = (TabLayout) view.findViewById(R.id.tablayout_inquire);
        vp_inquire.setOffscreenPageLimit(4);
        token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        //requestData(token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_today:
                EventBus.getDefault().post(new MessageEventTime(todayStr));
                tv_today.setTextColor(0xffffd200);
                tv_today.setBackgroundColor(Color.WHITE);
                tv_date.setTextColor(Color.WHITE);
                tv_date.setBackgroundColor(0xffffd200);
                break;
            //日历
            case R.id.tv_date:
                tv_date.setTextColor(0xffffd200);
                tv_date.setBackgroundColor(Color.WHITE);
                tv_today.setTextColor(Color.WHITE);
                tv_today.setBackgroundColor(0xffffd200);
                //通过values和values-v21下的MyDatePickerDialogTheme改变颜色，
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int
                                    dayOfMonth) {
                                if (monthOfYear <= 9) {
                                    mouth1 = "0" + (monthOfYear + 1);
                                } else {
                                    mouth1 = String.valueOf(monthOfYear + 1);
                                }
                                if (dayOfMonth <= 9) {
                                    day1 = "0" + dayOfMonth;
                                } else {
                                    day1 = String.valueOf(dayOfMonth);
                                }
                                dateStr = String.valueOf(year) + "-" + mouth1 + "-" + day1;
                                tv_date.setText(dateStr.substring(5, 10));
                                EventBus.getDefault().post(new MessageEventTime(dateStr + " " + "00:00:00"));
                            }
                        }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH),
                        calender.get(Calendar.DAY_OF_MONTH));
                //设置起始日期和结束日期
                DatePicker datePicker = datePickerDialog.getDatePicker();
                // datePicker.setMinDate();
                datePicker.setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

                break;
            default:
                break;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        tablayout_inquire.post(new Runnable() {
            @Override
            public void run() {

                setIndicator(tablayout_inquire,  CommonUtils.dip2px(5),  CommonUtils.dip2px(5));
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
