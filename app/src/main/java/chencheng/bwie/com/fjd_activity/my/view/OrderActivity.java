package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.bean.CatsOrdersBean;
import chencheng.bwie.com.fjd_activity.my.presenter.CartsOrderPresenter;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener ,ICartsOrderView {

    private TextView mTvLeft;
    /**
     * 立即下单
     */
    private Button mBt;
    private LinearLayout mActivityConfirm;
    CartsOrderPresenter presenter;
    private SharedPreferences sp;
    private String uid;
    private String token;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        token = sp.getString("token", "");
        //接收传过来实付款
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        presenter=new CartsOrderPresenter(this);
        presenter.getOrder(uid,money,token);
        initView();
        mTvLeft.setText("实付款：¥" + money);

    }

    private void initView() {
        mTvLeft = (TextView) findViewById(R.id.tvLeft);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mActivityConfirm = (LinearLayout) findViewById(R.id.activity_confirm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                int i = Integer.parseInt(code);
                if (i==0) {
                    //跳转到订单页面
                    Intent intent = new Intent(OrderActivity.this, OrderTwoActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"价格错误",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void getShow(CatsOrdersBean bean) {
        code = bean.getCode();
    }
}
