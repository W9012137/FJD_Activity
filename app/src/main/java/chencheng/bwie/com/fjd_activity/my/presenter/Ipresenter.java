package chencheng.bwie.com.fjd_activity.my.presenter;

import android.content.Context;

import java.util.List;

import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;
import chencheng.bwie.com.fjd_activity.my.view.Imode;
import chencheng.bwie.com.fjd_activity.my.view.Iview;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Ipresenter {
    void getmv(Context context, Iview iview, Imode imode);

    //上啦加载更多的方法
    void pjiazaigengduo(Context context, Iview iview, Imode imode);

    //修改的方法
    void upgood(Context context, Iview iview, Imode imode, int id);

    //点击图片时的适配器
    void diantu(Context context, Iview iview, Imode imode, List<OrdersBean.DataBean> list1);
}
