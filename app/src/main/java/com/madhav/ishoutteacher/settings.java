package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by madhav on 08-02-2016.
 */
public class settings extends Fragment {

    EditText name,work,from,to;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.settings,
                container, false);

        name = (EditText)rootView.findViewById(R.id.nametag);
        work = (EditText)rootView.findViewById(R.id.workat);
        from = (EditText)rootView.findViewById(R.id.fromrange);
        to = (EditText)rootView.findViewById(R.id.torange);


        SharedPreferences spf2 = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String updatedname = spf2.getString("strname", "");
        String updatework = spf2.getString("strwork" , "");
        String updatedrangefrom = spf2.getString("fromrange" , "");
        String updatedrangeto = spf2.getString("torange" , "");

        if(!updatedname.isEmpty())
        {
            name.setText(updatedname);
        }
        if(!updatework.isEmpty())
        {
            work.setText(updatework);
        }

        if(!updatedrangefrom.isEmpty())
        {
            from.setText(updatedrangefrom);
        }

        if(!updatedrangeto.isEmpty())
        {
            to.setText(updatedrangeto);
        }


        Button update = (Button)rootView.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String strname = name.getText().toString();
                String strwork = work.getText().toString();

                SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor edit = spf.edit();
                edit.putString("strname",strname);
                edit.putString("strwork",strwork);
                edit.commit();




                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Profile Updated!", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();

            }
        });


        Button rangebut = (Button)rootView.findViewById(R.id.button);
        rangebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fromrange = from.getText().toString();
                String torange = to.getText().toString();

                SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor edit = spf.edit();
                edit.putString("fromrange",fromrange);
                edit.putString("torange",torange);
                edit.commit();



                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Range Updated!", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();
            }
        });
        return  rootView;
    }
}
