package chencheng.bwie.com.fjd_activity.classification.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.DetailBean;
import chencheng.bwie.com.fjd_activity.view.Main2Activity;

public class Spxqadpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private DetailBean.DataBean list;
    OnAddCar onAddCar;
    public void setOnAddCar(OnAddCar onAddCar) {
        this.onAddCar = onAddCar;
    }

    public Spxqadpter(Context context, DetailBean.DataBean list) {
        this.context = context;
        this.list = list;
    }

    public interface  OnAddCar {
        public void addCar(int pid);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.spxq_zhitem, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(list.getSubhead());
        myViewHolder.price.setText( "¥"+list.getPrice()+"元");
        myViewHolder.gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("userloginflag", 1);
                context.startActivity(intent);


            }
        });
        myViewHolder.addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddCar.addCar(list.getPid());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final TextView price;
        Button addCar;
        private final Button gwc;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_xq_item);
            price = itemView.findViewById(R.id.price);
            addCar=itemView.findViewById(R.id.jrgw);
            gwc = itemView.findViewById(R.id.bt_gwc);
        }
    }
}
