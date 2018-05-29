package chencheng.bwie.com.fjd_activity.homepage.presenter;

import java.util.List;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.model.CatagoryModel;
import chencheng.bwie.com.fjd_activity.classification.model.ICatagoryModel;
import chencheng.bwie.com.fjd_activity.homepage.view.IOneView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class LefterPresnter {
    private IOneView iViewTwo;
    private ICatagoryModel iCatagoryModel;

    public LefterPresnter(IOneView iViewTwo) {
        this.iViewTwo = iViewTwo;
        iCatagoryModel=new CatagoryModel();
    }
    public void Pdstry(){
        if (iViewTwo!=null){
            iViewTwo=null;
        }
    }
    public void rv_list() {
        iCatagoryModel.getCata(new NetListenter<CatagoryBean>() {
            @Override
            public void onSccess(CatagoryBean catagoryBean) {
                final List<CatagoryBean.DataBean> data = catagoryBean.getData();
                iViewTwo.LefiView(data);

            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
