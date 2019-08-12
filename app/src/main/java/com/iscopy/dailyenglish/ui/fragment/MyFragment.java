package com.iscopy.dailyenglish.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscopy.dailyenglish.R;
import com.iscopy.dailyenglish.app.DEApplication;
import com.iscopy.dailyenglish.base.BaseFragment;
import com.iscopy.dailyenglish.constant.Config;
import com.iscopy.dailyenglish.databank.SharedPreferencesUtils;
import com.iscopy.dailyenglish.ui.my.CollectionActivity;
import com.iscopy.dailyenglish.ui.my.EditorActivity;
import com.iscopy.dailyenglish.ui.my.LoadingActivity;
import com.iscopy.dailyenglish.utils.Base64BitmapUtils;
import com.iscopy.dailyenglish.utils.GlideUtil;
import com.iscopy.dailyenglish.utils.T;
import com.iscopy.dailyenglish.widget.IosDialogEvalute;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MyFragment extends BaseFragment {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.tv_editor)
    TextView tvEditor;
    @BindView(R.id.iv_editor)
    ImageView ivEditor;
    @BindView(R.id.ll_collection)
    LinearLayout llCollection;
    @BindView(R.id.ll_sign_in)
    LinearLayout llSignIn;
    @BindView(R.id.ll_instructions)
    LinearLayout llInstructions;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.ll_set_up)
    LinearLayout llSetUp;

    private Bitmap bitmap;
    private static final int CAMERA_VALUE = 0x0001;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    private final String IMAGE_DIR = Environment.getExternalStorageDirectory() + File.separator + "photo.jpg";

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        //我的金句
        quotes();

        //头像
        String head = SharedPreferencesUtils.getStringData(DEApplication.getAppContext(), Config.HEAD);
        if (head != null) {
            Bitmap bitmap = Base64BitmapUtils.base64ToBitmap(head);
            bitmap = screenshots(bitmap);
            ivHead.setImageBitmap(bitmap);
        }
    }

    @Override
    public void receiveRadio(Intent intent) {

    }

    @OnClick({R.id.iv_head, R.id.ll_tag, R.id.iv_editor, R.id.ll_collection, R.id.ll_sign_in, R.id.ll_instructions, R.id.ll_loading, R.id.ll_set_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                showdialog();
                break;
            case R.id.ll_tag:
                break;
            case R.id.iv_editor:
                startActivity(EditorActivity.class, Config.MY_QUOTES_INT);
                break;
            case R.id.ll_collection:
                startActivity(CollectionActivity.class);
                break;
            case R.id.ll_sign_in:
                break;
            case R.id.ll_instructions:
                break;
            case R.id.ll_loading:
                startActivity(LoadingActivity.class);
                break;
            case R.id.ll_set_up:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.MY_QUOTES_INT:
                quotes();
                break;
            case Config.PHOTOA_REQUEST_CODE:
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(DEApplication.getAppContext().getContentResolver(), data.getData());
                    bitmap = screenshots(bitmap);
                    ivHead.setImageBitmap(bitmap);
                    saveBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Config.CAMERA_REQUEST_CODE:
                // 调用相机拍照
                if (IMAGE_DIR != null) {
                    File temp = new File(IMAGE_DIR);
                    try {
                        Uri uri = Uri.fromFile(temp);
                        bitmap = MediaStore.Images.Media.getBitmap(DEApplication.getAppContext().getContentResolver(), uri);
                        bitmap = screenshots(bitmap);
                        ivHead.setImageBitmap(bitmap);
                        saveBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 图片如果过大，就处理图片
     *
     * @param bitmap
     */
    public Bitmap screenshots(Bitmap bitmap) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        /**
         * 图片的宽度 （用于判断当前图判是否过大，避免OOM使得imageview无法显示）
         * 适用于 720P，其他分辨率需要重新修改
         */
        int mFixedWidth = 1280;
        //图片的高度 （用于客户预置特别大图，重新处理bitmap）
        int mFixedHeight = 720;
        if (bitmap.getWidth() <= screenWidth) {
            return bitmap;
        } else {
            //Bitmap bmp=Bitmap.createScaledBitmap(bm, screenWidth, bm.getHeight()*screenWidth/bm.getWidth(), true);
            if (bitmap.getWidth() <= mFixedWidth) {
                return bitmap;
            }
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * mFixedHeight / bitmap.getHeight(), mFixedHeight, true);
            return bitmap;
        }
    }

    /**
     * 我的金句
     */
    public void quotes() {
        String quotes = SharedPreferencesUtils.getStringData(getMContext(), Config.MY_QUOTES);
        if (quotes != null && !quotes.equals("")) {
            tvEditor.setText(quotes);
        }
    }

    /**
     * 弹框选择拍照还是相册
     */
    public void showdialog() {
        IosDialogEvalute.Builder builder = new IosDialogEvalute.Builder(getMContext());
        builder.setPositiveButton("拍照", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            requestCaneraQermissions();
        });
        builder.setNegativeButton("从相册选择", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            pickFromGallery();
        });
        builder.setCancelButton("取消", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    /**
     * 拍照
     */
    @AfterPermissionGranted(CAMERA_VALUE)
    public void requestCaneraQermissions() {
        if (EasyPermissions.hasPermissions(getMContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
            //  inspectPermission();
            takePhoto();
        } else {
            EasyPermissions.requestPermissions(this, "需要开启相机权限", CAMERA_VALUE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        }
    }

    private void takePhoto() {
        // Permission was added in API Level 16
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && ActivityCompat.checkSelfPermission(getMContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
        } else {
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //下面这句指定调用相机拍照后的照片存储的路径
            System.out.print("---IMAGE_DIR------" + IMAGE_DIR);
            Uri uri;
            //适配7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(getMContext(), "com.iscopy.dailyenglish.fileprovider", new File(IMAGE_DIR));
            } else {
                uri = Uri.fromFile(new File(IMAGE_DIR));
            }
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(takeIntent, Config.CAMERA_REQUEST_CODE);
        }
    }

    /**
     * 从相册选择
     */
    private void pickFromGallery() {
        // Permission was added in API Level 16
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && ActivityCompat.checkSelfPermission(getMContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 101);
        } else {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
            // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, Config.PHOTOA_REQUEST_CODE);
        }
    }

    /**
     * 将Bitmap转换成Base64保存到SharedPreferences
     *
     * @param bitmap
     */
    public void saveBitmap(Bitmap bitmap) {
        String strBitmap = Base64BitmapUtils.bitmapToBase64(bitmap);
        if (strBitmap.equals("")) {
            T.showShort("bitmapToBase64 转换失败！");
        } else {
            boolean trfa = SharedPreferencesUtils.saveStringData(DEApplication.getAppContext(), Config.HEAD, strBitmap);
            if (trfa) {
                T.showShort("头像保存成功！");
            } else {
                T.showShort("头像保存失败！");
            }
        }
    }
}
