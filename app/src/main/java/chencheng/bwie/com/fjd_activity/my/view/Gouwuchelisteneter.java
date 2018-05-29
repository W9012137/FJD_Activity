package chencheng.bwie.com.fjd_activity.my.view;

import java.util.List;

import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Gouwuchelisteneter {
    //购物车数据监听事件
    void gouwucheListeneter(List<OrdersBean.DataBean> data);
}
