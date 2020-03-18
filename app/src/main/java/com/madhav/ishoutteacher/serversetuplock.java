package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.app.FragmentManager;
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
 * Created by madhav on 8/7/2016.
 */
public class serversetuplock extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.serversetuplock, container , false);

        final EditText password = (EditText) rootview.findViewById(R.id.password);
        Button submit = (Button) rootview.findViewById(R.id.unlockbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString().equals("svpcet"))
                {
                    Fragment frag = new serversetup();
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.container,frag).commit();

                }else
                {
                    //snackbar
                    Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.serversnack), "Wrong Password!", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.parseColor("#FFFFFF"));

                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    snack.show();
                }
            }
        });

        return rootview;
    }


}
