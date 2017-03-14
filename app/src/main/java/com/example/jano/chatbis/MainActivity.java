package com.example.jano.chatbis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    private WebView wv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView)findViewById(R.id.weby);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
            }
        });
        wv.setWebViewClient(
                new WebViewClient(){
                    public boolean shouldOverrideUrlLoading(WebView view, String url){
                        view.loadUrl(url);
                        return false;
                    }
                });
        wv.loadUrl("https://chatnodejs-jano22.c9users.io/");
        wv.addJavascriptInterface(new WebSocketFactory(wv), "WebSocketFactory");
       // mWebView.loadUrl();

    }
}
