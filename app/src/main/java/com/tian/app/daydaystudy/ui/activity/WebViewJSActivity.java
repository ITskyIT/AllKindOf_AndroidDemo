package com.tian.app.daydaystudy.ui.activity;

import android.os.Build;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tian.app.daydaystudy.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 网页加载
 * @author tian
 * by 2016 9 28
 *
 *
 */
@ContentView(R.layout.activity_web_view_js)
public class WebViewJSActivity extends BaseActivity {
    @ViewInject(R.id.webView)
    WebView webView;
    @ViewInject(R.id.progressBarWang)
    ProgressBar pBar;
    WebSettings settings;

    @Override
    protected void init() {
    settings=webView.getSettings();
        //取消滚动条
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        settings=webView.getSettings();
        //支持JS调用
        settings.setJavaScriptEnabled(true);
        //设置支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //适应内容大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//适应屏幕
        //适配
        settings.setUseWideViewPort(true);

        settings.setLoadWithOverviewMode(true);
        //不显示控制条
        settings.setDisplayZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setMinimumFontSize(14);
       /**           核心                        */
       webView.loadUrl("file:///android_asset/js.html");
        //js调用Android
        webView.addJavascriptInterface(new JavaInterface(),"jiazai");//第一个参数obj对象中实现JS调用android的实现的方法，
        // 第二个参数interfaceName是向WebView注入一个interfaceName的对象，这个对象绑定的是obj对象，即js的中调用方法的对象。



        /**
         * 利用webview加载，禁止调用浏览器
         */
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pBar.setVisibility(View.GONE);
            }

            /**
             * 错误处理
             * @param view
             * @param errorCode
             * @param description
             * @param failingUrl
             */
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error.html"); //加载本地写好的界面
                     /**
                      * wView.loadUrl("file:///android_asset/error.html");
                        -----打开本包内asset目录下的index.html文件
                        wView.loadUrl("content://com.android.htmlfileprovider/sdcard/error.html");
                        -----打开本地sd卡内的index.html文件
                       wView.loadUrl("http://wap.baidu.com");
                        -----打开指定URL的html文件

                      */
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pBar.setProgress(newProgress);
            }
        });





    }
    public class JavaInterface{
        @JavascriptInterface
        public void sendMessage(String message){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 点击事件
     * @param view
     */
    @Event(value = {R.id.button,R.id.button2})
    private void onClick(View view){
      switch (view.getId()){
          case R.id.button:
              pBar.setVisibility(View.VISIBLE);
              webView.loadUrl("http://www.yemianzhaobudao.com");//此链接是错误链接 notfound
              break;
          case R.id.button2:
              //Android 调用js
              pBar.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT>19){
                    webView.evaluateJavascript("sum('android成功调用了js的方法')", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            mToast.showToast(value);
                        }

                    });
                }else{
                    pBar.setVisibility(View.VISIBLE);
                    //4.4版本一下 获取js的返回值 自己处理吧O(∩_∩)O
                    webView.loadUrl("file:///android_asset/js.html");
                    webView.loadUrl("javascript:sum('android成功调用了js的方法')");
                }
              break;

      }
    }
}
