package com.aladdin.hxe.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.NewOrderAdapter;
import com.aladdin.hxe.bean.CallNumBean;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventD;
import com.aladdin.hxe.bean.NewOrderBean;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.bean.StatusBean;
import com.aladdin.hxe.holder.NewOrderMainHolder;
import com.aladdin.hxe.interfaces.OnItemClickListener;
import com.aladdin.hxe.interfaces.OnItemLongClickListener;
import com.aladdin.hxe.manager.SwipeItemLayout;
import com.aladdin.hxe.utils.PopupWindowUtil;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.TimeUtils;
import com.aladdin.hxe.utils.Url;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${韩永光} on on 2018/1/24 0024 10:50..
 */

public class NewOrderFragment extends Fragment implements XRecyclerView.LoadingListener, View.OnClickListener {
    private View view;
    private ToggleButton mToggleButton;
    private int page = 1;
    private int size = 20;
    private String orderStatus = "0";
    private XRecyclerView mRecyclerView;
    private ArrayList<NewOrderBean.DataBean.RowsBean> listAll = new ArrayList<>();
    private NewOrderAdapter myAdapter = new NewOrderAdapter(getActivity());
    private int total;
    private boolean isChecked = false;
    private TextView tv_callNum;
    private List<NewOrderBean.DataBean.RowsBean.OrdersProductsBean> ordersProducts;
    private NewOrderBean.DataBean.RowsBean rowsBean;
    private TextView tv_accept;
    private TextView tv_refuse;
    private LinearLayout ll_call;
    private String orderNum;
    private int getTb_customer_id;
    private String token;
    private int isAuto = 1;
    private String message;
    private List<NewOrderBean.DataBean.RowsBean> rows;
    private PopupWindow popupWindow;
    public CountDownTimer countDownTimer;
    NewOrderMainHolder newOrderMainHolder;
    List<CountDownTimer> countDownTimers;
    public List<Boolean> IsTimeStart;//用来确定是否已经开始计算时间,即是否进行了点击
    public List<Boolean> IsTimeGone;//用来确定是否已经结束计算时间,即点击后时间是否已经结束



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_neworder, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initData();


    }

    private void initData() {
        getStutas();
    }

    private void initSwitchButton(int isAuto) {
        if (isAuto == 1) {
            mToggleButton.setChecked(true);
        } else if (isAuto == 2) {
            mToggleButton.setChecked(false);
        }

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    NewOrderFragment.this.isAuto = 1;
                    initAutoOrder(NewOrderFragment.this.isAuto);
                } else {
                    NewOrderFragment.this.isAuto = 2;
                    initAutoOrder(NewOrderFragment.this.isAuto);
                }


            }
        });

        //recyclerView条目点击事件
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {
                //用户订单信息ID  orderNum
                rowsBean = listAll.get(position);
                ordersProducts = listAll.get(position).getOrdersProducts();
                final LinearLayout order_hide = (LinearLayout) view.findViewById(R.id.order_hide);
                ll_call = (LinearLayout) view.findViewById(R.id.ll_call);
                tv_refuse = (TextView) view.findViewById(R.id.tv_refuse);
                tv_accept = (TextView) view.findViewById(R.id.tv_accept);
                tv_callNum = (TextView) view.findViewById(R.id.tv_callNum);
                ll_call.setOnClickListener(NewOrderFragment.this);
                tv_refuse.setOnClickListener(NewOrderFragment.this);
                tv_accept.setOnClickListener(NewOrderFragment.this);
                tv_callNum.setOnClickListener(NewOrderFragment.this);

                ListView lv_orderproducts = (ListView) view.findViewById(R.id.lv_orderproducts);
                lv_orderproducts.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return ordersProducts == null ? 0 : ordersProducts.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder holder;
                        if (convertView == null) {
                            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.orderinfo_item, null);
                            holder = new ViewHolder();
                            holder.tv_goodsName = (TextView) convertView.findViewById(R.id.tv_goodsName);
                            holder.tv_goodsCount = (TextView) convertView.findViewById(R.id.tv_goodsCount);
                            holder.tv_goodsPrice = (TextView) convertView.findViewById(R.id.tv_goodsPrice);
                            convertView.setTag(holder);
                        } else {
                            holder = (ViewHolder) convertView.getTag();
                        }
                        holder.tv_goodsName.setText(ordersProducts.get(position).getGoodsName());
                        holder.tv_goodsCount.setText("X" + ordersProducts.get(position).getGoodsNum() + "");
                        holder.tv_goodsPrice.setText(ordersProducts.get(position).getGoodsPrice() + "元");
                        return convertView;
                    }
                });
                //动态设置listView的高度
                ListAdapter listAdapter = lv_orderproducts.getAdapter();
                if (listAdapter == null) {
                    return;
                }
                int totalHeight = 0;
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    View listItem = listAdapter.getView(i, null, lv_orderproducts);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }
                ViewGroup.LayoutParams params = lv_orderproducts.getLayoutParams();
                params.height = totalHeight + (lv_orderproducts.getDividerHeight() * (listAdapter.getCount() - 1));
                lv_orderproducts.setLayoutParams(params);
                if (isChecked) {
                    order_hide.setVisibility(View.GONE);
                    isChecked = false;
                } else {
                    order_hide.setVisibility(View.VISIBLE);
                    isChecked = true;
                }

            }
        });
        //长按删除
        myAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView order_status = (TextView) view.findViewById(R.id.order_status);
                String orderNum = listAll.get(position).getOrderNum();
                popWindow(order_status, position, orderNum);
            }
        });
    }

    private void popWindow(View view1, int position, String orderNum) {
        View contentView = getPopupWindowContentView(position, orderNum);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(view1, contentView);
        int xOff = 20; // 可以自己调整偏移
        windowPos[0] -= xOff;
        popupWindow.showAtLocation(view1, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    /*存放控件*/
    public final class ViewHolder {
        public TextView tv_goodsName;
        public TextView tv_goodsCount;
        public TextView tv_goodsPrice;
    }

    private void initView(View view) {
        //EventBus
        EventBus.getDefault().register(this);
        token = SharedPreferencesUtils.getString(getActivity(), "token", "");
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        mToggleButton = (ToggleButton) view.findViewById(R.id.ToggleButton);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        //设置适配器
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        mRecyclerView.getDefaultFootView().setLoadingHint("订单加载中");
        mRecyclerView.getDefaultFootView().setNoMoreHint("所有订单加载完毕");
        /**
         * 设置列表下拉刷新、上拉加载的监听
         */
        mRecyclerView.setLoadingListener(this);
        //设置允许下拉刷新
        mRecyclerView.setPullRefreshEnabled(true);
        //设置允许上拉加载
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.refresh();
        //倒计时设置
    }

    @Override
    public void onRefresh() {
        page = 1;
        requestData(true);
    }

    @Override
    public void onLoadMore() {
        page++;
        requestData(false);
    }

    /**
     * 请求数据
     *
     * @param isRefrsh:标记是刷新还是加载
     */
    public void requestData(final Boolean isRefrsh) {
        RequestManager.postJson()
                .setUrl(Url.findOrdersByStatus)
                .addParams("token", token)
                .addParams("size", String.valueOf(size))
                .addParams("startTime", TimeUtils.getBeforeTime())
                .addParams("endTime", TimeUtils.getNextTime())
                .addParams("page", String.valueOf(page))
                .builder().onUI()
                .setCallback(new IRequestBeanListener<NewOrderBean>() {
                    @Override
                    public void onSuccess(NewOrderBean baen) {
                        total = baen.getData().getTotal();
                        rows = baen.getData().getRows();
                        //如果是刷新就清楚之前加载的数据
                        if (isRefrsh) {
                            listAll.clear();
                        }
                        listAll.addAll(rows);
                        builderAdapter(listAll, isRefrsh);
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    /**
     * 刷新Adapter
     */
    private void builderAdapter(ArrayList<NewOrderBean.DataBean.RowsBean> listAll, Boolean isRefrsh) {
        myAdapter.setDatas(listAll);
        //模拟 表示服务器响应数据已经请求完了，可以屏蔽上拉加载操作
        if (isRefrsh) {
            //表示刷新完成
            mRecyclerView.refreshComplete();
            mRecyclerView.setLoadingMoreEnabled(true);
        } else {
            //表示加载完成
            mRecyclerView.loadMoreComplete();
        }
        if (total <= listAll.size()) {
            mRecyclerView.setLoadingMoreEnabled(false);
        } else {
            mRecyclerView.setLoadingMoreEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        orderNum = rowsBean.getOrderNum();
        getTb_customer_id = (int) rowsBean.getTb_customer_id();
        switch (v.getId()) {
            //联系商家
            case R.id.ll_call:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + rowsBean.getPhone());
                intent.setData(data);
                startActivity(intent);

                break;
            //拒单
            case R.id.tv_refuse:
                //实例化布局
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_alertdialog, null);
                //找到并对自定义布局中的控件进行操作的示例
                final EditText et_alertDialog = (EditText) view.findViewById(R.id.et_alertDialog);
                //创建对话框
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                //dialog.setIcon(R.mipmap.ic_launcher);//设置图标
                dialog.setTitle("拒单原因");//设置标题
                dialog.setView(view);//添加布局
                //设置按键
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "提交", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String orderStatusMsg = et_alertDialog.getText().toString().trim();
                                initoRefundOrder(orderNum, orderStatus, token, orderStatusMsg);

                            }
                        }

                );
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }

                );
                dialog.show();
                break;
            //接单
            case R.id.tv_accept:
                tv_callNum.setVisibility(View.VISIBLE);

                initAcceptOrder(orderNum, orderStatus, token);

                break;
            //叫号
            case R.id.tv_callNum:
                initPostCallNum(getTb_customer_id, orderNum, token);
                break;
            default:
                break;
        }
    }

    //拒单接口

    private void initoRefundOrder(String orderNum, String orderStatus, String token, String orderStatusMsg) {
        RequestManager.postJson()
                .setUrl(Url.updatebyorderstatus).addParams("orderStatus", String.valueOf(3))
                .addParams("token", token).addParams("orderNum", orderNum)
                .addParams("orderStatusMsg", orderStatusMsg)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<StatusBean>() {
                    @Override
                    public void onSuccess(StatusBean baen) {
                        Toast.makeText(getActivity(), "拒单成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    //接单接口
    private void initAcceptOrder(String orderNum, String orderStatus, String token) {
        RequestManager.postJson()
                .setUrl(Url.updatebyorderstatus).addParams("orderStatus", String.valueOf(2))
                .addParams("token", token).addParams("orderNum", orderNum)
                .addParams("orderStatusMsg", "")
                .builder().onUI()
                .setCallback(new IRequestBeanListener<StatusBean>() {
                    @Override
                    public void onSuccess(StatusBean baen) {

                        Toast.makeText(getActivity(), "接单成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    //叫号接口
    private void initPostCallNum(int getSys_user_id, String orderNum, String token) {
        RequestManager.postJson()
                .setUrl(Url.orderremind).addParams("id", String.valueOf(getSys_user_id))
                .addParams("token", token).addParams("orderNum", orderNum).addParams("msg", "您做的饭好啦H !")
                .builder().onUI()
                .setCallback(new IRequestBeanListener<CallNumBean>() {
                    @Override
                    public void onSuccess(final CallNumBean baen) {
                        Toast.makeText(getActivity(), "叫号成功", Toast.LENGTH_SHORT).show();
//                        Message message = handler.obtainMessage(1);     // Message
//                        handler.sendMessageDelayed(message, 1000);
                        /** 倒计时60秒，一次1秒 */
//                        CountDownTimer timer = new CountDownTimer(10 * 1000, 1000) {
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//                                // TODO Auto-generated method stub
//                                tv_callNum.setText("还剩" + millisUntilFinished / 1000 + "秒");
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                tv_callNum.setText("叫号");
//                                tv_callNum.setEnabled(true);
//                                tv_callNum.setBackgroundColor(0xff12cb77);
//                            }
//                        }.start();
//
                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    //自动接单接单接口
    private void initAutoOrder(int isAuto) {
        RequestManager.postJson()
                .setUrl(Url.appcommercialUpdate)
                .addParams("token", token).addParams("isAuto", String.valueOf(isAuto))
                .builder().onUI()
                .setCallback(new IRequestBeanListener<StatusBean>() {
                    @Override
                    public void onSuccess(StatusBean baen) {


                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventD messageEvent) {
        message = messageEvent.getMessage();
        if (message.equals("NOTICE")) {
            onRefresh();
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

    public void getStutas() {
        RequestManager.postJson().addParams("token", token)
                .setUrl(Url.findbytokenUrl)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<OrderInfoBean>() {
                    @Override
                    public void onSuccess(OrderInfoBean baen) {
                        int isAuto = baen.getData().getIsAuto();
                        initSwitchButton(isAuto);
                    }

                    @Override
                    public void onErr(String s) {
                    }
                });
    }

    private View getPopupWindowContentView(int position, final String orderNum) {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.item_popwindow;   // 布局ID
        View contentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_delete:
                        RequestManager.postJson()
                                .addParams("token", token)
                                .addParams("orderNum", orderNum)
                                .addParams("orderStatusMsg", "")
                                .addParams("orderStatus", String.valueOf(6))
                                .setUrl(Url.updatebyorderstatusURL)
                                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
                            @Override
                            public void onErr(String s) {

                            }

                            @Override
                            public void onSuccess(LoginBean baen) {
                                if (baen.getStatus().equals("200")) {
                                    Toast.makeText(getActivity(), "移除成功", Toast.LENGTH_SHORT).show();
                                    onRefresh();
                                } else {
                                    Toast.makeText(getActivity(), "移除失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case R.id.tv_cancel:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.tv_delete).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(menuItemOnClickListener);
        return contentView;
    }
}
