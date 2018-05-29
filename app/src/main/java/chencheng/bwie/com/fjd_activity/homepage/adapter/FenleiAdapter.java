package chencheng.bwie.com.fjd_activity.homepage.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;

public class FenleiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CatagoryBean.DataBean> list;
    private Context context;

    public FenleiAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.sy_rvitem, null);

        return new MyHodel(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHodel myHodel = (MyHodel) holder;
        String[] split = list.get(position).getIcon().split("\\|");
        Uri parse = Uri.parse(split[0]);
        myHodel.im.setImageURI(parse);
        myHodel.tv.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHodel extends RecyclerView.ViewHolder {

        private final SimpleDraweeView im;
        private final TextView tv;

        public MyHodel(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.itemonf_sim);
            tv = itemView.findViewById(R.id.itemonef_tv);

        }
    }
}
