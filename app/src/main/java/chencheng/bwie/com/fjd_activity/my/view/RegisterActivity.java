package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.bean.RegisterBean;
import chencheng.bwie.com.fjd_activity.my.presenter.RegisterPresenter;

/**
 * Created by dell on 2018/4/10.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterview {
    private ImageView mImageView4;
    /**
     * 请输入账号
     */
    private EditText mPhone;
    /**
     * 请输入密码
     */
    private EditText mPwd;
    /**
     * 立即注册
     */
    private Button mButton;
    private String name;
    private String pwd;
    RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
        presenter = new RegisterPresenter(this);

    }

    private void initView() {
        mImageView4 = (ImageView) findViewById(R.id.imageView4);
        mPhone = (EditText) findViewById(R.id.phone);
        mPwd = (EditText) findViewById(R.id.pwd);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mImageView4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
               presenter.register();
                break;
            case R.id.imageView4:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void showregister(RegisterBean registerBean) {
        String msg = registerBean.getMsg();
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getAccount() {
        return mPhone.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mPwd.getText().toString().trim();
    }

    @Override
    public void finishAc() {
finish();
    }

    @Override
    public void show(String str) {
        Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.Pdstry();
        }
    }
}
