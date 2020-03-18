package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class recive_shouts extends Fragment{
	
	ArrayList<Integer> rollno = new ArrayList<Integer>();
	ArrayList<Integer> rollnoabsent = new ArrayList<Integer>();
	FileOutputStream fos;
	String currentDateandTime , str1 , str2;
	ListView list,list2;
	int from,to;
	ArrayAdapter<Integer> adapter , adapter2;
	int j,k;
	TextView countertext , countertext2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.recived_shouts,
				container, false);
		
		Button presentbut = (Button)rootView.findViewById(R.id.button2);
		Button absentbut = (Button)rootView.findViewById(R.id.button1);
		Button button = (Button)rootView.findViewById(R.id.button3);
		TextView rangetext = (TextView)rootView.findViewById(R.id.range);

		SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getActivity());

		String strp1,strp2;
		strp1 = spf.getString("fromrange" , "1");
		strp2 = spf.getString("torange" , "82");


			from = Integer.parseInt(strp1);
			to = Integer.parseInt(strp2);


		rangetext.setText("Range : " + from + "-" + to);

		Bundle b = this.getArguments();
		
		
		String[] wifi = b.getStringArray("wifistring");

		 countertext = (TextView)rootView.findViewById(R.id.textView5);
		 countertext2 = (TextView)rootView.findViewById(R.id.textView6);
			list = (ListView)rootView.findViewById(R.id.listView1);
		 list2 = (ListView)rootView.findViewById(R.id.listView2);
		List<String> absent = new ArrayList<String>();
		absent = Arrays.asList(wifi);
		
		
			for(k=from;k<=to;k++)
			{
				if(absent.contains(""+k+""))
				{
					rollno.add(k);

				}
			}
			
			for(k=from;k<=to;k++)
			{
				if(rollno.contains(k))
				{

				}
				else
				{
					rollnoabsent.add(k);
				}
			}
			
			 adapter = new ArrayAdapter<Integer>(getActivity(),R.layout.simple_listpresent,R.id.txtHeader,rollno);
			 adapter2 = new ArrayAdapter<Integer>(getActivity(),R.layout.simple_listabsent,R.id.txtHeader,rollnoabsent);
			
		countertext.setText("Counter : " + (rollno.size()));
		countertext2.setText("Counter : " + (rollnoabsent.size()));
		list.setAdapter(adapter);
		list2.setAdapter(adapter2);


		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				rollnoabsent.add(rollno.get(arg2));
				rollno.remove(arg2);
				str1 = rollno.toString();
				str2 = rollnoabsent.toString();
				adapter.notifyDataSetChanged();
				adapter2.notifyDataSetChanged();
				Collections.sort(rollno);
				Collections.sort(rollnoabsent);
				countertext.setText("Counter : " + (rollno.size()));
				countertext2.setText("Counter : " + (rollnoabsent.size()));
				
			}
		});
		
		list2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				rollno.add(rollnoabsent.get(arg2) );
				rollnoabsent.remove(arg2);
				str1 = rollno.toString();
				str2 = rollnoabsent.toString();
				adapter2.notifyDataSetChanged();
				adapter.notifyDataSetChanged();
				Collections.sort(rollno);
				Collections.sort(rollnoabsent);
				countertext.setText("Counter : " + (rollno.size()));
				countertext2.setText("Counter : " + (rollnoabsent.size()));
			}
		});
		
		
		
		 str1 = rollno.toString();
		 str2 = rollnoabsent.toString();
		
		

	
		
		 presentbut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy E HH:mm:ss");
					currentDateandTime = sdf.format(new Date());
					fos = getActivity().openFileOutput(currentDateandTime + "_Present", Context.MODE_PRIVATE);
					fos.write(str1.getBytes());
					fos.close();


					//snackbar
					Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Present Shouts Saved", Snackbar.LENGTH_LONG);
					View view = snack.getView();
					TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
					tv.setTextColor(Color.parseColor("#FFFFFF"));

					tv.setGravity(Gravity.CENTER_HORIZONTAL);
					snack.show();


				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		
			absentbut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy E HH:mm:ss");
					currentDateandTime = sdf.format(new Date());
					fos = getActivity().openFileOutput(currentDateandTime + "_Absent", Context.MODE_PRIVATE);
					fos.write(str2.getBytes());
					fos.close();


					Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Absents Shouts Saved", Snackbar.LENGTH_LONG);
					View view = snack.getView();
					TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
					tv.setTextColor(Color.parseColor("#FFFFFF"));

					tv.setGravity(Gravity.CENTER_HORIZONTAL);
					snack.show();


				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
			
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Fragment fm = new displaydata();
					FragmentManager manger = getFragmentManager();
					manger.beginTransaction().replace(R.id.container,fm).commit();
					
				}
			});
		
			
		
		return rootView;
	}

}
