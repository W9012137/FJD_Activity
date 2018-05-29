package chencheng.bwie.com.fjd_activity.theshoppingcart.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.view.OrderActivity;
import chencheng.bwie.com.fjd_activity.theshoppingcart.adapter.GwAdapter;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.MessageEvent;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.PriceAndCountEvent;
import chencheng.bwie.com.fjd_activity.theshoppingcart.presenter.CartsPresenter;

public class Fragment_four extends Fragment implements ICartsView {

    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private CartsPresenter presenter;
    SharedPreferences sharedPreferences;
    GwAdapter adapter;
    String uid;
    private int price;
    private String token;
    private SwipeRefreshLayout mShopiingScroll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new CartsPresenter(this);
        //创建SharedPreferences对象
        sharedPreferences = getActivity().getSharedPreferences("user", 0);
        token = sharedPreferences.getString("token", "");
        uid = sharedPreferences.getString("uid", 0 + "");
        if (this.token.equals("")){
            view = inflater.inflate(R.layout.ceshi_itme_jlj, container, false);
        }else {
            view = inflater.inflate(R.layout.fragmenter_gour, container, false);
            initView(view);
            presenter.Show(uid);
            Toast.makeText(getActivity(), "uid:" + uid, Toast.LENGTH_LONG).show();
            EventBus.getDefault().register(this);
            mCheckbox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.changeAllListCbState(mCheckbox2.isChecked());

                }
            });
        }
           
        return view;
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mShopiingScroll = (SwipeRefreshLayout) view.findViewById(R.id.shopiing_scroll);
        mTvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("money",price+"");
                startActivity(intent);

            }
        });
    }

    @Override
    public void showList(List<CartsBean.DataBean> groupList, List<List<CartsBean.DataBean.ListBean>> childList, final String uid) {

    adapter = new GwAdapter(getActivity(), groupList, childList);
    mElv.setAdapter(adapter);
    mElv.setGroupIndicator(null);
    //默认让其全部展开
    for (int i = 0; i < groupList.size(); i++) {
        mElv.expandGroup(i);
    }
    //删除接口
    adapter.setOnClink(new GwAdapter.OnClinks() {
        @Override
        public void onclikId(int pid) {
            presenter.getdelete(uid, pid + "", token);
        }

    });
    mShopiingScroll.setColorSchemeColors(Color.GRAY);
    mShopiingScroll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mShopiingScroll.setRefreshing(false);
            presenter.Show(sharedPreferences.getString("uid", 0 + ""));
        }
    });



    }

    @Override
    public void showDelete(DeleteBean deleteBean) {
        String code = deleteBean.getCode();
        int i = Integer.parseInt(code);
        if (i == 0) {
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), deleteBean.getMsg(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);


    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {

        mCheckbox2.setChecked(event.isChecked());
    }


    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText(event.getPrice() + "");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.Pdstry();
            EventBus.getDefault().unregister(this);
        }
    }
}
