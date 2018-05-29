package chencheng.bwie.com.fjd_activity.theshoppingcart.model;

import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;

public interface ICaetsModel {
    void GetCarts(String uid, NetListenter<CartsBean> netListenter);
    void getdelete(String uid, String pid, String token, NetListenter<DeleteBean> onNetListner);
}
