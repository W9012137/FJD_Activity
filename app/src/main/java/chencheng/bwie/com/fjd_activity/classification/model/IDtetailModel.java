package chencheng.bwie.com.fjd_activity.classification.model;


import chencheng.bwie.com.fjd_activity.classification.bean.AddCartBean;
import chencheng.bwie.com.fjd_activity.classification.bean.DetailBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

/**
 * Created by
 */

public interface IDtetailModel {
    void getAdd(String uid, String pid, String source, String token, NetListenter<AddCartBean> onNetListner);

    void getDetail(String pid, String source, NetListenter<DetailBean> onNetListner);

}
