package chencheng.bwie.com.fjd_activity.my.view;

import android.content.Context;

import java.util.List;

import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Iview {
    //设置适配器的方法
    void  setmyadapter(Context context, List<OrdersBean.DataBean> list);
    //上拉加载更多的方法
    void vjiazaigengduo(Context context, List<OrdersBean.DataBean> list);

    //点击图片设置适配器的方法
    void  settuadapter(Context context, List<OrdersBean.DataBean> list);
}
