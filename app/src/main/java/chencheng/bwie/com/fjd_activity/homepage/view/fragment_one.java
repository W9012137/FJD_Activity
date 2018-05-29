package chencheng.bwie.com.fjd_activity.homepage.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.homepage.adapter.MyAdapter;
import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.homepage.presenter.GetAdPresenter;
import chencheng.bwie.com.fjd_activity.homepage.presenter.LefterPresnter;
import chencheng.bwie.com.fjd_activity.zing.MipcaActivityCapture;

public class fragment_one extends Fragment implements IOneView{

    private View view;
    private ImageView mIvG ,saomiao;
    private LinearLayout mLin;
    private RecyclerView mRvCont;
    private ObservableScrollView mScrollView;
    private int imageHeight=300; //设置渐变高度，一般为导航图片高度，自己控制
   MyAdapter adapter;
   GetAdPresenter presenter;
   List<CatagoryBean.DataBean> catagoryBean;
   LefterPresnter lefterPresnter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenter_one, container, false);
        initView(view);
         presenter=new GetAdPresenter(this);
         presenter.GetShow();
        lefterPresnter=new LefterPresnter(this);
        lefterPresnter.rv_list();

        return view;
    }

    private void initView(View view) {
        mIvG = (ImageView) view.findViewById(R.id.iv_g);
        saomiao=view.findViewById(R.id.saomiao);
        mLin = (LinearLayout) view.findViewById(R.id.lin);
        mRvCont = (RecyclerView) view.findViewById(R.id.rv_cont);
        mRvCont.setLayoutManager( new LinearLayoutManager(getActivity()));
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        //扫描
        saomiao = view.findViewById(R.id.saomiao);
        saomiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描二维码
                Intent intent = new Intent(getActivity(), MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 1);
                // startActivityForResult(new Intent(getActivity(), MipcaActivityCapture.class), MipcaActivityCapture.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });
        //搜索框在布局最上面
        mLin.bringToFront();
        mScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    mLin.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    mLin.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
                } else {
                    mLin.setBackgroundColor(Color.argb((int) 200, 0, 0, 0));
                }
            }

        });
    }

    @Override
    public void showView(GetAdBean getAdBean) {
  adapter=new MyAdapter(getActivity(),getAdBean,catagoryBean);
  mRvCont.setAdapter(adapter);
    }

    @Override
    public void LefiView(List<CatagoryBean.DataBean> catagoryBean) {
        this.catagoryBean=catagoryBean;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       if (presenter!=null){
           presenter.Pdstry();
       }
    }
}
