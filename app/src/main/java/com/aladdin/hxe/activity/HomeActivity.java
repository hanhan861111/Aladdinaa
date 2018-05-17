package com.aladdin.hxe.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.MessageEventE;
import com.aladdin.hxe.factory.FragmentFactory;
import com.aladdin.hxe.utils.CommonUtils;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class HomeActivity extends BaseActivity {

    private ViewPager vp_home;
    private RadioButton rb_manage;
    private RadioButton rb_mine;
    private RadioButton rb_pending;
    private RadioGroup rg_home;
    private long exitTime = 0;
    private GoogleApiClient client;
    private RadioButton rb_inquire;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        //初始化viewPager
        initViewPager();
        //初始化RadioGroup
        initRadioGroup();
    }


    //程序退出设置
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    private void initRadioGroup() {
        rg_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_pending:
                        vp_home.setCurrentItem(0);
                        break;
                    case R.id.rb_inquire:
                        vp_home.setCurrentItem(1);
                        break;
                    case R.id.rb_manage:
                        vp_home.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        vp_home.setCurrentItem(3);
                        break;
                    default:
                        break;

                }
            }
        });
    }

    private void initViewPager() {
        vp_home.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = FragmentFactory.getFragment(position);
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    private void initView() {
        //EventBus
        EventBus.getDefault().register(this);
        tts = new TextToSpeech(this, new MyOnInitialListener());
        vp_home = (ViewPager) findViewById(R.id.vp_home);
        rb_manage = (RadioButton) findViewById(R.id.rb_manage);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rb_pending = (RadioButton) findViewById(R.id.rb_pending);
        rb_inquire = (RadioButton) findViewById(R.id.rb_inquire);
        rg_home = (RadioGroup) findViewById(R.id.rg_home);
        vp_home.setOffscreenPageLimit(4);
        //定义底部标签图片大小
        int i1 = CommonUtils.dip2px(27);
        int i2 = CommonUtils.dip2px(29);
        int i3 = CommonUtils.dip2px(4);
        Drawable drawablePending = getResources().getDrawable(R.drawable.pending);
        drawablePending.setBounds(0, i3, i1, i2);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_pending.setCompoundDrawables(null, drawablePending, null, null);//只放上面

        Drawable drawableInquire = getResources().getDrawable(R.drawable.inquire);
        drawableInquire.setBounds(0, i3, i1, i2);
        rb_inquire.setCompoundDrawables(null, drawableInquire, null, null);

        Drawable drawableManage = getResources().getDrawable(R.drawable.manage);
        drawableManage.setBounds(0, i3, i1, i2);
        rb_manage.setCompoundDrawables(null, drawableManage, null, null);


        Drawable drawableMine = getResources().getDrawable(R.drawable.mine);
        drawableMine.setBounds(0, i3, i1, i2);
        rb_mine.setCompoundDrawables(null, drawableMine, null, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventE messageEvent) {
        String message = messageEvent.getMessage();
        tts.speak("您的账户到账"+message+"元", TextToSpeech.QUEUE_FLUSH, null);

    }

    //解除EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (tts != null) { // 关闭TTS引擎
            tts.shutdown();
        }

    }

    class MyOnInitialListener implements TextToSpeech.OnInitListener {

        @Override
        public void onInit(int status) {

            // tts.setEngineByPackageName("com.iflytek.vflynote");
            tts.setLanguage(Locale.CHINESE);

        }

    }



}
