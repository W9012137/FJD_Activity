package chencheng.bwie.com.fjd_activity.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.view.Fragment_two;
import chencheng.bwie.com.fjd_activity.found.view.Fragment_three;
import chencheng.bwie.com.fjd_activity.homepage.view.fragment_one;
import chencheng.bwie.com.fjd_activity.my.view.Fragment_five;
import chencheng.bwie.com.fjd_activity.theshoppingcart.view.Fragment_four;

public class MainTwoActivity extends AppCompatActivity {
    private BottomTabBar mBottomTabBar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        initView();

        //创建SharedPreferences对象
        sharedPreferences=getSharedPreferences("user",0);
        //拿到后返回的用户id

        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(70, 70)
                .setFontSize(15)
                .setTabPadding(2, 5, 5)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.ac1,R.drawable.ac0, fragment_one.class)
                .addTabItem("分类",R.drawable.abx,R.drawable.abw, Fragment_two.class)
                .addTabItem("发现", R.drawable.abz,R.drawable.ac0, Fragment_three.class)
                .addTabItem("购物车", R.drawable.ac1,R.drawable.aby, Fragment_four.class)
                .addTabItem("个人中心", R.drawable.ac3,R.drawable.ac2, Fragment_five.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);


    }

    private void initView() {
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
    }
    @SuppressLint("ResourceType")
    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("userloginflag", 3);

        if (id == 1 ) {
            mBottomTabBar.setTabBarBackgroundResource(3);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        if (id == 2 ) {
            mBottomTabBar.setTabBarBackgroundResource(4);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        super.onResume();
    }

}
