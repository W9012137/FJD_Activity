package chencheng.bwie.com.fjd_activity.homepage.view;

import java.util.List;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;

public interface IOneView {
    void showView(GetAdBean getAdBean);
    void LefiView(List<CatagoryBean.DataBean> catagoryBean);
}
