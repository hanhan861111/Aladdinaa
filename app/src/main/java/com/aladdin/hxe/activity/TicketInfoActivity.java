package com.aladdin.hxe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.TicketBean;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;

import org.greenrobot.eventbus.EventBus;

public class TicketInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private TextView tv_type;
    private TextView tv_name;
    private TextView tv_num;
    private TicketBean.DataBean item;
    private String token;
    private String brand;
    private String ticketNum;
    private TextView tv_unbindTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info);
        initView();
        getData();
        setData();
    }

    private void setData() {
        if (item.getType().equals("GP")) {
            tv_type.setText("GPRS");
        }
        if (item.getName().equals("HC")) {
            tv_name.setText("后厨");
        }
        if (item.getName().equals("SY")) {
            tv_name.setText("收银");
        }
        tv_num.setText(item.getDeviceNo());
    }

    private void getData() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        Bundle bundle = getIntent().getExtras();
        item = (TicketBean.DataBean) bundle.getSerializable("item");
        brand = item.getBrand();
    }

    private void initView() {
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_unbindTicket = (TextView) findViewById(R.id.tv_unbindTicket);
        imb_back.setOnClickListener(this);
        tv_unbindTicket.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.tv_unbindTicket:
                ticketNum = tv_num.getText().toString().trim();
                unbindTicket(ticketNum);
                break;
            default:
                break;
        }
    }

    private void unbindTicket(String ticketNum) {
        RequestManager.postJson()
                .addParams("deviceNo", ticketNum)
                .addParams("brand", brand)
                .addParams("token", token).setUrl(Url.unbindTicketURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if (baen.getStatus().equals("200")) {
                    EventBus.getDefault().post(new MessageEventC("UNBIND"));
                    Toast.makeText(TicketInfoActivity.this, "解绑票机成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(TicketInfoActivity.this, "解绑票机失败,请检查类型", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}
