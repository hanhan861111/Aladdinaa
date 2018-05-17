package com.aladdin.hxe.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.activity.ActivesetActivity;
import com.aladdin.hxe.activity.AddBankcardActivity;
import com.aladdin.hxe.activity.AlalysisActivity;
import com.aladdin.hxe.activity.ComingsoonActivity;
import com.aladdin.hxe.activity.DealinActivity;
import com.aladdin.hxe.activity.ManagefoodActivity;
import com.aladdin.hxe.activity.MessageActivity;
import com.aladdin.hxe.activity.ScanActivity;
import com.aladdin.hxe.activity.SettlementActivity;
import com.aladdin.hxe.activity.ShopInfoActivity;
import com.aladdin.hxe.activity.SidedishActivity;
import com.aladdin.hxe.activity.UserratingActivity;
import com.aladdin.hxe.adapter.GridView_Adapter;
import com.aladdin.hxe.bean.GoodsStatusBean;
import com.aladdin.hxe.bean.MessageEventB;
import com.aladdin.hxe.bean.MessageEventD;
import com.aladdin.hxe.bean.MessageEventE;
import com.aladdin.hxe.bean.MyBankBean;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.bean.OrderTodayTurnover;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;
import com.aladdin.hxe.utils.Url;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2018/1/23 0023 14:51..
 */

