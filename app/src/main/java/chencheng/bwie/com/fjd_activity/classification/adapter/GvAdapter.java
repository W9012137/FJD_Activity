package chencheng.bwie.com.fjd_activity.classification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;

class GvAdapter extends BaseAdapter {
    private Context context;
    private List<RightBean.DataBean.ListBean> list;
    private final LayoutInflater inflater;

    public GvAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.view_item, null);
            holder.sv = view.findViewById(R.id.iv);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        final RightBean.DataBean.ListBean listBean = list.get(position);
        holder.tv.setText(listBean.getName());
        holder.sv.setImageURI(listBean.getIcon());
        return view;
    }

    class ViewHolder {
        SimpleDraweeView sv;
        TextView tv;
    }
}
