package chencheng.bwie.com.fjd_activity.classification.model;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public interface ICatagoryModel {
    void getCata(NetListenter<CatagoryBean> netListenter);
    void getCid(String cid, NetListenter<RightBean> netListenter);
}
