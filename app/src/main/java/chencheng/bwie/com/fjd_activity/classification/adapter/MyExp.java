package chencheng.bwie.com.fjd_activity.classification.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.classification.view.Rv_ListActivity;

public class MyExp extends BaseExpandableListAdapter {
    private Context context;
    private List<RightBean.DataBean> group;
    private List<List<RightBean.DataBean.ListBean>> child;
    private LayoutInflater inflater;
    public MyExp(Context context, List<RightBean.DataBean> group, List<List<RightBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder holder;
        if (view==null){
            holder=new GroupViewHolder();
            view=inflater.inflate(R.layout.ex_group,null);
            holder.tv=view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder= (GroupViewHolder) view.getTag();
        }
        final RightBean.DataBean dataBean = group.get(i);
        holder.tv.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder;
        if (view==null){
            holder=new ChildViewHolder();
            view=inflater.inflate(R.layout.ex_chiod,null);
            holder.gv=view.findViewById(R.id.gv);
            view.setTag(holder);
        }else{
            holder= (ChildViewHolder) view.getTag();
        }
        final List<RightBean.DataBean.ListBean> listBeen = child.get(i1);
        final GvAdapter gvAdapter = new GvAdapter(context, listBeen);
        holder.gv.setAdapter(gvAdapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "position:" + i, Toast.LENGTH_SHORT).show();
                Intent it=new Intent(context,Rv_ListActivity.class);
                it.putExtra("pscid",listBeen.get(i).getPscid()+"");
                context.startActivity(it);

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class GroupViewHolder{
        TextView tv;
    }
    class ChildViewHolder{
        GridView gv;
    }
}
