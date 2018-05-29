package chencheng.bwie.com.fjd_activity.green;

import chencheng.bwie.com.fjd_activity.MyApp;

public class GreenDaoManager {
    private static GreenDaoManager mGreenDaoManager;
    private DaoMaster mMaster;
    private DaoSession mDaosession;

    public GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(MyApp.getContext(),"user-db",null);
        mMaster=new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaosession=mMaster.newSession();
    }
    public static GreenDaoManager getInstance(){
        if(mGreenDaoManager==null){
            mGreenDaoManager=new GreenDaoManager();
        }
        return mGreenDaoManager;
    }
    public DaoMaster getMaster(){
        return mMaster;
    }
    public DaoSession getDaosession(){
        return mDaosession;
    }
    public DaoSession getNewDasession(){
        mDaosession=mMaster.newSession();
        return mDaosession;
    }
}
