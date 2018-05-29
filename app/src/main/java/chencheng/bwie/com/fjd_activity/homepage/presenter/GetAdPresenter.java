package chencheng.bwie.com.fjd_activity.homepage.presenter;

import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.homepage.view.IOneView;
import chencheng.bwie.com.fjd_activity.model.GetAdModel;
import chencheng.bwie.com.fjd_activity.model.IGetAdModel;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class GetAdPresenter {
    private IOneView iOneView;
    private IGetAdModel iGetAdModel;

    public GetAdPresenter(IOneView iOneView) {
        this.iOneView = iOneView;
        iGetAdModel=new GetAdModel();
    }
    public void Pdstry(){
        if (iOneView!=null){
            iOneView=null;
        }
    }
    public void GetShow(){
        iGetAdModel.ShowAD(new NetListenter<GetAdBean>() {
            @Override
            public void onSccess(GetAdBean getAdBean) {
                iOneView.showView(getAdBean);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
