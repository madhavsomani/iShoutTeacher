package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.guo.duoduo.randomtextview.RandomTextView;
import com.skyfishjy.library.RippleBackground;

import java.util.List;

/**
 * Created by madhav on 10-02-2016.
 */
public class home extends Fragment {

    String wifis[] = null;
    List<ScanResult> wifiScanList;
    BroadcastReceiver reciver;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View rootview = inflater.inflate(R.layout.home , container , false);
      //  final ListView listView = (ListView) rootview.findViewById(R.id.listView1);
       Button wifibut = (Button) rootview.findViewById(R.id.button1);
       final Button recive_shouts = (Button) rootview.findViewById(R.id.button2);
        final WifiManager wifi = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);

        final RandomTextView randomTextView = (RandomTextView) rootview.findViewById(
                R.id.random_textview2);


        if (wifi.isWifiEnabled() == true)
        {
            wifi.setWifiEnabled(false);
        }


        //Radar

        final RippleBackground rippleBackground=(RippleBackground)rootview.findViewById(R.id.content);


        wifibut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (wifi.isWifiEnabled() == false)
                {
                    wifi.setWifiEnabled(true);
                }

                wifi.startScan();




                //snackbar
                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Scan Started!", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();

                rippleBackground.startRippleAnimation();


            }

        });




         reciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                wifiScanList = wifi.getScanResults();
                wifis = new String[wifiScanList.size()];

                for(int i = 0; i < wifiScanList.size(); i++){
                    wifis[i] = ((wifiScanList.get(i).SSID).toString());
                    randomTextView.addKeyWord(wifis[i]);
                    randomTextView.show();

                }
                //listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list,R.id.txtHeader, wifis));

            }
        };

        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getActivity().registerReceiver(reciver,i);


        recive_shouts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(wifis == null)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"First Click Start!" , Toast.LENGTH_SHORT).show();
                }else
                {

                    Bundle b = new Bundle();
                    b.putStringArray("wifistring", wifis);
                    Fragment fm = new recive_shouts();
                    FragmentManager manger = getFragmentManager();
                    fm.setArguments(b);
                    manger.beginTransaction().replace(R.id.container,fm).addToBackStack(null).commit();
                }
            }
        });


        return rootview;

    }

    @Override
    public void onPause() {

        getActivity().unregisterReceiver(reciver);
        super.onPause();
    }

    @Override
    public void onResume() {
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getActivity().registerReceiver(reciver,i);
        super.onResume();
    }



}
