package chencheng.bwie.com.fjd_activity.my.view;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.HashMap;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.my.presenter.UserPresenter;
import chencheng.bwie.com.fjd_activity.view.Main2Activity;

public class UserActivity extends AppCompatActivity implements View.OnClickListener ,IUserView {

    private ImageView mImageView5;
    /**
     * 个人中心
     */
    private TextView mTextView;
    private SimpleDraweeView mUserSim;
    /**
     * nihao
     */
    private TextView mNewname;
    /**
     * 白天模式
     */
    private TextView mQiehuan;
    /**
     * 退出登录
     */

    private Button mBut;
    UserPresenter presenter;
    SharedPreferences sharedPreferences;
    String uid;
    private String imagePath;
    private PopupWindow popupWindow;
    //上传头像
    private static final int CHOOSE_PICTURE=0;
    private static final int TAKE_PICTURE=1;
    private static final int CROP_SMALL_PICTURE=2;
    private static Uri tempUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (SPUtils.get(this, "theme", "dayTheme").equals("dayTheme")) {
            setTheme(R.style.dayTheme);
        } else {
            setTheme(R.style.nightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        presenter=new UserPresenter(this);
        sharedPreferences=UserActivity.this.getSharedPreferences("user",0);
        uid=sharedPreferences.getString("uid",0+"");
        presenter.showu(uid);
    }

    private void initView() {
        mImageView5 = (ImageView) findViewById(R.id.imageView5);
        mImageView5.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);
        mUserSim = (SimpleDraweeView) findViewById(R.id.user_sim);
        mUserSim.setOnClickListener(this);
        mNewname = (TextView) findViewById(R.id.newname);
        mNewname.setOnClickListener(this);
        mQiehuan = (TextView) findViewById(R.id.qiehuan);
        mQiehuan.setOnClickListener(this);
        mBut = (Button) findViewById(R.id.but);
        mBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imageView5:
                Intent intent = new Intent(UserActivity.this, Main2Activity.class);
                intent.putExtra("userloginflag", 2);

                startActivity(intent);

                finish();
                break;
            case R.id.user_sim:
                //弹出pop
                View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
                //找到pop里面的选项
                TextView pai = view.findViewById(R.id.pai);
                pai.setOnClickListener(this);
                TextView xiangce = view.findViewById(R.id.xiangce);
                xiangce.setOnClickListener(this);
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                popupWindow.setOutsideTouchable(true);
                //显示
                popupWindow.showAtLocation(UserActivity.this.findViewById(R.id.main),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

            case R.id.pai:
                //启动系统相机
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                //开启相机之前，设置图片保存的路径
                imagePath = Environment.getExternalStorageDirectory().getPath() + "myIcon.png";
                File file = new File(imagePath);
                //创建Uri
                Uri imageUri = Uri.fromFile(file);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                //设置完毕开启Activity
                startActivityForResult(intent1, 0);
                //pop消失
                popupWindow.dismiss();
                break;
            case R.id.xiangce:
                Intent intent2 = new Intent();
                intent2.addCategory(Intent.CATEGORY_OPENABLE);
                intent2.setType("image/*");
                if (Build.VERSION.SDK_INT < 19) {
                    intent2.setAction(Intent.ACTION_GET_CONTENT);
                } else {
                    intent2.setAction(Intent.ACTION_OPEN_DOCUMENT);
                }
                startActivityForResult(intent2, 1);
                break;
            case R.id.newname:
                break;
            case R.id.qiehuan:
                if (SPUtils.get(UserActivity.this, "theme", "dayTheme").equals(
                        "dayTheme")) {
                    mQiehuan.setText("夜天模式");
                    SPUtils.put(UserActivity.this, "theme", "nightTheme");

                } else {
                    mQiehuan.setText("白天模式");
                    SPUtils.put(UserActivity.this, "theme", "dayTheme");

                }
                recreate();

                break;
            case R.id.but:
                break;
        }
    }

    @Override
    public void ShowUser(UserBean userBean) {
        mUserSim.setImageURI(userBean.getData().getIcon());
        mNewname.setText(userBean.getData().getUsername());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.Pdstry();
        }
    }
    //重写
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断
        switch (requestCode) {
            case 0:
                //获取图片的uri
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    //放入图片
                    mUserSim.setImageBitmap(bitmap);
                    //上传头像
                    //得到图片路径
                    File imageFile = new File(imagePath);
                    OKHttpUtils okHttpUtils = OKHttpUtils.getInstance();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("source", "android");
                    okHttpUtils.upLoadFile("https://www.zhaoapi.cn/file/upload", imageFile, map);
                    okHttpUtils.setUpListene(new UpListener() {
                        @Override
                        public void upSuccess(String json) {
                            Log.d("TAG", "upSuccess() returned: " + json);
                            Toast.makeText(UserActivity.this, "chengg000", Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void upError(String e) {
                            Log.d("TAG", "upError() returned: " + e);
                            Toast.makeText(UserActivity.this, "shibai----", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case 1:
                Uri uri = data.getData();
                //相册返回的是uri，这里需要将uri转成path路径
                String path = getImageAbsolutePath(this, uri);
                Log.d("TAG", "路径---- " + uri + "--" + path);
                File imageFile = new File(path);//创建文件
                //开始调用okhttputils进行网络上传
                OKHttpUtils okHttpUtils = OKHttpUtils.getInstance();
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("source", "android");
                okHttpUtils.upLoadFile("https://www.zhaoapi.cn/file/upload", imageFile, map);
                //回调
                okHttpUtils.setUpListene(new UpListener() {
                    @Override
                    public void upSuccess(String json) {
                        Log.d("TAG", "upSuccess() returned: " + json);
                        Toast.makeText(UserActivity.this, "chengg000", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void upError(String e) {
                        Log.d("TAG", "upError() returned: " + e);
                        Toast.makeText(UserActivity.this, "shibai----", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }


    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
