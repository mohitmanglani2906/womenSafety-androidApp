package com.example.mohit2906.womensafety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.logging.Logger;

public class Send_Message extends AppCompatActivity {

    private WebView myWebView = null;
    Toolbar toolbar_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__message);

//        myWebView = (WebView)findViewById(R.id.webViewSendMessage);
//        myWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                try {
//                    view.stopLoading();
//                } catch (Exception e) {
//                }
//
//                if (view.canGoBack()) {
//                    view.goBack();
//                }
//                view.loadUrl("https://mohit2906-chat-app.herokuapp.com");
//
//            }
//        });
          // myWebView.loadUrl("https://mohit2906-chat-app.herokuapp.com");
            setupToolBar();
    }

    private class SendMessageWebView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public void setupToolBar()
    {
        toolbar_send_message = (Toolbar)findViewById(R.id.toolbar_added_contacts);
        setSupportActionBar(toolbar_send_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar_send_message.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });

    }
}
