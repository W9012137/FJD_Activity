package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.bean.LogBean;
import chencheng.bwie.com.fjd_activity.my.presenter.LoginPresenter;
import chencheng.bwie.com.fjd_activity.net.Toasts;
import chencheng.bwie.com.fjd_activity.view.Main2Activity;

/**
 * Created by dell on 2018/4/10.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,ILogView {
    /**
     * 请输入账号
     */
    private EditText mPhone;
    /**
     * 请输入密码
     */
    private EditText mPwd;
    /**
     * 登录
     */
    private Button mButton;
    /**
     * 注册
     */
    private Button mButton2;
    private ImageView mImageView2;
    private ImageView mImageView3;
    LoginPresenter presenter;
    private String name;
    private String pwd;
    SharedPreferences sharedPreferences;
    private static final String APP_ID = "1106932614";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
        presenter = new LoginPresenter(this);
        DisplayImageOptions op=new DisplayImageOptions.Builder().build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(op)
                .build();
        ImageLoader.getInstance().init(con);
        initView();
    }

    private void initView() {
        mPhone = (EditText) findViewById(R.id.phone);
        mPwd = (EditText) findViewById(R.id.pwd);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView2.setOnClickListener(this);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                name = mPhone.getText().toString().trim();
                pwd = mPwd.getText().toString().trim();
                presenter.log(name, pwd);
                break;
            case R.id.button2:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.imageView2:
                break;
            case R.id.imageView3:
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this,"all",mIUiListener);
                break;
        }
    }

    @Override
    public void showlofin(LogBean logBean) {
        final LogBean.DataBean data = logBean.getData();
        SharedPreferences sp = getSharedPreferences("user", 0);
        sp.edit().putString("name", data.getMobile() + "")
                .putString("uid", data.getUid() + "")
                .putString("pwd", data.getPassword() + "")
                .putString("token", data.getToken() + "").commit();
        Toasts.showLong(this, "登录成功");
        final Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.Pdstry();
        }
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(final Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            final JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                        System.out.println("==========" + response);


                        JSONObject oo = (JSONObject) response;

                        try {
                            String na = oo.getString("nickname");
                            System.out.println("===解析的名字" + na);
                            // name.setText(na);
                            String url = oo.getString("figureurl_2");
                            System.out.println("===解析的url" + url);
                            //ImageLoader.getInstance().displayImage(url,head);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}