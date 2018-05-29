package chencheng.bwie.com.fjd_activity.classification.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.right_tvitem,null);
            holder.tv=view.findViewById(R.id.tv);
            holder.ll=view.findViewById(R.id.ll);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        final CatagoryBean.DataBean dataBean = list.get(i);
        holder.tv.setText(dataBean.getName());
        if (dataBean.isIscheckd()){
            holder.ll.setBackgroundColor(Color.parseColor("#333333"));
        }else{
            holder.ll.setBackgroundColor(Color.WHITE);
        }
        return view;
    }
    class ViewHolder{
        TextView tv;
        LinearLayout ll;
    }
    public void changeItemSelect(int position){
        for (int i=0;i<list.size();i++){
            final CatagoryBean.DataBean dataBean = list.get(i);
            dataBean.setIscheckd(false);
        }
        final CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setIscheckd(true);
        notifyDataSetChanged();
    }
}
