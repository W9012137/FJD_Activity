package chencheng.bwie.com.fjd_activity.my.model;


import chencheng.bwie.com.fjd_activity.my.bean.RegisterBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

/**
 * Created by dell on 2018/4/10.
 */

public interface IRegisterModel {
    void getRegister(String mobile, String password, NetListenter<RegisterBean> netListenter);
}
