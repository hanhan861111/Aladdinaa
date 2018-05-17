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
import com.aladdin.hxe.bean.LoginBean;
import com.aladdin.hxe.bean.MessageEventC;
import com.aladdin.hxe.bean.ProductList;
import com.aladdin.hxe.bean.UrlBean;
import com.aladdin.hxe.utils.PictureUtils;
import com.aladdin.hxe.utils.SharedPreferencesUtils;
import com.aladdin.hxe.utils.Url;
import com.aladdin.hxe.utils.WeiboDialogUtils;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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

public class AddFoodActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton imb_back;
    private TextView tv_addSave;
    private ImageView img_add;
    private EditText et_addName;
    private EditText et_addPrice;
    private Spinner spinner;
    private static final int SUCCESSCODE = 100;
    private static final int REQ_GALLERY = 333;
    private static final int REQUEST_CODE_PICK_IMAGE = 222;
    private String mPublicPhotoPath;
    private String name;
    private String price;
    private String token;
    ArrayList<String> nameList = new ArrayList<>();
    private String categoryName;
    private ArrayList<Integer> categoryIDList = new ArrayList<>();
    private Integer categoryID;
    private Dialog mWeiboDialog;
    private GoogleApiClient client;
    private EditText et_youhuiPrice;
    private String et_youhuiPrice1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        initView();
        initEvent();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initEvent() {
        requestData();
        //按钮点击事件
        imb_back.setOnClickListener(this);
        tv_addSave.setOnClickListener(this);
        img_add.setOnClickListener(this);
    }

    private void initView() {
        token = SharedPreferencesUtils.getString(this, "token", "");
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        tv_addSave = (TextView) findViewById(R.id.tv_addSave);
        img_add = (ImageView) findViewById(R.id.img_add);
        et_addName = (EditText) findViewById(R.id.et_addName);
        et_addPrice = (EditText) findViewById(R.id.et_addPrice);
        et_youhuiPrice = (EditText) findViewById(R.id.et_youhuiPrice);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加菜品
            case R.id.tv_addSave:
                et_youhuiPrice1 = et_youhuiPrice.getText().toString().trim();
                name = et_addName.getText().toString().trim();
                price = et_addPrice.getText().toString().trim();
                if("".equals(et_youhuiPrice1)){
                    et_youhuiPrice.setText(price);
                    et_youhuiPrice1 = et_youhuiPrice.getText().toString().trim();
                }
                if (nameList.size() <= 0) {
                    Toast.makeText(AddFoodActivity.this, "上架商品失败，请先添加菜品管理页面左侧的菜品分类信息", Toast.LENGTH_SHORT).show();
                    return;
                } else if (nameList.size() > 0) {
                    if (name.equals("") || price.equals("")) {
                        Toast.makeText(AddFoodActivity.this, "上架商品失败，菜名、价格等信息不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mWeiboDialog = WeiboDialogUtils.createLoadingDialog(AddFoodActivity.this, "上传中...");
                    upload();
                }


                break;
            //添加图片
            case R.id.img_add:
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
                break;
            //返回
            case R.id.imb_back:
                finish();
                break;
            default:
                break;
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
        PermissionGen.with(AddFoodActivity.this)
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
        mTargetW = img_add.getWidth();
        mTargetH = img_add.getHeight();

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
                    path = PictureUtils.getPath_above19(AddFoodActivity.this, this.uri);
                } else {
                    path = PictureUtils.getFilePath_below19(AddFoodActivity.this, this.uri);
                }
                break;
        }
        img_add.setImageBitmap(PictureUtils.getSmallBitmap(path, mTargetW, mTargetH));
        img_add.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void upload() {
        String imageType = "multipart/form-data";
        if (path == null) {
            Toast.makeText(AddFoodActivity.this, "请输入菜品图片信息", Toast.LENGTH_SHORT).show();
            return;
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
                    String url = urlbean.getUrl();
                    //上传菜品
                    initPost(url);
                }
            });
        }
    }

    private void initPost(final String url) {
        RequestManager.postJson().addParams("tb_category_categoryId", String.valueOf(categoryID))
                .addParams("name", name)
                .addParams("activityPrice", price)
                .addParams("sellPrice",et_youhuiPrice1)
                .addParams("imageUrl", url)
                .addParams("token", token).setUrl(Url.addURL)
                .builder().onUI().setCallback(new IRequestBeanListener<LoginBean>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSuccess(LoginBean baen) {
                if (baen.getStatus().equals("200")) {
                    EventBus.getDefault().post(new MessageEventC("C"));
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    Toast.makeText(AddFoodActivity.this, "添加菜品成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddFoodActivity.this, "添加菜品失败", Toast.LENGTH_SHORT).show();
                }

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
                        List<ProductList.DataBean> data = productList.getData();
                        for (int i = 0; i < data.size(); i++) {
                            nameList.add(data.get(i).getName());
                            categoryIDList.add(data.get(i).getId());

                        }
                        //使用自定义的ArrayAdapter
                        TestArrayAdapterA testArrayAdapterA = new TestArrayAdapterA(AddFoodActivity.this, nameList);
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
                categoryID = categoryIDList.get(position);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

    }
}
