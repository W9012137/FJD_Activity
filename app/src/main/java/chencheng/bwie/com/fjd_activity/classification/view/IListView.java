package chencheng.bwie.com.fjd_activity.classification.view;

import java.util.List;

import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;

public interface IListView {
    void ShowList(List<LiatBean.DataBean> dataBeans, String pscid);
}
