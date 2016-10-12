package com.tian.app.daydaystudy.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.view.FlikerProgressBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 资源来自：https://github.com/LineChen/FlickerProgressBar
 */
@ContentView(R.layout.activity_progress_bar)
public class ProgressBarActivity extends BaseActivity {
    @ViewInject(R.id.flikerbar)
    FlikerProgressBar flikerProgressBar;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void init() {
        flikerProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flikerProgressBar.isFinish()) {
                    flikerProgressBar.toggle();
                }
            }
        });

        downLoad();
       /* Observable.interval(0,200,TimeUnit.MILLISECONDS)
                .range(0, 101)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        flikerProgressBar.setProgress(integer);
                        if (integer == 100) {
                            flikerProgressBar.finishLoad();
                        }
                    }
                });*/

    }

   private void downLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(200);
                        Message message = handler.obtainMessage();
                        message.arg1 = i + 1;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
