package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.R;
import chencheng.bwie.com.fjd_activity.my.adapter.Myadapter;
import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;
import chencheng.bwie.com.fjd_activity.my.presenter.Presenter;

public class OrderTwoActivity extends AppCompatActivity implements Iview,View.OnClickListener{
    private XRecyclerView xrc;
    private Myadapter myadapter;
    private Presenter presenter;
    private ImageView iv;
    private PopupWindow popupWindow;
    String[] arr={"待支付","已支付","已取消"};
    List<OrdersBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_two);

        //初始化id
        innitid();
        presenter = new Presenter();
        presenter.getmv(this,this,new Mymode());

        //上啦加载下拉刷新的方法
        shuaxinjiazai();

        //设置图片的点击事件
        iv.setOnClickListener(this);

    }

    private void shuaxinjiazai() {
        xrc.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新的方法
            @Override
            public void onRefresh() {
                //刷新时从新调用下适配器
                Presenter presenter=new Presenter();
                presenter.getmv(OrderTwoActivity.this,OrderTwoActivity.this,new Mymode());
                //关闭刷新
                xrc.refreshComplete();
            }

            //下拉加载的方法
            @Override
            public void onLoadMore() {
                Toast.makeText(OrderTwoActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
                //调用p层加载更多的方法
                presenter.pjiazaigengduo(OrderTwoActivity.this,OrderTwoActivity.this,new Mymode());
                xrc.refreshComplete();
            }
        });
    }

    private void innitid() {
        xrc = findViewById(R.id.xrc);
        iv = findViewById(R.id.iv);
    }

    //默认设置适配器的方法
    @Override
    public void setmyadapter(Context context, List<OrdersBean.DataBean> list) {
        //给全局变量赋值
        this.list=list;
        myadapter = new Myadapter(context,list,this);
        xrc.setAdapter(myadapter);
        xrc.setLayoutManager(new LinearLayoutManager(this));
    }

    //上啦加载更多的方法
    @Override
    public void vjiazaigengduo(Context context,List<OrdersBean.DataBean> list) {
        //adapter适配器里面必须有set   get方法
        List<OrdersBean.DataBean> data = myadapter.getList();
        data.addAll(list);
        myadapter.notifyDataSetChanged();

    }

    //d点击图片时 设置适配器的方法
    @Override
    public void settuadapter(Context context, List<OrdersBean.DataBean> list) {
        myadapter = new Myadapter(context,list,this);
        xrc.setAdapter(myadapter);
    }

    //图片的点击事件
    @Override
    public void onClick(View v) {
        //pop_item  布局为listview
        View view = LayoutInflater.from(OrderTwoActivity.this).inflate(R.layout.pop_item,null);
        ListView listview = view.findViewById(R.id.listview);
        //为listview设置适配器
        listview.setAdapter(new BaseAdapter() {

            private TextView textView;

            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    textView = new TextView(OrderTwoActivity.this);
                }
                textView.setText(arr[position]);
                textView.setTextSize(30);
                return textView;
            }
        });

        //  popupWindow显示在底部
        popupWindow = new PopupWindow(view, 140, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(iv,0,10);

        //设置listview的点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            List<OrdersBean.DataBean> list1=new ArrayList<OrdersBean.DataBean>();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arr[position].equals("待支付")){
                    //遍历集合的数据
                    for (int j = 0;j<list.size(); j++) {

                        //判断数据的值  ==0  取出来放到新的集合  用新的集合重新设置适配器
                        if (list.get(j).getStatus()==0.0){

                            OrdersBean.DataBean dataBean = list.get(j);
                            list1.add(dataBean);

                            //从新设置适配器
                            presenter.diantu(OrderTwoActivity.this, OrderTwoActivity.this, new Mymode(), list1);


                        }
                    }
                    popupWindow.dismiss();
                }


                if (arr[position].equals("已支付")){
                    //遍历集合的数据
                    for (int j = 0;j<list.size(); j++) {
                        //判断数据的值  ==1.0  取出来放到新的集合  用新的集合重新设置适配器
                        if (list.get(j).getStatus()==1.0){
                            OrdersBean.DataBean dataBean = list.get(j);
                            list1.add(dataBean);
                            //从新设置适配器
                            presenter.diantu(OrderTwoActivity.this,OrderTwoActivity.this,new Mymode(),list1);


                        }
                    }


                    popupWindow.dismiss();
                }

                if (arr[position].equals("已取消")){
                    //遍历集合的数据
                    for (int j = 0;j<list.size(); j++) {
                        //判断数据的值  ==2.0  取出来放到新的集合  用新的集合重新设置适配器
                        if (list.get(j).getStatus()==2.0){
                            OrdersBean.DataBean dataBean = list.get(j);
                            list1.add(dataBean);
                            //从新设置适配器
                            presenter.diantu(OrderTwoActivity.this,OrderTwoActivity.this,new Mymode(),list1);


                        }
                    }


                    popupWindow.dismiss();
                }
            }
        });

    }
}
