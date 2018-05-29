package chencheng.bwie.com.fjd_activity.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.view.TwoDetactivity;
import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;

public class TuiJianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<GetAdBean.TuijianBean.ListBean> list;
    private Context context;

    OnClickfl onClickfl;
    public interface OnClickfl{
        void onClickxq(int position);
    }

    public void setOnclickSpflAdpter(OnClickfl onClickfl) {
        this.onClickfl = onClickfl;
    }

    public TuiJianAdapter(List<GetAdBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sy_tuijian, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        GetAdBean.TuijianBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        myViewHolder.sdv.setImageURI(split[0]);
        myViewHolder.tv.setText(listBean.getTitle());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TwoDetactivity.class);
                intent.putExtra("pid", list.get(position).getPid() + "");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv_xq);
            tv = itemView.findViewById(R.id.tv_xq);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
