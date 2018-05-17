package com.aladdin.hxe.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.VerSionCodeBean;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.aladdin.hxe.utils.WeiboDialogUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.aladdin.hxe.utils.PhoneUtils.isMobileNO;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText etphone_login;
    private EditText pass_login;
    private ImageButton imb_login;
    private String token;
    private Dialog mWeiboDialog;
    private ImageView img_eye;
    private Boolean canSee = true;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        //版本检测
        initVersion();
        //初始化控件
        initView();
        //初始化数据
        initEvent();

    }

    private void initVersion() {
        getFWQ();

    }

    private void getFWQ() {
        RequestManager.get().setUrl("http://51hxe.com:9002/appversion/findnew/1")
                .builder().onUI().setCallback(new IRequestBeanListener<VerSionCodeBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(VerSionCodeBean baen) {
                if (baen.getStatus() == 200) {
                    //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
                    url = baen.getData().getUrl();
                    try {
                        float v = Float.parseFloat(baen.getData().getVersionid()) - getVersionCode();
                        Log.d("WWWWWWWW", v + "");
                        if (v >= 1) {
                            //强制更新
                            showDialogUpdateMust();

                        } else if (0 < v && v < 1) {
                            //弹出提示版本更新的对话框
                            showDialogUpdate();
                        } else {
                            //否则吐司，说现在是最新的版本
                            //Toast.makeText(MainActivity.this, "当前已经是最新的版本", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initEvent() {
        img_eye.setOnClickListener(this);
        imb_login.setOnClickListener(this);

    }

    private void getLoginData(final String phoneNumber, final String pass) {
        RequestManager.postJson().addParams("password", pass)
                .addParams("phone", phoneNumber)
                .setUrl(Url.loginUrl)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean baen) {
                        token = (String) baen.getData();
                        if (baen.getStatus().equals("200")) {
                            com.aladdin.hxe.utils.SharedPreferencesUtils.saveString(MainActivity.this, "token", token);
                            com.aladdin.hxe.utils.SharedPreferencesUtils.saveString(MainActivity.this, "phoneNumber", phoneNumber);
                            com.aladdin.hxe.utils.SharedPreferencesUtils.saveString(MainActivity.this, "pass", pass);
                            WeiboDialogUtils.closeDialog(mWeiboDialog);
                            CommonUtils.startActivity(MainActivity.this, HomeActivity.class);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                            mWeiboDialog.dismiss();

                        }

                    }

                    @Override
                    public void onErr(String s) {

                    }
                });
    }


    private void initView() {
        String token = SharedPreferencesUtils.getString(this, "token", "");
        String phoneNumber = SharedPreferencesUtils.getString(this, "phoneNumber", "");
        String pass = SharedPreferencesUtils.getString(this, "pass", "");
        etphone_login = (EditText) findViewById(R.id.etphone_login);
        pass_login = (EditText) findViewById(R.id.pass_login);
        pass_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imb_login = (ImageButton) findViewById(R.id.imb_login);
        img_eye = (ImageView) findViewById(R.id.img_eye);
        if (!"".equals(token)) {
            etphone_login.setText(phoneNumber);
            pass_login.setText(pass);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_eye:
                //通过全局的一个变量的设置，这个就是判断控件里面的内容是不是能被看到
                if (canSee == false) {
                    //如果是不能看到密码的情况下，
                    pass_login.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    img_eye.setImageResource(R.drawable.eyeopen);
                    canSee = true;
                } else {
                    //如果是能看到密码的状态下
                    pass_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    img_eye.setImageResource(R.drawable.eyeclose);
                    canSee = false;
                }
                break;
            case R.id.imb_login:
                final String phoneNumber = etphone_login.getText().toString().trim();
                final String pass = pass_login.getText().toString().trim();
                if ("".equals(phoneNumber) || "".equals(pass)) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("手机号码或登录密码不能为空").setCancelable(true).show();
                } else if (isMobileNO(phoneNumber) == false) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
                } else if (!"".equals(phoneNumber) && !"".equals(pass) && isMobileNO(phoneNumber) == true) {
                    mWeiboDialog = WeiboDialogUtils.createLoadingDialog(MainActivity.this, "登录中...");
                    getLoginData(phoneNumber, pass);
                }
                break;
            default:
                break;
        }
    }

    /*
     * 获取当前程序的版本号
     */
    private float getVersionCode() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        Log.e("rrrrrrrrr", "版本号" + packInfo.versionCode);
        Log.e("rrrrrrrrrrr", "版本名" + packInfo.versionName);
        return Float.parseFloat(packInfo.versionName);
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.ic_launcher).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog. setCancelable(false);
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 提示强制版本更新的对话框
     */
    private void showDialogUpdateMust() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.ic_launcher).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                });
        // 生产对话框
        AlertDialog alertDialog = builder.create();
        //设置强制更新 点击无效果
        alertDialog. setCancelable(false);
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(MainActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {

                    File file = getFileFromServer(url, pd);

                    sleep(1000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    //Toast.makeText(getApplicationContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    Log.d("NNN", e.getMessage() + "111" + e.getMessage().toString() + "");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)

        {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "applicationId.fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else

        {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        startActivity(intent);
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    public void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}