package com.aladdin.hxe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;

public class UpdatePhoneActivity extends Activity implements View.OnClickListener {

    private TextView tv_cancel;
    private TextView tv_submit;
    private EditText et_code;
    private TextView tv_time;
    private int countSeconds = 60;//倒计时秒数
    private String shopphone;
    private MyCountDownTimer myCountDownTimer;
    private String token;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone);
        initView();
        initEvent();
        //6012
    }

    private void initEvent() {
        tv_time.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        et_code = (EditText) findViewById(R.id.et_code);
        tv_time = (TextView) findViewById(R.id.tv_time);
        token = SharedPreferencesUtils.getString(this, "token", "");
        shopphone = getIntent().getStringExtra("shopphone");
        //new倒计时对象,总共的时间,每隔多少秒更新一次时间
        myCountDownTimer = new MyCountDownTimer(5000, 1000);
        myCountDownTimer.start();

    }

    private void getidentifyingCode(String shopphone) {
        RequestManager.postJson()
                .addParams("phone", shopphone)
                .setUrl(Url.smsURL)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<LoginBean>() {
                    @Override
                    public void onErr(String s) {

                    }

                    @Override
                    public void onSuccess(LoginBean baen) {
                        if ("200".equals(baen.getStatus())) {
                            myCountDownTimer.start();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time:
                getidentifyingCode(shopphone);
                break;
            case R.id.tv_submit:
                code = et_code.getText().toString().trim();
                updatePhone(shopphone, code);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void updatePhone(String shopphone, String code) {
        RequestManager.postJson()
                .addParams("token", token)
                .addParams("phone", shopphone)
                .addParams("code", code)
                .setUrl(Url.updatePhoneURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                Log.d("EEEEEEEEE",baen.getStatus());
                if ("200".equals(baen.getStatus())) {
                    EventBus.getDefault().post(new MessageEventC("PHONE"));
                    finish();
                } else {
                    Toast.makeText(UpdatePhoneActivity.this, "验证码有误,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            tv_time.setClickable(false);
            tv_time.setText(l / 1000 + "s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            tv_time.setText("重新获取验证码");
            //设置可点击
            tv_time.setClickable(true);
        }
    }
}
