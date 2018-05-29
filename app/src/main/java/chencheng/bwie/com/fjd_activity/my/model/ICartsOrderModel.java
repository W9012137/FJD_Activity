package chencheng.bwie.com.fjd_activity.my.model;

import chencheng.bwie.com.fjd_activity.my.bean.CatsOrdersBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public interface ICartsOrderModel {
    void getOrder(String uid, String price, String token, NetListenter<CatsOrdersBean> onNetListner);
}
