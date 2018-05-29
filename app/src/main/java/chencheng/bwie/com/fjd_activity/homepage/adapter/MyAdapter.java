package chencheng.bwie.com.fjd_activity.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.view.TwoDetactivity;
import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.homepage.view.GlideImageLoader;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static int TYPE1=0;
    private static int TYPE2=1;
    private static int TYPE3=2;
    private static int TYPE4=3;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
List<CatagoryBean.DataBean> listc;
private Context context;
private GetAdBean list;
    private List<String> info;
    List<String > listi=new ArrayList<>();
    private type3viewHodel type3;
    private FenleiAdapter fenleiAdapter;

    public MyAdapter(Context context, GetAdBean list, List<CatagoryBean.DataBean> listc) {
        this.context = context;
        this.list = list;
        this.listc=listc;

    }
    //时间
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                   type3.tv_hour.setText("0"+mHour+"");
                }else {
                    type3.tv_hour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    type3.tv_minute.setText("0"+mMin+"");
                }else {
                    type3.tv_minute.setText(mMin+"");
                }
                if (mSecond<10){
                   type3.tv_second.setText("0"+mSecond+"");
                }else {
                    type3.tv_second.setText(mSecond+"");
                }
            }
        }
    };



    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        if (viewType==TYPE1){
            View view= LayoutInflater.from(context).inflate(R.layout.xbanner,null);
            viewHolder=new type1viewHodel(view);
        }else if(viewType==TYPE2){
            View view=LayoutInflater.from(context).inflate(R.layout.sy_getview,null);
            viewHolder=new type2viewHodel(view);
        }else if (viewType==TYPE3){
            View view=LayoutInflater.from(context).inflate(R.layout.sy_review,null);
            viewHolder=new type3viewHodel(view);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof type1viewHodel){
            type1viewHodel type1= (type1viewHodel) holder;
             List<GetAdBean.DataBean> data = list.getData();
            for (int i = 0; i < data.size(); i++) {
                String icon = data.get(i).getIcon();
                listi.add(icon);
            }
            type1.banner.setImages(listi);
            type1.banner.setImageLoader(new GlideImageLoader());
            type1.banner.setBannerAnimation(Transformer.DepthPage);
            type1.banner.setDelayTime(2000);
//启动banner
            type1.banner.start();
        } else if (holder instanceof type2viewHodel) {
            type2viewHodel type2= (type2viewHodel) holder;
            type2.vip.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false));
            fenleiAdapter = new FenleiAdapter(context, listc);
            type2.vip.setAdapter(fenleiAdapter);

        }
        else if (holder instanceof type3viewHodel) {
            type3 = (type3viewHodel) holder;
            //秒杀
            type3.miao_rlv.setLayoutManager(new GridLayoutManager(context, 12));
            GetAdBean.MiaoshaBean miaosha = list.getMiaosha();
            startRun();
            Miaoshaadpter miaoshaadpter = new Miaoshaadpter(context, miaosha.getList());
            type3.miao_rlv.setAdapter(miaoshaadpter);
            miaoshaadpter.setMiaoshaadpter(new Miaoshaadpter.OnClickms() {
                @Override
                public void onClickms(int position) {
                    Intent intent = new Intent(context, TwoDetactivity.class);
                    intent.putExtra("pid", list.getMiaosha().getList().get(position).getPid() + "");
                    context.startActivity(intent);
                }
            });

            //推荐
            type3.rlv.setLayoutManager(new GridLayoutManager(context, 2));
             GetAdBean.TuijianBean tuijian = list.getTuijian();
             TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(tuijian.getList(), context);
            type3.rlv.setAdapter(tuiJianAdapter);
            tuiJianAdapter.setOnclickSpflAdpter(new TuiJianAdapter.OnClickfl() {
                @Override
                public void onClickxq(int position) {
                    Intent intent = new Intent(context, TwoDetactivity.class);
                    intent.putExtra("pid", list.getTuijian().getList().get(position).getPid() + "");
                    context.startActivity(intent);
                }
            });
            info=new ArrayList<>();

            info.add("公告内容1");
            info.add("公告内容2");
            info.add("公告内容367ggjjkk");
            info.add("公告内容4");
            type3.marqueeView.startWithList(info);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE1;
        } else if (position == 1) {
            return TYPE2;
        } else {
            return TYPE3;
        }

    }
class type1viewHodel extends ViewHolder {
    Banner banner;
    public type1viewHodel(View itemView) {
        super(itemView);
        banner=itemView.findViewById(R.id.xbanner);

    }
}
    class type2viewHodel extends ViewHolder {
       RecyclerView vip;
        public type2viewHodel(View itemView) {
            super(itemView);
            vip=itemView.findViewById(R.id.recy);
        }
    }
    class type3viewHodel extends ViewHolder {
    TextView tv_hour,tv_minute,tv_second;
    RecyclerView miao_rlv;
    MarqueeView marqueeView;
        RecyclerView rlv;
        public type3viewHodel(View itemView) {
            super(itemView);
            tv_hour=itemView.findViewById(R.id.tv_hour);
            tv_minute=itemView.findViewById(R.id.tv_minute);
            tv_second=itemView.findViewById(R.id.tv_second);
            miao_rlv=itemView.findViewById(R.id.miao_rlv);
            rlv=itemView.findViewById(R.id.rlv);
            marqueeView = itemView.findViewById(R.id.marqueeView);
        }
    }


    @Override
    public int getItemCount() {
        if (TYPE1 == 0) {
            return list.getData().size();
        }
        else if (TYPE2== 2) {
            return list.getMiaosha().getList().size();
        }else {
            return list.getTuijian().getList().size();
        }
    }
}