public class ManageFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View view;
    private GridView gv_manage;
    String textData[] = {"配菜助手", "菜品管理", "用户评价", "结算管理", "营销活动", "消息中心", "营业分析", "经营数据", "敬请期待"};
    int imgviewData[] = {R.mipmap.sidedish, R.mipmap.managefood, R.mipmap.userrating, R.mipmap.settlement, R.mipmap.activeset, R.mipmap.message, R.mipmap.alalysis, R.mipmap.dealin, R.mipmap.comingsoon};
    private LinearLayout shopinfo_manage;
    private TextView tvname_manage;
    private ImageView imgshop_manage;
    private ImageView imgshopstate_manage1;
    private ImageView imgshopstate_manage2;
    private ArrayList<String> textList;
    private ArrayList<Integer> imageList;
    private RelativeLayout rl_balance;
    private RelativeLayout rl_turnover;
    private RelativeLayout rl_order;
    private OrderInfoBean.DataBean data;
    private TextView tv_turnover;
    private String token;
    private String isPay = "2";
    private String orderStatus = "0";
    private int page = 1;
    private int size = 20;
    private String orderfeesum;
    private TextView tv_order;
    private TextView tv_scanSum;
    private String message;
    private int shopStutas;
    private RelativeLayout imgshopstate_manage;
    private CardView cardView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_manage, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView(view);
        initData();

    }

    private void initData() {
        GridView_Adapter gridView_adapter = new GridView_Adapter(getActivity(), textList, imageList);
        gv_manage.setAdapter(gridView_adapter);
        //点击事件
        imgshopstate_manage.setOnClickListener(this);
        shopinfo_manage.setOnClickListener(this);
        rl_balance.setOnClickListener(this);
        rl_order.setOnClickListener(this);
        rl_turnover.setOnClickListener(this);
        gv_manage.setOnItemClickListener(this);
        requestData();
        requestOrderData();
    }

    private void initView(View view) {
        //EventBus
        EventBus.getDefault().register(this);
        token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        textList = new ArrayList<>();
        imageList = new ArrayList<>();
        shopinfo_manage = (LinearLayout) view.findViewById(R.id.shopinfo_manage);
        tvname_manage = (TextView) view.findViewById(R.id.tvname_manage);
        imgshop_manage = (ImageView) view.findViewById(R.id.imgshop_manage);
        gv_manage = (GridView) view.findViewById(R.id.gv_manage);
        imgshopstate_manage = (RelativeLayout) view.findViewById(R.id.imgshopstate_manage);
        imgshopstate_manage1 = (ImageView) view.findViewById(R.id.imgshopstate_manage1);
        imgshopstate_manage2 = (ImageView) view.findViewById(R.id.imgshopstate_manage2);
        rl_balance = (RelativeLayout) view.findViewById(R.id.rl_balance);
        rl_turnover = (RelativeLayout) view.findViewById(R.id.rl_turnover);
        rl_order = (RelativeLayout) view.findViewById(R.id.rl_order);
        tv_turnover = (TextView) view.findViewById(R.id.tv_turnover);
        tv_order = (TextView) view.findViewById(R.id.tv_order);
        tv_scanSum = (TextView) view.findViewById(R.id.tv_scanSum);
        cardView = (CardView) view.findViewById(R.id.cardView);
        //cardview设置
        cardView.setRadius(8);//设置图片圆角的半径大小
        cardView.setCardElevation(0);//设置阴影部分大小
        cardView.setContentPadding(0,0,0,0);//设置图片距离阴影大小
        for (int i = 0; i < textData.length; i++) {
            textList.add(textData[i]);
            imageList.add(imgviewData[i]);

        }
    }

    //商户信息请求
    public void requestData() {
        RequestManager.postJson().addParams("token", token)
                .setUrl(Url.findbytokenUrl)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderInfoBean>() {
                    @Override
                    public void onSuccess(OrderInfoBean orderInfoBean) {
                        data = orderInfoBean.getData();
                        shopStutas = data.getShopStutas();
                        if (shopStutas == 1) {
                            imgshopstate_manage1.setVisibility(View.VISIBLE);
                            imgshopstate_manage2.setVisibility(View.GONE);

                        } else if (shopStutas == 2) {
                            imgshopstate_manage1.setVisibility(View.GONE);
                            imgshopstate_manage2.setVisibility(View.VISIBLE);
                        }
                        tvname_manage.setText(orderInfoBean.getData().getShopSign() + "");
                        Glide.with(getActivity()).load(orderInfoBean.getData().getLogoImage()).into(imgshop_manage);
                        imgshop_manage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onErr(String s) {
                    }
                });
    }

    //营业额数据请求
    public void requestOrderData() {
        RequestManager.postJson()
                .setUrl(Url.findtodayinfo)
                .addParams("token", token).addParams("orderStatus", orderStatus).addParams("createTime", TimeUtils.GetCurrentTime())
                .addParams("page", String.valueOf(page)).addParams("isPay", isPay)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderTodayTurnover>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(OrderTodayTurnover baen) {
                        if (baen.getStatus() != 200) {
                            return;
                        }
                        orderfeesum = String.valueOf(baen.getData().getOrderfeesum());
                        tv_order.setText(baen.getData().getOrdercount()+"");
                        String orderqrfeesum = baen.getData().getOrderqrfeesum();
                        String a = new DecimalFormat("###,###,###.##").format(Float.parseFloat(orderqrfeesum));
                        tv_turnover.setText(orderfeesum);
                        tv_scanSum.setText(a);
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    //商户信息修改请求
    public void requestDataUpdate(int shopStutas) {
        RequestManager.postJson().addParams("token", token)
                .addParams("shopStutas", String.valueOf(shopStutas))
                .setUrl(Url.appcommercialUpdate)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<GoodsStatusBean>() {
                    @Override
                    public void onSuccess(GoodsStatusBean bean) {
                        Toast.makeText(getActivity(), bean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErr(String s) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //门店详情
            case R.id.shopinfo_manage:
                Bundle bundle = new Bundle();
                bundle.putSerializable("ShopKey", (Serializable) data);
                Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            //营业图片
            case R.id.imgshopstate_manage:
                if (shopStutas == 1) {
                    imgshopstate_manage1.setVisibility(View.GONE);
                    imgshopstate_manage2.setVisibility(View.VISIBLE);
                    shopStutas = 2;
                    requestDataUpdate(shopStutas);
                } else if (shopStutas == 2) {
                    imgshopstate_manage1.setVisibility(View.VISIBLE);
                    imgshopstate_manage2.setVisibility(View.GONE);
                    shopStutas = 1;
                    requestDataUpdate(shopStutas);
                }
                break;
            //扫码收款
            case R.id.rl_balance:
                CommonUtils.startActivity(getActivity(), ScanActivity.class);
                break;
            //今日订单
            case R.id.rl_order:
                // CommonUtils.startActivity(getActivity(), TodayOrderActivity.class);
                break;
            //今日营业额
            case R.id.rl_turnover:
                //CommonUtils.startActivity(getActivity(), TurnoverActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            //配菜助手
            case 0:
                CommonUtils.startActivity(getActivity(), SidedishActivity.class);
                break;
            //菜品管理
            case 1:
                CommonUtils.startActivity(getActivity(), ManagefoodActivity.class);
                break;
            //用户评价
            case 2:
                CommonUtils.startActivity(getActivity(), UserratingActivity.class);
                break;
            //结算管理
            case 3:
                getBankInfo();
                break;
            //营销活动
            case 4:
                CommonUtils.startActivity(getActivity(), ActivesetActivity.class);
                break;
            //消息中心
            case 5:
                CommonUtils.startActivity(getActivity(), MessageActivity.class);
                break;
            //营业分析
            case 6:
                CommonUtils.startActivity(getActivity(), AlalysisActivity.class);
                break;
            //经营数据
            case 7:
                CommonUtils.startActivity(getActivity(), DealinActivity.class);
                break;
            //敬请期待
            case 8:
                CommonUtils.startActivity(getActivity(), ComingsoonActivity.class);
                break;
        }
    }
//银行卡信息
    private void getBankInfo() {
        RequestManager.postJson()
                .addParams("token", token)
                .setUrl(Url.findBankAccountURL)
                .builder().onUI().setCallback(new IRequestBeanListener<MyBankBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(MyBankBean baen) {
                if (baen.getStatus() != 200) {
                    return;
                } else if (baen.getData().size() > 0) {
                    //当前已绑定银行卡，跳转至显示编辑银行卡页面
                    Log.d("AAAAAAAAAAAAAA",baen.getData().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("baen", (Serializable) baen);
                    Intent intent = new Intent(getActivity(), SettlementActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else if (baen.getData().size() <= 0) {
                    //当前未绑定银行卡，跳转至添加银行卡页面
                    CommonUtils.startActivity(getActivity(), AddBankcardActivity.class);
                }


            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventD messageEvent) {
        message = messageEvent.getMessage();
        if (message.equals("NOTICE")) {
            requestOrderData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventB(MessageEventB messageEvent) {
        message = messageEvent.getMessage();
        if (message.equals("B")) {
            requestData();
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventE messageEvent) {
        requestOrderData();
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
