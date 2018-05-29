package chencheng.bwie.com.fjd_activity.my.presenter;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chencheng.bwie.com.fjd_activity.my.bean.RegisterBean;
import chencheng.bwie.com.fjd_activity.my.model.IRegisterModel;
import chencheng.bwie.com.fjd_activity.my.model.RegisterModel;
import chencheng.bwie.com.fjd_activity.my.view.IRegisterview;
import chencheng.bwie.com.fjd_activity.net.NetListenter;


/**
 * Created by dell on 2018/4/10.
 */

public class RegisterPresenter {
    private IRegisterview iRegisterview;
    private IRegisterModel iRegisterModel;

    public RegisterPresenter(IRegisterview iRegisterview) {
        this.iRegisterview = iRegisterview;
        iRegisterModel=new RegisterModel();
    }
    public void Pdstry(){
        if (iRegisterview!=null){
            iRegisterview=null;
        }
    }
    private boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            //给用户提示，输入的账号不能为空
            iRegisterview.show("请输入密码");
            return false;
        }


        if (pwd.length() != 6) {
            iRegisterview.show("请输入6位密码");
            return false;
        }
        return true;
    }




    /**
     * 验证手机号是否正确
     *
     * @param account
     */
    private boolean checkAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            //给用户提示，输入的账号不能为空
            iRegisterview.show("请输入账号");
            return false;
        }
        if (!isMobileNO(account)) {
            iRegisterview.show("请输入正确的手机号");
            return false;
        }
        return true;
    }




    /*
    判断是否是手机号
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }




    public void register() {
        String account = iRegisterview.getAccount();
        String pwd = iRegisterview.getPwd();
        //判断账号密码是否正确
        if (checkAccount(account) && checkPwd(pwd)) {
            iRegisterModel.getRegister(account, pwd, new NetListenter<RegisterBean>() {
                @Override
                public void onSccess(RegisterBean registerBean) {
                    if (registerBean.getCode().equals("1")) {
                        iRegisterview.show(registerBean.getMsg());
                    } else {
                        iRegisterview.show(registerBean.getMsg());
                        iRegisterview.finishAc();
                    }
                }

                @Override
                public void onFailuer(Exception e) {

                }


            });
        }


    }
}
