package chencheng.bwie.com.fjd_activity.classification.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.adapter.MyListAdapter;
import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;
import chencheng.bwie.com.fjd_activity.classification.presenter.ListPresenter;
import chencheng.bwie.com.fjd_activity.view.Main2Activity;

public class Rv_ListActivity extends AppCompatActivity implements IListView ,View.OnClickListener{
    private XRecyclerView mRv;
    /**
     * 综合
     */
    private TextView mTvZhonghe;
    private ImageView fan;
    /**
     * 销量
     */
    private TextView mTvXiaoliang;
    /**
     * 价格
     */
    private TextView mTvPrice;
    /**
     * 筛选
     */
    private boolean b = false;

    private TextView mTvShaixuan;

    private String pscid;
    private String sort = "0";
    private int page = 1;
    private ListPresenter presenter;
    private MyListAdapter adapter;
    List<LiatBean.DataBean> list = new ArrayList<>();
    private ImageView mIvG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv__list);
        Intent it = getIntent();
        pscid = it.getStringExtra("pscid");
        presenter = new ListPresenter(this);
        presenter.showl(pscid, page + "", "0");
        initView();
    }

    private void initView() {
        mRv = (XRecyclerView) findViewById(R.id.xrv);
        mTvZhonghe = (TextView) findViewById(R.id.tvZhonghe);
        mTvZhonghe.setOnClickListener(this);
        mTvXiaoliang = (TextView) findViewById(R.id.tvXiaoliang);
        mTvXiaoliang.setOnClickListener(this);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvPrice.setOnClickListener(this);
        mTvShaixuan = (TextView) findViewById(R.id.tvShaixuan);
        fan=findViewById(R.id.fanhui);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rv_ListActivity.this, Main2Activity.class);
                intent.putExtra("userloginflag", 3);

                startActivity(intent);

                finish();
            }
        });
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page ++;
                presenter.showl(pscid, page + "", sort);
                mRv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.showl(pscid, page + "", sort);
                mRv.loadMoreComplete();
            }
        });
        mIvG = (ImageView) findViewById(R.id.iv_g);
        mIvG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Rv_ListActivity.this, "点击了切换视图按钮", Toast.LENGTH_SHORT).show();
                if (b == false) {
//点击后想要变成什么要的布局样式就搞一个你的需求
                    mRv .setLayoutManager(new GridLayoutManager(Rv_ListActivity.this, 2));
//给布尔值重新赋值
                    b = true;
//给点击按钮的图片重新赋值
                    /*im.setImageResource(R.mipmap.ic_linear);*/
                } else if (b == true) {
                    mRv.setLayoutManager(new LinearLayoutManager(Rv_ListActivity.this));
//给布尔值重新赋值
                    b = false;
                    mIvG.setImageResource(R.drawable.kind_grid);
//给点击按钮的图片重新赋值
                    /*   cIv.setImageResource(R.mipmap.ic_grid);*/
                }
            }
        });

        mRv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvZhonghe:
                sort = "0";
                presenter.showl(pscid, "1", sort);
                break;
            case R.id.tvXiaoliang:
                sort = "1";
                presenter.showl(pscid, "1", sort);
                break;
            case R.id.tvPrice:
                sort = "2";
                presenter.showl(pscid, "1", sort);
                break;
        }
    }
    @Override
    public void ShowList(List<LiatBean.DataBean> dataBeans, String pscid) {
        adapter = new MyListAdapter(this, dataBeans);
        mRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        adapter.setOnclickSpflAdpter(new MyListAdapter.OnClickfl() {
//            @Override
//            public void onClickxq(int position) {
//                Intent intent = new Intent(Rv_ListActivity.this, TwoDetactivity.class);
//                intent.putExtra("pid", position);
//                startActivity(intent);
//
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.Pdstry();
        }
    }
}
