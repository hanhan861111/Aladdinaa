package com.aladdin.hxe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.activity.FeedBackActivity;
import com.aladdin.hxe.activity.MainActivity;
import com.aladdin.hxe.activity.ShopInfoActivity;
import com.aladdin.hxe.activity.TicketManageActivity;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventB;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

/**
 * Created by ${韩永光} on on 2018/1/23 0023 14:51..
 */

public class SetFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView img_mine;
    private LinearLayout ll_shopname_mine;
    private TextView tv_shopname_mine;
    private LinearLayout ll_shopinfo_mine;
    private LinearLayout ll_feedback_mine;
    private OrderInfoBean.DataBean data;
    private Bundle bundle;
    private Intent intent;
    private ToggleButton mToggleButton;
    private TextView cancel;
    private LinearLayout ll_ticketManage_mine;
    private String isAuto = "";
    private String token;
    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_set, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        requestData();
        ll_shopname_mine.setOnClickListener(this);
        ll_shopinfo_mine.setOnClickListener(this);
        ll_feedback_mine.setOnClickListener(this);
        mToggleButton.setOnClickListener(this);
        cancel.setOnClickListener(this);
        ll_ticketManage_mine.setOnClickListener(this);
        //推送语音的控制 语音/震动
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    isAuto = "";
                } else {
                    isAuto = "1";
                }

                SharedPreferencesUtils.saveString(getActivity(), "isAuto", isAuto);

            }
        });
    }

    private void initView() {
        //EventBus
        EventBus.getDefault().register(this);
        img_mine = (ImageView) view.findViewById(R.id.img_mine);
        ll_shopname_mine = (LinearLayout) view.findViewById(R.id.ll_shopname_mine);
        tv_shopname_mine = (TextView) view.findViewById(R.id.tv_shopname_mine);
        ll_shopinfo_mine = (LinearLayout) view.findViewById(R.id.ll_shopinfo_mine);
        ll_feedback_mine = (LinearLayout) view.findViewById(R.id.ll_feedback_mine);
        mToggleButton = (ToggleButton) view.findViewById(R.id.ToggleButton);
        ll_ticketManage_mine = (LinearLayout) view.findViewById(R.id.ll_ticketManage_mine);
        cardView = (CardView) view.findViewById(R.id.cardView);
        cancel = (TextView) view.findViewById(R.id.cancel);
        //cardview设置
        cardView.setRadius(8);//设置图片圆角的半径大小
        cardView.setCardElevation(0);//设置阴影部分大小
        cardView.setContentPadding(0,0,0,0);//设置图片距离阴影大小
    }

    public void requestData() {
        token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        RequestManager.postJson().addParams("token", token)
                .setUrl(Url.findbytokenUrl)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderInfoBean>() {
                    @Override
                    public void onSuccess(OrderInfoBean baen) {
                        data = baen.getData();
                        tv_shopname_mine.setText(baen.getData().getShopSign());
                        Glide.with(getActivity()).load(baen.getData().getLogoImage()).into(img_mine);
                        img_mine.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onErr(String s) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //头像点击
            case R.id.ll_shopname_mine:
                bundle = new Bundle();
                bundle.putSerializable("ShopKey", (Serializable) data);
                intent = new Intent(getActivity(), ShopInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            //门店信息点击
            case R.id.ll_shopinfo_mine:
                bundle = new Bundle();
                bundle.putSerializable("ShopKey", (Serializable) data);
                intent = new Intent(getActivity(), ShopInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            //意见反馈点击
            case R.id.ll_feedback_mine:
                bundle = new Bundle();
                bundle.putSerializable("ShopKey", (Serializable) data);
                intent = new Intent(getActivity(), FeedBackActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            //退出登录
            case R.id.cancel:
                RequestManager.postJson().addParams("token", token)
                        .setUrl(Url.loginoutURL)
                        .builder().onUI()
                        .setCallback(new IRequestBeanListener<LoginBean>() {
                            @Override
                            public void onSuccess(LoginBean baen) {
                                if (baen.getStatus().equals("200")) {
                                    SharedPreferencesUtils.clear(getActivity());
                                    CommonUtils.startActivity(getActivity(), MainActivity.class);
                                    getActivity().finish();
                                }
                            }

                            @Override
                            public void onErr(String s) {
                            }
                        });

                break;
            //票机管理
            case R.id.ll_ticketManage_mine:
                CommonUtils.startActivity(getActivity(), TicketManageActivity.class);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventB(MessageEventB messageEvent) {
       String message = messageEvent.getMessage();
        if(message.equals("B")){
            requestData();
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
