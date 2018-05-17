package com.aladdin.hxe.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventB;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.OrderInfoBean;
import com.aladdin.hxe.bean.UrlBean;
import com.aladdin.hxe.utils.CommonUtils;
import com.aladdin.hxe.utils.PhoneUtils;
import com.aladdin.hxe.utils.PictureUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.aladdin.hxe.utils.WeiboDialogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_shoptitle;
    private EditText et_shopphone;
    private EditText et_shopname;
    private EditText et_shopaddress;
    private EditText et_shopnotice;
    private ImageButton img_back;
    private static final int SUCCESSCODE = 100;
    private static final int REQ_GALLERY = 333;
    private static final int REQUEST_CODE_PICK_IMAGE = 222;
    private String mPublicPhotoPath;
    private Button bt_submit;
    private String url;
    private Dialog mWeiboDialog;
    private OrderInfoBean.DataBean dataBean;
    private String userId;
    private String token;
    private String shopaddress;
    private String shopname;
    private String shopnotice;
    private String shopphone;
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        img_back.setOnClickListener(this);
        img_shoptitle.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
    }

    private void initView() {
        //EventBus
        EventBus.getDefault().register(this);
        token = SharedPreferencesUtils.getString(this, "token", "");
        userId = SharedPreferencesUtils.getString(this, "userId", "");
        img_shoptitle = (ImageView) findViewById(R.id.img_shoptitle);
        et_shopphone = (EditText) findViewById(R.id.et_shopphone);
        et_shopname = (EditText) findViewById(R.id.et_shopname);
        et_shopaddress = (EditText) findViewById(R.id.et_shopaddress);
        et_shopnotice = (EditText) findViewById(R.id.et_shopnotice);
        img_back = (ImageButton) findViewById(R.id.img_back);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        et_shopphone.setSelection(et_shopphone.getText().length());//将光标移至文字末尾
        cardView = (CardView) findViewById(R.id.cardView);
        //cardview设置
        cardView.setRadius(8);//设置图片圆角的半径大小
        cardView.setCardElevation(0);//设置阴影部分大小
        cardView.setContentPadding(0,0,0,0);//设置图片距离阴影大小
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        dataBean = (OrderInfoBean.DataBean) bundle.getSerializable("ShopKey");
        CommonUtils.runOnUIthread(new Runnable() {
            @Override
            public void run() {
                Glide.with(ShopInfoActivity.this).load(dataBean.getLogoImage()).into(img_shoptitle);
                img_shoptitle.setScaleType(ImageView.ScaleType.CENTER_CROP);
                et_shopname.setText(dataBean.getShopSign());
                et_shopaddress.setText(dataBean.getAddress());
                et_shopphone.setText(dataBean.getPhone());
                et_shopnotice.setText(dataBean.getData3());

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.img_back:
                finish();
                break;
            //确认提交
            case R.id.bt_submit:
                shopaddress = et_shopaddress.getText().toString().trim();
                shopname = et_shopname.getText().toString().trim();
                shopnotice = et_shopnotice.getText().toString().trim();
                shopphone = et_shopphone.getText().toString().trim();
                //根据电话号码是否更改，判断是否调用修改电话接
                if (PhoneUtils.isMobileNO(shopphone)) {
                    if (dataBean.getPhone().equals(shopphone)) {
                        //修改店铺信息
                        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(ShopInfoActivity.this, "上传中...");
                        upload();
                    } else {
                        //获得验证码
                        getidentifyingCode(shopphone);
                    }
                } else {
                    Toast.makeText(ShopInfoActivity.this, "请核对手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            //修改图片
            case R.id.img_shoptitle:
                upLoadImg();
                break;
            default:
                break;
        }
    }

    private void getidentifyingCode(final String shopphone) {
        RequestManager.postJson()
                .addParams("phone",shopphone)
                .setUrl(Url.smsURL)
                .builder().onUI()
                .setCallback(new IRequestBeanListener<LoginBean>() {
                    @Override
                    public void onErr(String s) {

                    }

                    @Override
                    public void onSuccess(LoginBean baen) {
                        if("200".equals(baen.getStatus())){
                            //跳转弹窗
//                            CommonUtils.startActivity(ShopInfoActivity.this, UpdatePhoneActivity.class);
                            Intent intent=new Intent(ShopInfoActivity.this, UpdatePhoneActivity.class);
                            intent.putExtra("shopphone",shopphone);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void upLoadImg() {
        View popView = View.inflate(this, R.layout.popupwindow_camera_need, null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels * 5 / 6;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        popupWindow.isShowing();
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);
        //调用相册
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromAlbum();
                popupWindow.dismiss();
            }
        });
        //调用相机
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTakePicture();
                popupWindow.dismiss();
            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
    }

    /**
     * 获取相册中的图片
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    //拍照的功能
    private void showTakePicture() {
        PermissionGen.with(ShopInfoActivity.this)
                .addRequestCode(SUCCESSCODE)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request();
    }

    //权限申请
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //权限申请成功
    @PermissionSuccess(requestCode = SUCCESSCODE)
    public void doSomething() {
        //申请成功
        startTake();
    }

    //申请失败
    @PermissionFail(requestCode = SUCCESSCODE)
    public void doFailSomething() {
    }

    private void startTake() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //创建临时图片文件
            File photoFile = null;
            try {
                photoFile = PictureUtils.createPublicImageFile();
                mPublicPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置Action为拍照
            if (photoFile != null) {
                takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                //这里加入flag
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri photoURI = FileProvider.getUriForFile(this, "applicationId.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQ_GALLERY);
            }
        }
    }

    private Uri uri;
    String path;
    int mTargetW;
    int mTargetH;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTargetW = img_shoptitle.getWidth();
        mTargetH = img_shoptitle.getHeight();

        switch (requestCode) {
            //拍照
            case REQ_GALLERY:
                if (resultCode != Activity.RESULT_OK) return;
                uri = Uri.parse(mPublicPhotoPath);
                path = uri.getPath();
                PictureUtils.galleryAddPic(mPublicPhotoPath, this);

                break;
            //获取相册的图片
            case REQUEST_CODE_PICK_IMAGE:
                if (data == null) return;
                uri = data.getData();
                int sdkVersion = Integer.valueOf(Build.VERSION.SDK);
                if (sdkVersion >= 19) {
                    path = this.uri.getPath();
                    path = PictureUtils.getPath_above19(ShopInfoActivity.this, this.uri);
                } else {
                    path = PictureUtils.getFilePath_below19(ShopInfoActivity.this, this.uri);
                }
                break;
        }
        img_shoptitle.setImageBitmap(PictureUtils.getSmallBitmap(path, mTargetW, mTargetH));
        img_shoptitle.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    //上传图片
    private void upload() {
        String imageType = "multipart/form-data";
        //修改菜品时候的不改图片的状态
        if (path == null) {
            initPost();
        } else {
            File uploadFile = new File(path);
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), uploadFile);
            final RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("Content-Type", "multipart/form-data")
                    .addFormDataPart("uploadFile", "aa.jpg", fileBody)
                    .addFormDataPart("imagetype", imageType)
                    .build();
            final Request request = new Request.Builder()
                    .url(Url.upload)
                    .post(requestBody)
                    .build();
            final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = httpBuilder
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Reader reader = response.body().charStream();
                    UrlBean urlbean = new Gson().fromJson(reader, UrlBean.class);
                    url = urlbean.getUrl();
                    //上传商店信息
                    initPost();
                }

            });
        }
    }

    private void initPost() {
        RequestManager.postJson()
                .addParams("address", shopaddress)
                .addParams("data3", shopnotice)
                .addParams("id", userId)
                .addParams("logoImage", url)
                .addParams("shopSign", shopname)
                .addParams("token", token).setUrl(Url.updateShopInfoURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if (baen.getStatus().equals("200")) {
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    EventBus.getDefault().post(new MessageEventB("B"));
                    Toast.makeText(ShopInfoActivity.this, "修改商户信息成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ShopInfoActivity.this, "修改商户信息失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventC messageEvent) {
       String message = messageEvent.getMessage();
        if (message.equals("PHONE")) {
            upload();
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
