package com.tian.app.daydaystudy.ui.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;

import com.tian.app.daydaystudy.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_span_string)
public class SpanStringActivity extends BaseActivity {
    @ViewInject(R.id.tv)
    EditText tv;

    @Override
    protected void init() {

    }
    @Event(value = {R.id.underline_btn,R.id.strike_btn,R.id.style_btn,R.id.font_btn,
            R.id.color_btn1,R.id.color_btn2,R.id.url_btn,R.id.image_btn})
    private void ssonClick(View v) {
        switch (v.getId()) {
            case R.id.underline_btn:
                addUnderLineSpan();
                break;
            case R.id.strike_btn:
               addStrikeSpan();
                break;
            case R.id.style_btn:
               addStyleSpan();
                break;
            case R.id.font_btn:
                addFontSpan();
                break;
            case R.id.color_btn1:
                addForeColorSpan();
                break;
            case R.id.color_btn2:
                addBackColorSpan();
                break;
            case R.id.url_btn:
               addUrlSpan();
                break;
            case R.id.image_btn:
                addImageSpan();
                break;
        }
    }

    private void addImageSpan() {
        SpannableString spanString = new SpannableString(" ");
        Drawable d=getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
        ImageSpan span=new ImageSpan(d,ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }

    private void addUrlSpan() {
        SpannableString spanString = new SpannableString("超链接");
        URLSpan span = new URLSpan("http://www.baidu.com");
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //文字背景颜色
    private void addBackColorSpan() {
        SpannableString spanString = new SpannableString("颜色2");
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }

    /**
     * 文字颜色
     */
    private void addForeColorSpan() {
        SpannableString spannableString=new SpannableString("颜色1");
        ForegroundColorSpan span=new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spannableString);
    }

    /**+
     * 字体大小
     */
    private void addFontSpan() {
        SpannableString spannableString=new SpannableString("字体大小");
        AbsoluteSizeSpan span=new AbsoluteSizeSpan(20,true);
        spannableString.setSpan(span,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spannableString);
    }

    /**
     * 粗体 斜体
     */
    private void addStyleSpan() {
    SpannableString spannableString=new SpannableString("TXETTEXT");
        StyleSpan span=new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(span,0,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /*StyleSpan span1=new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(span1,4,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        tv.append(spannableString);
    }

    /**
     * 删除线
     */
    private void addStrikeSpan() {
        SpannableString spannableString=new SpannableString("删除线");
        StrikethroughSpan span=new StrikethroughSpan();
        spannableString.setSpan(span,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spannableString);
    }

    /**
     * 下划线
     */
    private void addUnderLineSpan() {
        SpannableString spannableString=new SpannableString("下划线");
        UnderlineSpan span=new UnderlineSpan();
        spannableString.setSpan(span,0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spannableString);
    }
}
