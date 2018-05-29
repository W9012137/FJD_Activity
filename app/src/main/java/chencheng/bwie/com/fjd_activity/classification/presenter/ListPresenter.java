package chencheng.bwie.com.fjd_activity.classification.presenter;

import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;
import chencheng.bwie.com.fjd_activity.classification.model.IListModel;
import chencheng.bwie.com.fjd_activity.classification.model.ListModel;
import chencheng.bwie.com.fjd_activity.classification.view.IListView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class ListPresenter {
    private IListView iListView;
    private IListModel iListModel;

    public ListPresenter(IListView iListView) {
        this.iListView = iListView;
        iListModel=new ListModel();
    }
    public void Pdstry(){
        if (iListView!=null){
            iListView=null;
        }
    }
    public void showl(final String pscid, String page, String sort ){
        iListModel.GetList(pscid,page,sort, new NetListenter<LiatBean>() {
            @Override
            public void onSccess(LiatBean productsBean) {

                iListView.ShowList(productsBean.getData(),pscid);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
