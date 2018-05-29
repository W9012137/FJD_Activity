package chencheng.bwie.com.fjd_activity.theshoppingcart.view;

import java.util.List;

import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;

public interface ICartsView {
    public void showList(List<CartsBean.DataBean> groupList, List<List<CartsBean.DataBean.ListBean>> childList , String uid);
    void showDelete(DeleteBean deleteBean);

}
