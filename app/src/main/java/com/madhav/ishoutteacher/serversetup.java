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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by madhav on 7/31/2016.
 */
public class serversetup extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.serversetup , container , false);

        final EditText serverurltext = (EditText) rootview.findViewById(R.id.serverurl);
        Button submit = (Button) rootview.findViewById(R.id.serverurlbutton);

        SharedPreferences spf2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor edit = spf2.edit();

        String serverurl = spf2.getString("serverurl", "http://www.svpcet.net/ATTWIFI/atthome.aspx");

        serverurltext.setText(serverurl);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("serverurl" , serverurltext.getText().toString());
                edit.commit();

                //snackbar
                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.serversnack), "parameter updated", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();


            }
        });





        return rootview;
    }
}
