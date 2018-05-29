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


public class Miaoshaadpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GetAdBean.MiaoshaBean.ListBeanX> list;
    OnClickms onClickms;
    public interface OnClickms{
        void onClickms(int position);
    }

    public void setMiaoshaadpter(OnClickms onClickms) {
        this.onClickms = onClickms;
    }

    public Miaoshaadpter(Context context, List<GetAdBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.miaosha_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        GetAdBean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        // myViewHolder.sdv.setImageURI(listBeanX.getImages());
        String str = listBeanX.getImages();
        String[] strs = str.split("\\|");
        for (int i=0;i<2;i++) {
            myViewHolder.sdv.setImageURI(strs[i]);
        }
        myViewHolder.js.setText(listBeanX.getSubhead());
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView js;
        private final SimpleDraweeView sdv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            js = itemView.findViewById(R.id.js);
            sdv = itemView.findViewById(R.id.sdv_miaosha);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
