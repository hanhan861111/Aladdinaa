package com.aladdin.hxe.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.SearchBleAdapter;
import com.ysh.rn.printet.BluetoothController;
import com.ysh.rn.printet.base.AppInfo;
import com.ysh.rn.printet.bt.BluetoothActivity;
import com.ysh.rn.printet.bt.BtService;
import com.ysh.rn.printet.bt.BtUtil;
import com.ysh.rn.printet.print.PrintQueue;
import com.ysh.rn.printet.print.PrintUtil;
import com.ysh.rn.printet.util.ToastUtil;

import java.lang.reflect.Method;

public class BlueActivity extends BluetoothActivity implements BluetoothController.PrinterInterface, AdapterView.OnItemClickListener, View.OnClickListener {
    int PERMISSION_REQUEST_COARSE_LOCATION = 2;
    private ListView lv_searchblt;
    private ImageButton imb_back;
    private TextView tv_title;
    private BluetoothAdapter bluetoothAdapter;
    private SearchBleAdapter searchBleAdapter;
    private TextView tv_summary;
    BluetoothController bluetoothController;
    private String blueAddress;
    private TextView tv_text;
    private TextView tv_break;
    boolean mBtEnable = true;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);
        lv_searchblt = (ListView) findViewById(R.id.lv_searchblt);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_summary = (TextView) findViewById(R.id.tv_summary);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_break = (TextView) findViewById(R.id.tv_break);
        //6.0以上的手机要地理位置权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }

        bluetoothController = new BluetoothController(this);
        bluetoothController.setPrinterInterface(this);
        //初始化蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        searchBleAdapter = new SearchBleAdapter(BlueActivity.this, null);
        lv_searchblt.setAdapter(searchBleAdapter);
        init();
        searchDeviceOrOpenBluetooth();
        lv_searchblt.setOnItemClickListener(this);
        tv_title.setOnClickListener(this);
        tv_summary.setOnClickListener(this);
        tv_text.setOnClickListener(this);
        tv_break.setOnClickListener(this);
        imb_back.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bluetoothController.init();
    }

    @Override
    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
        bluetoothController.init();
    }


    private void init() {
        if (!BtUtil.isOpen(bluetoothAdapter)) {
            tv_title.setText("未连接蓝牙打印机");
            tv_summary.setText("系统蓝牙已关闭,点击开启");
        } else {
            if (!PrintUtil.isBondPrinter(this, bluetoothAdapter)) {
                //未绑定蓝牙打印机器
                tv_title.setText("未连接蓝牙打印机");
                tv_summary.setText("点击后搜索蓝牙打印机");

            } else {
                //已绑定蓝牙设备
                tv_title.setText(getPrinterName() + "已连接");
                blueAddress = PrintUtil.getDefaultBluethoothDeviceAddress(this);
                if (TextUtils.isEmpty(blueAddress)) {
                    blueAddress = "点击后搜索蓝牙打印机";
                }
                tv_summary.setText(blueAddress);
            }
        }
    }

    private String getPrinterName(){
        String dName = PrintUtil.getDefaultBluetoothDeviceName(this);
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }
    private String getPrinterName(String dName) {
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }

    /**
     * 开始搜索
     * search device
     */
    private void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(bluetoothAdapter)) {
            BtUtil.searchDevices(bluetoothAdapter);
        }
    }

    /**
     * 关闭搜索
     * cancel search
     */
    @Override
    protected void onStop() {
        super.onStop();
        BtUtil.cancelDiscovery(bluetoothAdapter);
    }
    @Override
    public void btStartDiscovery(Intent intent) {
        tv_title.setText("正在搜索蓝牙设备…");
        tv_summary.setText("");
    }

    @Override
    public void btFinishDiscovery(Intent intent) {
        tv_title.setText("搜索完成");
        tv_summary.setText("点击重新搜索");
    }
    @Override
    public void btFoundDevice(Intent intent) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        Log.d("1","!");
        if (null != bluetoothAdapter && device != null) {
            searchBleAdapter.addDevices(device);
            String dName = device.getName() == null ? "未知设备" : device.getName();
            Log.d("未知设备",dName);
            Log.d("1","!");
        }
    }

    @Override
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        switch (device.getBondState()) {
            case BluetoothDevice.BOND_BONDING://正在配对
                Log.d("BlueToothTestActivity", "正在配对......");
                break;
            case BluetoothDevice.BOND_BONDED://配对结束
                Log.d("BlueToothTestActivity", "完成配对");
                connectBlt(device);
                break;
            case BluetoothDevice.BOND_NONE://取消配对/未配对
                Log.d("BlueToothTestActivity", "取消配对");
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (null == searchBleAdapter) {
            return;
        }
        final BluetoothDevice bluetoothDevice = searchBleAdapter.getItem(position);
        if (null == bluetoothDevice) {
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("绑定" + getPrinterName(bluetoothDevice.getName()) + "?")
                .setMessage("点击确认绑定蓝牙设备")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            BtUtil.cancelDiscovery(bluetoothAdapter);


                            if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                                connectBlt(bluetoothDevice);

                            } else {
                                Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                                createBondMethod.invoke(bluetoothDevice);
                            }
                            PrintQueue.getQueue(getApplicationContext()).disconnect();
                            String name = bluetoothDevice.getName();

                        } catch (Exception e) {
                            e.printStackTrace();
                            PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), "");
                            PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), "");
                            ToastUtil.showToast(BlueActivity.this,"蓝牙绑定失败,请重试");
                        }
                    }
                })
                .create()
                .show();





    }

    /***
     * 配对成功连接蓝牙
     * @param bluetoothDevice
     */

    private void connectBlt(BluetoothDevice bluetoothDevice) {
        if (null != searchBleAdapter) {
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        init();
        searchBleAdapter.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), bluetoothDevice.getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_title:
                break;
            case R.id.tv_summary:
                //searchDeviceOrOpenBluetooth();
                break;
            case R.id.imb_back:
                finish();
            case R.id.tv_text:
                if (TextUtils.isEmpty(AppInfo.btAddress)) {
                    ToastUtil.showToast(BlueActivity.this, "请连接蓝牙...");
                } else {
                    ToastUtil.showToast(BlueActivity.this, "打印测试...");
                    intent2 = new Intent(getApplicationContext(), BtService.class);
                    intent2.setAction(PrintUtil.ACTION_PRINT_TEST_TWO);
                    startService(intent2);

                }
                break;
            case R.id.tv_break:

                break;
            default:
                break;
        }
    }

    @Override
    public void NoBT() {
        this.tv_title.setText("该设备没有蓝牙模块");
        this.mBtEnable = false;
    }

    @Override
    public void BT_NoOpen() {
        this.tv_title.setText("蓝牙未打开");
    }

    @Override
    public void BT_NoBind() {
        this.tv_title.setText("尚未绑定蓝牙设备");
    }

    @Override
    public void BT_Bind(String name, String address) {
//        if (TextUtils.isEmpty(AppInfo.btAddress)) {
//            tv_title.setText("123");
//        }else{
            this.tv_title.setText("已绑定蓝牙：" + name);
            this.tv_summary.setText(address);
//        }

    }

}
