package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.tauth.Tencent;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.my.presenter.UserPresenter;

public class Fragment_five extends Fragment implements View.OnClickListener, IUserView {
    private SimpleDraweeView mImageView;
    /**
     * 登录/注册
     */

    private TextView mLogin;

    SharedPreferences sp;
    String uid;
    String token;
    private Tencent mTencent;
    UserPresenter presenter;
    private View view;
    private LinearLayout mOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("user", 0);
        uid = sp.getString("uid", 0 + "");
        token=sp.getString("token","");
        View view = inflater.inflate(R.layout.fragmenter_five, container, false);
        initView(view);
        presenter = new UserPresenter(this);

        presenter.showu(uid);

        return view;
    }

    private void initView(View view) {
        mImageView = (SimpleDraweeView) view.findViewById(R.id.imageView);
        mImageView.setOnClickListener(this);
        mLogin = (TextView) view.findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mOrder = (LinearLayout) view.findViewById(R.id.order);
        mOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imageView:
                startActivity(new Intent(getActivity(), UserActivity.class));

                break;
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.order:
                startActivity(new Intent(getActivity(), OrderTwoActivity.class));

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void ShowUser(UserBean userBean) {
        if (token.equals("")) {
            mImageView.setImageURI(userBean.getData().getIcon());
            mLogin.setText(userBean.getData().getUsername());
        } else {
            mImageView.setImageURI(String.valueOf(R.drawable.user));
            mLogin.setText("登录/注册");
        }
  }
}
