package com.example.zahan.kuetbus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Zahan on 1/18/2016.
 */
public class Fragment_temp extends Fragment {

    public Fragment_temp() {
    }

    private WebView wv1;
    private ProgressDialog progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp, container,
                false);
        wv1 = (WebView) view.findViewById(R.id.web2);
        wv1.getSettings().setBuiltInZoomControls(true);
        progressBar = ProgressDialog.show(getActivity(), "Temporary schedule", "Loading...");
        setweb();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Temporary");


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = ProgressDialog.show(getActivity(), "Temporary Schedule", "Reloading...");
                setweb();


            }
        });
        return view;
    }

    public void setweb() {
        WebSettings settings = wv1.getSettings();
        settings.setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wv1.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

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
        wv1.loadUrl("http://qahhar.com/bus/temp.html");
    }

}
