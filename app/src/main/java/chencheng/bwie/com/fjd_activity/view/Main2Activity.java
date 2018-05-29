package chencheng.bwie.com.fjd_activity.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.adapter.MyFragmentAdapter;
import chencheng.bwie.com.fjd_activity.classification.view.Fragment_two;
import chencheng.bwie.com.fjd_activity.found.view.Fragment_three;
import chencheng.bwie.com.fjd_activity.homepage.view.fragment_one;
import chencheng.bwie.com.fjd_activity.my.view.BaseActivity;
import chencheng.bwie.com.fjd_activity.my.view.Fragment_five;
import chencheng.bwie.com.fjd_activity.theshoppingcart.view.Fragment_four;

public class Main2Activity extends BaseActivity{

    private ViewPager mViewPager;
    private RadioButton mSybut;
    private RadioButton mFlbut;
    private RadioButton mFxbut;
    private RadioButton mGwcbut;
    private RadioButton mWdbut;
    private RadioGroup mConbut;
    List<Fragment> list;
    MyFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mSybut = (RadioButton) findViewById(R.id.sybut);
        mFlbut = (RadioButton) findViewById(R.id.flbut);
        mFxbut = (RadioButton) findViewById(R.id.fxbut);
        mGwcbut = (RadioButton) findViewById(R.id.gwcbut);
        mWdbut = (RadioButton) findViewById(R.id.wdbut);
        mConbut = (RadioGroup) findViewById(R.id.conbut);
        list=new ArrayList<>();
        list.add(new fragment_one());
        list.add(new Fragment_two());
        list.add(new Fragment_three());
        list.add(new Fragment_four());
        list.add(new Fragment_five());
        adapter=new MyFragmentAdapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mConbut.check(R.id.sybut);
                        break;
                    case 1:
                        mConbut.check(R.id.flbut);
                        break;
                    case 2:
                        mConbut.check(R.id.fxbut);
                        break;
                    case 3:
                        mConbut.check(R.id.gwcbut);
                        break;
                    case 4:
                        mConbut.check(R.id.wdbut);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mConbut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.sybut:
                        /**
                         * setCurrentItem第二个参数控制页面切换动画
                         * true:打开/false:关闭
                         */
                        mViewPager.setCurrentItem(0, false);

                        break;
                    case R.id.flbut:
                        mViewPager.setCurrentItem(1, false);

                        break;
                    case R.id.fxbut:
                        mViewPager.setCurrentItem(2, false);

                        break;
                    case R.id.gwcbut:
                        mViewPager.setCurrentItem(3, false);

                        break;
                    case R.id.wdbut:
                        mViewPager.setCurrentItem(4, false);

                        break;
                }
            }
        });
    }
    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("userloginflag", 3);

        if (id == 1 ) {
            mViewPager.setCurrentItem(3);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        if (id == 2 ) {
            mViewPager.setCurrentItem(4);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        if (id == 3) {
            mViewPager.setCurrentItem(1);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        if (id == 4) {
            mViewPager.setCurrentItem(0);
            //3代表”我的京东“所在条目的位置，参考下面的源码即可理解
        }
        super.onResume();
    }

}
