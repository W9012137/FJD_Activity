package chencheng.bwie.com.fjd_activity.model;

import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public interface IGetAdModel {
    void ShowAD(NetListenter<GetAdBean> netListenter);
}
