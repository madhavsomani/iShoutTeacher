package com.madhav.ishoutteacher;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class records extends Fragment{
	String str;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{

		final View rootView = inflater.inflate(R.layout.records,
				container, false);

		Button delet = (Button)rootView.findViewById(R.id.deletbutn);
		Button upload = (Button)rootView.findViewById(R.id.uploadbutn);
		final TextView text = (TextView)rootView.findViewById(R.id.textView1);
		TextView text2 = (TextView)rootView.findViewById(R.id.textView2);
		int n;

		Bundle b = this.getArguments();
		 str = b.getString("Data");


		
		text2.setText(str);

		FileInputStream fis;
		final StringBuffer fileContent = new StringBuffer("");
		try {
			fis = getActivity().openFileInput(str);
			byte[] buffer = new byte[1024];
			while ((n = fis.read(buffer)) != -1) 
			{ 
			  fileContent.append(new String(buffer, 0, n)); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		text.setText(fileContent);


		upload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

				CharSequence option[] = new CharSequence[] {"Server", "Email"};

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Pick a option");
				builder.setItems(option, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// the user clicked on colors[which]
						if(which == 0 )
						{

                            Fragment fm = new upload();
                            FragmentManager manger = getFragmentManager();
                            Bundle b = new Bundle();
                            b.putString("attendance", fileContent.toString());
                            fm.setArguments(b);
                            manger.beginTransaction().replace(R.id.container, fm).addToBackStack(null).commit();

                        }
						if(which == 1)
						{

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/html");

                            intent.putExtra(Intent.EXTRA_SUBJECT, "iShout Attendance"+str);
                            intent.putExtra(Intent.EXTRA_TEXT, ""+ fileContent.toString());

                            startActivity(Intent.createChooser(intent, "Send Email"));
						}
					}

				});
				builder.show();




            }
        });
		
		delet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Data Deleted"+str, Toast.LENGTH_SHORT).show();
					getActivity().deleteFile(str);


					Fragment fm = new displaydata();
				FragmentManager manger = getFragmentManager();
				manger.beginTransaction().replace(R.id.container, fm).commit();

			}
		});




		return rootView;
	}


}
	
	