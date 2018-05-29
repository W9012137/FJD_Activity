package chencheng.bwie.com.fjd_activity.classification.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.adapter.MyAdapter;
import chencheng.bwie.com.fjd_activity.classification.adapter.MyExp;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.classification.presenter.LifetPresnter;

public class Fragment_two extends Fragment implements IViewTwo {

    private View view;
    private ListView mLv;
    private ImageView mBanner;
    private MyExpandableListView mElv;
    private ScrollView mSv;
  LifetPresnter presnter;
  MyAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenter_two, container, false);
        presnter=new LifetPresnter(this);
        presnter.rv_list();
        initView(view);
        return view;
    }

    @Override
    public void ShowList(List<CatagoryBean.DataBean> catagoryBean) {
  adapter=new MyAdapter(getActivity(),catagoryBean);
  mLv.setAdapter(adapter);
    }

    @Override
    public void showright(List<RightBean.DataBean> group, List<List<RightBean.DataBean.ListBean>> child) {
        MyExp myExp = new MyExp(getActivity(), group, child);
        mElv.setAdapter(myExp);
        for (int i=0;i<group.size();i++){
            mElv.expandGroup(i);
        }
    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        mBanner = (ImageView) view.findViewById(R.id.banner);
        mElv = (MyExpandableListView) view.findViewById(R.id.elv);
        mSv = (ScrollView) view.findViewById(R.id.sv);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CatagoryBean.DataBean dataBean= (CatagoryBean.DataBean) adapterView.getItemAtPosition(i);
                adapter.changeItemSelect(i);
                int cid=dataBean.getCid();
                presnter.showR(cid+"");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       if (presnter!=null){
           presnter.Pdstry();
       }
    }
}
