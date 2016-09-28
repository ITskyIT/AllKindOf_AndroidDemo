package com.tian.app.daydaystudy.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 工具类
 */
public class Tool {
    Context context;
    public Tool(Context context) {
        this.context=context;
    }

    /**
     * 吐司
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public void showToast(int msgResId){
        Toast.makeText(context, msgResId, Toast.LENGTH_SHORT).show();
    }
}
