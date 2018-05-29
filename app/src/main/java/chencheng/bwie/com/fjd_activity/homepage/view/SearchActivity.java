package chencheng.bwie.com.fjd_activity.homepage.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import chencheng.bwie.com.fjd_activity.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFanhui;
    /**
     * 搜索
     */
    private Button mBut;
    private LinearLayout mLin;
    private EditText mName;
    private String string;
    private String[] mCustomItems = new String[]{"修改姓名", "删除","取消"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mFanhui.setOnClickListener(this);
        mBut = (Button) findViewById(R.id.but);
        mBut.setOnClickListener(this);
        mLin = (LinearLayout) findViewById(R.id.lin);
        mName = (EditText) findViewById(R.id.name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fanhui:
                break;
            case R.id.but:
                break;
        }
    }
}
