package chencheng.bwie.com.fjd_activity.classification.presenter;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.classification.model.CatagoryModel;
import chencheng.bwie.com.fjd_activity.classification.model.ICatagoryModel;
import chencheng.bwie.com.fjd_activity.classification.view.IViewTwo;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class LifetPresnter {
    private IViewTwo iViewTwo;
    private ICatagoryModel iCatagoryModel;

    public LifetPresnter(IViewTwo iViewTwo) {
        this.iViewTwo = iViewTwo;
        iCatagoryModel=new CatagoryModel();
    }
    public void Pdstry(){
        if (iViewTwo!=null){
            iViewTwo=null;
        }
    }
    public void rv_list(){
        iCatagoryModel.getCata(new NetListenter<CatagoryBean>() {
            @Override
            public void onSccess(CatagoryBean catagoryBean) {
                final List<CatagoryBean.DataBean> data = catagoryBean.getData();
                iViewTwo.ShowList(data);
                showR(catagoryBean.getData().get(0).getCid()+"");
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
    public void showR(String cid){
        iCatagoryModel.getCid(cid, new NetListenter<RightBean>() {
            @Override
            public void onSccess(RightBean rightBean) {
                List<List<RightBean.DataBean.ListBean>> child=new ArrayList<List<RightBean.DataBean.ListBean>>();
                List<RightBean.DataBean> group=rightBean.getData();
                for (int i=0;i<group.size();i++){
                    final RightBean.DataBean dataBean = group.get(i);
                    final List<RightBean.DataBean.ListBean> list = dataBean.getList();
                    child.add(list);
                }
                iViewTwo.showright(group,child);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
