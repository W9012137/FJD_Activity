package chencheng.bwie.com.fjd_activity.my.view;

import java.util.Map;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Imode {
    //获取接口数据的方法
    void getnetjsonbean(String uri, Map<String, String> map, Gouwuchelisteneter gouwuchelisteneter);

    //修改的方法
    void xiugai(String uri, Map<String, String> map, Xiugailisteneter xiugailisteneter);
}
