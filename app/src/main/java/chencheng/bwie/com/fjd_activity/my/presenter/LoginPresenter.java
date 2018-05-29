package chencheng.bwie.com.fjd_activity.my.presenter;

import chencheng.bwie.com.fjd_activity.my.bean.LogBean;
import chencheng.bwie.com.fjd_activity.my.model.ILogModel;
import chencheng.bwie.com.fjd_activity.my.model.LogModel;
import chencheng.bwie.com.fjd_activity.my.view.ILogView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

/**
 * Created by dell on 2018/4/10.
 */

public class LoginPresenter {
    private ILogView iLogView;
    private ILogModel iLogModel;

    public LoginPresenter(ILogView iLogView) {
        this.iLogView = iLogView;
        iLogModel=new LogModel();
    }
    public void Pdstry(){
        if (iLogView!=null){
            iLogView=null;
        }
    }
   public void log(String mobile, String password){
        iLogModel.getLogin(mobile, password, new NetListenter<LogBean>() {
            @Override
            public void onSccess(LogBean logBean) {
                iLogView.showlofin(logBean);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
   }
}
