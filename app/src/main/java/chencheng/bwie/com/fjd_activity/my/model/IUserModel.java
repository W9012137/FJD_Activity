package chencheng.bwie.com.fjd_activity.my.model;

import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public interface IUserModel {
    void GetUser(String uid, NetListenter<UserBean> netListenter);
}
