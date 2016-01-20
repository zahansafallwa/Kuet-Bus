package com.example.zahan.kuetbus;

/**
 * Created by Zahan on 1/18/2016.
 */

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.ProgressDialog;
import android.webkit.WebSettings;
import android.widget.Toast;


public class Fragment_down extends Fragment {


    public Fragment_down() {
    }

    ;
    private WebView wv1;
    private ProgressDialog progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_down, container,
                false);


        wv1 = (WebView) view.findViewById(R.id.wv1);
        wv1.getSettings().setBuiltInZoomControls(true);
        progressBar = ProgressDialog.show(getActivity(), "Download link", "Loading...");
        setweb();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Notice");


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = ProgressDialog.show(getActivity(), "Download link", "Reloading...");
                setweb();


            }
        });


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Downloads");
        return view;
    }

    public void setweb() {
        WebSettings settings = wv1.getSettings();
        settings.setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wv1.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    Uri source = Uri.parse(url);
                    DownloadManager.Request request = new DownloadManager.Request(source);
                    request.setDescription("kuet bus file");
                    request.setTitle("your_file");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download.pdf");
                    DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                }
                view.loadUrl(url);

                return true;
            }

            public void onPageFinished(WebView view, String url) {

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                Toast.makeText(getActivity(), "Connection Error! ", Toast.LENGTH_SHORT).show();

            }
        });
        wv1.loadUrl("http://qahhar.com/bus/download.html");
    }


}