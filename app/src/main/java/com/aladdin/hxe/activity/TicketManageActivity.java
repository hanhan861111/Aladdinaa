package com.aladdin.hxe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.TicketBean;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TicketManageActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private TextView tv_addGRPSTicket;
    private ListView lv_myTicket;
    private String token;
    private TextView tv_addBLUETicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_manage);
        initView();
        initEvent();
    }

    private void initEvent() {
        //获取票机信息
        getTicket();
        imb_back.setOnClickListener(this);
        tv_addGRPSTicket.setOnClickListener(this);
        tv_addBLUETicket.setOnClickListener(this);
    }

    private void initView() {
        //EventBus
        EventBus.getDefault().register(this);
        token = SharedPreferencesUtils.getString(this, "token", "");
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_addGRPSTicket = (TextView) findViewById(R.id.tv_addGRPSTicket);
        lv_myTicket = (ListView) findViewById(R.id.lv_myTicket);
        tv_addBLUETicket = (TextView) findViewById(R.id.tv_addBLUETicket);
    }

    private void getTicket() {
        RequestManager.postJson().addParams("token", token)
                .setUrl(Url.findTicketURL)
                .builder().onUI().setCallback(new IRequestBeanListener<TicketBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(TicketBean baen) {
                final List<TicketBean.DataBean> data = baen.getData();
                if (baen.getStatus() == 200 && baen.getData().size() > 0) {
                    CommonAdapter<TicketBean.DataBean> commonAdapter = getDataBeanCommonAdapter(data);
                    lv_myTicket.setAdapter(commonAdapter);
                } else {
                    data.clear();
                    CommonAdapter<TicketBean.DataBean> commonAdapter = getDataBeanCommonAdapter(data);
                    lv_myTicket.setAdapter(commonAdapter);
                    Toast.makeText(TicketManageActivity.this, "您还未绑定任何票机...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @NonNull
    private CommonAdapter<TicketBean.DataBean> getDataBeanCommonAdapter(final List<TicketBean.DataBean> data) {
        return new CommonAdapter<TicketBean.DataBean>(TicketManageActivity.this, R.layout.item_ticket, data) {
                            @Override
                            protected void convert(ViewHolder viewHolder, final TicketBean.DataBean item, int position) {
                                if (item.getBrand().equals("FC")) {
                                    viewHolder.setText(R.id.tv_name, "风驰(票机)");
                                }
                                viewHolder.setText(R.id.tv_statuse, item.getPrintersStatus());
                                viewHolder.setOnClickListener(R.id.ll_itemTicket, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("item", item);
                                        Intent intent = new Intent(TicketManageActivity.this, TicketInfoActivity.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_back:
                finish();
                break;
            case R.id.tv_addGRPSTicket:
                CommonUtils.startActivity(TicketManageActivity.this, AddTicketActivity.class);
                break;
            case R.id.tv_addBLUETicket:
               // CommonUtils.startActivity(TicketManageActivity.this,BlueActivity.class);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventC messageEvent) {
        if(messageEvent.getMessage().equals("UNBIND")){
            getTicket();
        }

    }

    //解除EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }

}
