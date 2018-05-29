package chencheng.bwie.com.fjd_activity.classification.model;

import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public interface IListModel {
    void GetList(String pscid,String page,String sort , NetListenter<LiatBean> netListenter);
}
