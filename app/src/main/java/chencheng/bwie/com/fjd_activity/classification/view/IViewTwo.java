package chencheng.bwie.com.fjd_activity.classification.view;

import java.util.List;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;

public interface IViewTwo {
    void ShowList(List<CatagoryBean.DataBean> catagoryBean);
    void showright(List<RightBean.DataBean> group, List<List<RightBean.DataBean.ListBean>> child);
}
