package com.example.consultview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;


public class CustomDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btn_close;
    public WebView wv;
    public String html;

    public CustomDialog(Activity a,String t) {
        super(a);
        html = t;
        this.c = a;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog__webview_custom);


        wv = (WebView) findViewById(R.id.wv_html);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadsImagesAutomatically(true);

        wv.loadData(html, "text/html; charset=utf-8","");


        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}