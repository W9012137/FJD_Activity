package chencheng.bwie.com.fjd_activity.my.view;

import chencheng.bwie.com.fjd_activity.my.bean.RegisterBean;

/**
 * Created by dell on 2018/4/10.
 */

public interface IRegisterview {
    void showregister(RegisterBean registerBean);
    public String getAccount();
    public String getPwd();


    public void finishAc();


    public void show(String str);
}
