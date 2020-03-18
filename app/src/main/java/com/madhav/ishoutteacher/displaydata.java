package com.madhav.ishoutteacher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class displaydata extends ListFragment{
	String[] str2 ;
	ListView list;
	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
    {

		final View rootView = inflater.inflate(R.layout.data,
				container, false);
		int n;
		TextView text = (TextView)rootView.findViewById(R.id.textView1);
		str2 = getActivity().fileList();

		list = (ListView)rootView.findViewById(android.R.id.list);
		list.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_listcard, R.id.txtHeader, str2));
		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

        Fragment fm = new records();
        FragmentManager manger = getFragmentManager();

		Bundle b = new Bundle();
		String passdata = str2[position];
		b.putString("Data", passdata);
		fm.setArguments(b);
        manger.beginTransaction().replace(R.id.container,fm).addToBackStack(null).commit();

		
	}



	
	
}

