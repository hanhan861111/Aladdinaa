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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aladdin.common.net.RequestManager;
import com.aladdin.common.net.listener.IRequestBeanListener;
import com.aladdin.hxe.R;
import com.aladdin.hxe.adapter.TestArrayAdapterA;
import com.aladdin.hxe.bean.GoodsStatusBean;
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.bean.UrlBean;
import com.aladdin.hxe.utils.PictureUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.aladdin.hxe.utils.WeiboDialogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
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

public class UpdateFoodActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;
    private ImageButton imb_back;
    private TextView tv_updateSave;
    private EditText et_updateName;
    private EditText et_updatePrice;
    private ImageView img_update;
    private int position;
    private List<ProductList.DataBean.ProductBean> mRightListDatas;
    private List<ProductList.DataBean> data;
    ArrayList<String> nameList = new ArrayList<>();
    private TestArrayAdapterA testArrayAdapterA;
    private static final int SUCCESSCODE = 100;
    private static final int REQ_GALLERY = 333;
    private static final int REQUEST_CODE_PICK_IMAGE = 222;
    private String mPublicPhotoPath;
    private String token;
    private String et_name;
    private String et_price;
    private ArrayList<Integer> listID = new ArrayList<>();
    private String categoryName;
    private int id;
    private TextView tv_delete;
    private int deleteID;
    private String url;
    private Dialog mWeiboDialog;
    private EditText et_youhuiPrice;
    private String et_youhuiPrice1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);
        initView();
        //初始化数据
        initData();
        //页面初始数据设置
        setData();
        initEvent();
    }

    private void initEvent() {
        imb_back.setOnClickListener(this);
        img_update.setOnClickListener(this);
        tv_updateSave.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    private void setData() {
        et_youhuiPrice.setText(mRightListDatas.get(position).getSellPrice());
        et_updateName.setText(mRightListDatas.get(position).getName());
        et_updatePrice.setText(mRightListDatas.get(position).getActivityPrice());
        Glide.with(this).load(mRightListDatas.get(position).getImageUrl()).into(img_update);
        img_update.setScaleType(ImageView.ScaleType.CENTER_CROP);

    }


    private void initData() {
        requestData();
        position = getIntent().getIntExtra("position", 0);
        mRightListDatas = (List<ProductList.DataBean.ProductBean>) getIntent().getSerializableExtra("mRightListDatas");
        deleteID = mRightListDatas.get(position).getId();
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_updateSave = (TextView) findViewById(R.id.tv_updateSave);
        et_updateName = (EditText) findViewById(R.id.et_updateName);
        et_updatePrice = (EditText) findViewById(R.id.et_updatePrice);
        img_update = (ImageView) findViewById(R.id.img_update);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        et_youhuiPrice = (EditText) findViewById(R.id.et_youhuiPrice);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //修改图片
            case R.id.img_update:
                upLoadImg();
                break;
            //保存
            case R.id.tv_updateSave:
                et_name = et_updateName.getText().toString().trim();
                et_price = et_updatePrice.getText().toString().trim();
                et_youhuiPrice1 = et_youhuiPrice.getText().toString().trim();
                if(("").equals(et_youhuiPrice1)){
                    et_youhuiPrice.setText(et_price);
                    et_youhuiPrice1 = et_youhuiPrice.getText().toString().trim();
                }
                if ( et_name.equals("") || et_price.equals("")) {
                    Toast.makeText(UpdateFoodActivity.this, "上架商品失败，菜名、价格等信息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(UpdateFoodActivity.this, "上传中...");
                //上传菜品
                upload();
                break;
            //返回
            case R.id.imb_back:
                finish();
                break;
            //删除
            case R.id.tv_delete:
                initDelete();
                break;
            default:
                break;
        }
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

    public void requestData() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        RequestManager.postJson().addParams("token", token).
                setUrl(Url.findallallUrl).builder().onUI().
                setCallback(new IRequestBeanListener<ProductList>() {

                    @Override
                    public void onErr(String s) {
                    }

                    @Override
                    public void onSuccess(final ProductList productList) {
                        data = productList.getData();
                        for (int i = 0; i < data.size(); i++) {
                            nameList.add(data.get(i).getName());
                            listID.add(data.get(i).getId());

                        }
                        //使用自定义的ArrayAdapter
                        testArrayAdapterA = new TestArrayAdapterA(UpdateFoodActivity.this, nameList);
                        //设置下拉列表风格(这句不些也行)
                        // testArrayAdapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(testArrayAdapterA);
                        //监听Item选中事件
                        spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
                    }
                });

    }

    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            if (nameList.size() > 0) {
                categoryName = nameList.get(position).toString();
                id = listID.get(position);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

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

                    Log.d("DDD", "失败" + e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Reader reader = response.body().charStream();
                    UrlBean urlbean = new Gson().fromJson(reader, UrlBean.class);
                    url = urlbean.getUrl();
                    //上传菜品
                    initPost();
                }

            });
        }
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
        PermissionGen.with(UpdateFoodActivity.this)
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
        mTargetW = img_update.getWidth();
        mTargetH = img_update.getHeight();

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
                    path = PictureUtils.getPath_above19(UpdateFoodActivity.this, this.uri);
                } else {
                    path = PictureUtils.getFilePath_below19(UpdateFoodActivity.this, this.uri);
                }
                break;
        }
        img_update.setImageBitmap(PictureUtils.getSmallBitmap(path, mTargetW, mTargetH));
        img_update.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void initPost() {
        RequestManager.postJson().addParams("tb_category_categoryId", String.valueOf(id))
                .addParams("name", et_name)
                .addParams("activityPrice", et_price)
                .addParams("sellPrice",et_youhuiPrice1)
                .addParams("imageUrl", url)
                .addParams("id", String.valueOf(deleteID))
                .addParams("token", token).setUrl(Url.update)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if (baen.getStatus().equals("200")) {
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    EventBus.getDefault().post(new MessageEventC("C"));
                    Toast.makeText(UpdateFoodActivity.this, "修改菜品成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateFoodActivity.this, "修改菜品失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initDelete() {
        RequestManager.postJson().addParams("token", token).
                addParams("id", String.valueOf(deleteID)).
                setUrl(Url.deleteURL).builder().onUI().
                setCallback(new IRequestBeanListener<GoodsStatusBean>() {
                    @Override
                    public void onErr(String s) {

                    }

                    @Override
                    public void onSuccess(GoodsStatusBean baen) {
                        if (baen.getStatus() == 200) {
                            EventBus.getDefault().post(new MessageEventC("C"));
                            Toast.makeText(UpdateFoodActivity.this, "删除菜品成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}
