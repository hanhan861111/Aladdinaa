package com.aladdin.hxe.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton img_back;
    private Button bt_submit;
    private EditText et_feedBack;
    private int id;
    private String userName;
    private String phone;
    private String token;
    private LinearLayout ll_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        final OrderInfoBean.DataBean dataBean = (OrderInfoBean.DataBean) bundle.getSerializable("ShopKey");
        id = dataBean.getId();
        userName = dataBean.getUserName();
        phone = dataBean.getPhone();
        token = SharedPreferencesUtils.getString(FeedBackActivity.this, "token", "");
    }

    private void initEvent() {
        img_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        ll_phone.setOnClickListener(this);
    }

    private void initView() {
        img_back = (ImageButton) findViewById(R.id.imb_back);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        et_feedBack = (EditText) findViewById(R.id.et_feedBack);
        ll_phone = (LinearLayout) findViewById(R.id.ll_phone);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回
            case R.id.imb_back:
                finish();
                break;
            //提交
            case R.id.bt_submit:
                String content = et_feedBack.getText().toString().trim();
                if(!content.equals("")) {
                    upLoadMessage(content);
                }else{
                    Toast.makeText(FeedBackActivity.this,"提交内容不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            //联系电话
            case  R.id.ll_phone:
                if (ContextCompat.checkSelfPermission(FeedBackActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "037156033072");
                intent.setData(data);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void upLoadMessage(String content) {
        RequestManager.postJson()
                .addParams("token", token)
                .addParams("content",content)
                .addParams("userType",String.valueOf(3))
                .addParams("userId", String.valueOf(id))
                .addParams("userUserName",userName)
                .addParams("userPhone",phone)
                .setUrl(Url.adduserbackmsgURL)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean baen) {
                        if (baen.getStatus().equals("200")) {
                            Toast.makeText(FeedBackActivity.this,"提交反馈成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(FeedBackActivity.this,"提交反馈失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErr(String s) {
                    }
                });
    }
}
