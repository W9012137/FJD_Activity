package chencheng.bwie.com.fjd_activity.my.model;


import chencheng.bwie.com.fjd_activity.my.bean.LogBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

/**
 * Created by dell on 2018/4/10.
 */

public interface ILogModel {
    void getLogin(String mobile, String password, NetListenter<LogBean> netListenter);


}
