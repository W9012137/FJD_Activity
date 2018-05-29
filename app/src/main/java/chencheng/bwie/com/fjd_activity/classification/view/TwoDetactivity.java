package chencheng.bwie.com.fjd_activity.classification.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.AddCartBean;
import chencheng.bwie.com.fjd_activity.classification.bean.DetailBean;
import chencheng.bwie.com.fjd_activity.classification.presenter.TwoDetailPresenter;
import chencheng.bwie.com.fjd_activity.view.Main2Activity;

public class TwoDetactivity extends AppCompatActivity implements ITwoDetailView, View.OnClickListener {
    private ImageView mIvBack;
    /**
     * 商品
     */
    private RadioButton mRbGoods;
    /**
     * 详情
     */
    private RadioButton mRbDetails;
    /**
     * 评价
     */
    private RadioButton mRbAppraise;
    private RadioGroup mRg;
    private ImageView mIvShare;
    private ImageView mIvMsg;
    private Banner mDetailBn;
    private TextView mDetailTitel;
    private TextView mDetailPrice;
    private LinearLayout mVp;
    private LinearLayout mLlSupplier;
    private LinearLayout mLlShop;
    private LinearLayout mLlAttention;
    private LinearLayout mLlCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private List<String> list;
    private TwoDetailPresenter detailPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtwo);
        initView();
        ButterKnife.bind(this);


        detailPresenter = new TwoDetailPresenter(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");

        detailPresenter.getDetai(pid);
    }

    @Override
    public void detailshow(DetailBean detailBean) {
        mDetailTitel.setText(detailBean.getData().getSubhead());
        mDetailPrice.setText("¥" + detailBean.getData().getPrice());

        list = new ArrayList<>();
        String images = detailBean.getData().getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        mDetailBn.setImages(list)
                .setImageLoader(new MyGlide())
                .isAutoPlay(true)
                .start();
    }

    @Override
    public void addshow(AddCartBean addCartBean) {
        Toast.makeText(this, addCartBean.getMsg() + "", Toast.LENGTH_LONG).show();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mRbGoods = (RadioButton) findViewById(R.id.rbGoods);
        mRbDetails = (RadioButton) findViewById(R.id.rbDetails);
        mRbAppraise = (RadioButton) findViewById(R.id.rbAppraise);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvMsg = (ImageView) findViewById(R.id.ivMsg);
        mDetailBn = (Banner) findViewById(R.id.detail_bn);
        mDetailTitel = (TextView) findViewById(R.id.detail_titel);
        mDetailPrice = (TextView) findViewById(R.id.detail_price);
        mVp = (LinearLayout) findViewById(R.id.vp);
        mLlSupplier = (LinearLayout) findViewById(R.id.llSupplier);
        mLlShop = (LinearLayout) findViewById(R.id.llShop);
        mLlAttention = (LinearLayout) findViewById(R.id.llAttention);
        mLlCard = (LinearLayout) findViewById(R.id.llCard);
        mLlCard.setOnClickListener(this);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mTvAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.llCard:
                Intent intent = new Intent(TwoDetactivity.this, Main2Activity.class);
                intent.putExtra("userloginflag", 1);

                startActivity(intent);

                finish();
                break;
            case R.id.tvAddCard:
                SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                detailPresenter.getadds(sp.getString("uid", ""), pid,sp.getString("token",""));

                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.onDestroys();
    }


}
