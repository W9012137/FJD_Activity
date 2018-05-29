package chencheng.bwie.com.fjd_activity.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import chencheng.bwie.com.fjd_activity.R;

public class MainActivity extends AppCompatActivity {
    private Timer timer = new Timer();
    private int pre = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1 && msg.arg1 == 1) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("userloginflag", 4);

                startActivity(intent);

                finish();

            }
        }
    };
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        SharedPreferences sp = getSharedPreferences("count", MODE_PRIVATE);
        int count = sp.getInt("count", 0);
        if (count == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Message message = Message.obtain();
                            message.what = 1;
                            message.arg1 = pre;
                            pre--;
                            handler.sendMessage(message);
                        }
                    }, 1000, 1000);
                }
            }).start();
        }
    }


    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
    }
}
