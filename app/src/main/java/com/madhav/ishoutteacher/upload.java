package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by madhav on 7/31/2016.
 */
public class upload extends Fragment {



    String serverurl;
    String attendance;
    String btno,date;


    ArrayList<String> lectureslot = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.upload, container, false);

        Bundle b = this.getArguments();
        attendance = b.getString("attendance").replace("]", "").replace("[", "");


        Button submit = (Button) rootview.findViewById(R.id.submiturlbutton);
        final WebView webview = (WebView) rootview.findViewById(R.id.webView2);

        //shared
        SharedPreferences spf2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor edit = spf2.edit();

        //serverurl
        serverurl = spf2.getString("serverurl", "http://www.svpcet.net/ATTWIFI/atthome.aspx");


        //preset data
        btno = "?uid=" + spf2.getString("strwork", "Null");
        date = (new SimpleDateFormat("MM/dd/yyyy").format(new Date())).toString();
        final String url = serverurl+btno;
        //final String url = "http://192.168.0.100/lol.html";

        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl(url);
        //you sould store ur value of java script in a vairable else it will open a blank page
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                //webview.loadUrl("javascript:document.getElementById('txtlistrollno').value = '"+attendance+"';");
                webview.loadUrl("javascript:var uselessvar =document.getElementById('txtlistrollno').value='"+attendance+"';");

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Attendance Uploaded", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();

                webview.loadUrl("javascript:document.getElementById('btsubmit').click();");
            }
        });

        return rootview;
    }
}
