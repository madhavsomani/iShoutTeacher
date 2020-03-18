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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by madhav on 7/31/2016.
 */
public class uploadtry extends Fragment {

    String attendance,lecturetype="TH",batchno="1",departmentstring="CE",semstring="3",subidstring="ICN(T)";
    TextView output,subjectidtextview;
    String url;
    String serverurl;
    Spinner sem,department,subid;
    EditText btno,date;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6;

    ArrayList<String> lectureslot = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.uploadtry, container , false);

        Bundle b = this.getArguments();
        attendance = b.getString("attendance").replace("]","").replace("[","");


        Button submit = (Button) rootview.findViewById(R.id.submiturlbutton);
        final WebView webview = (WebView)rootview.findViewById(R.id.webView);
       //  output = (TextView) rootview.findViewById(R.id.output);
        sem = (Spinner)rootview.findViewById(R.id.sem);
        department = (Spinner)rootview.findViewById(R.id.department);
        subid = (Spinner)rootview.findViewById(R.id.subidspinner);
        btno = (EditText)rootview.findViewById(R.id.btno);
        date = (EditText)rootview.findViewById(R.id.date);
        subjectidtextview = (TextView) rootview.findViewById(R.id.subjectidtextview) ;

         chk1 = (CheckBox) rootview.findViewById(R.id.checkboxfor1);
         chk2 = (CheckBox) rootview.findViewById(R.id.checkboxfor2);
         chk3 = (CheckBox) rootview.findViewById(R.id.checkboxfor3);
         chk4 = (CheckBox) rootview.findViewById(R.id.checkboxfor4);
         chk5 = (CheckBox) rootview.findViewById(R.id.checkboxfor5);
         chk6 = (CheckBox) rootview.findViewById(R.id.checkboxfor6);

        //shared
        SharedPreferences spf2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor edit = spf2.edit();

        //serverurl

        serverurl  = spf2.getString("serverurl", "http://www.svpcet.net/wpage.aspx?para=");


        //preset data
        btno.setText(spf2.getString("btno",""));
        date.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

        //Checkbox


        final Spinner lecturelist = (Spinner) rootview.findViewById(R.id.lecturespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.lecture_type_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecturelist.setAdapter(adapter);

        lecturelist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lecturetype = "" + lecturelist.getItemAtPosition(position).toString();
               /* if(lecturelist.getItemAtPosition(position).equals("TH")|| lecturelist.getItemAtPosition(position).equals("PR"))
                {
                    subid.setVisibility(View.VISIBLE);
                    subjectidtextview.setVisibility(View.VISIBLE);
                }else
                {
                    subid.setVisibility(View.GONE);
                    subjectidtextview.setVisibility(View.GONE);
                } */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner batchnolist = (Spinner) rootview.findViewById(R.id.batchnospiner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.Batch_no_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        batchnolist.setAdapter(adapter2);
        batchnolist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                batchno = "" + batchnolist.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.department_array, R.layout.spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter3);

        final ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(),
                R.array.sem_array, R.layout.spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(adapter4);

        final ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getActivity(),
                R.array.sem2_array, R.layout.spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //subid addapter
        final ArrayAdapter<CharSequence> ceadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cesubarray, R.layout.spinner_item);
        ceadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> etcadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.etcsubarray, R.layout.spinner_item);
        etcadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> firstyearadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.firstyearsubarray, R.layout.spinner_item);
        firstyearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> mechadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.mesubarray, R.layout.spinner_item);
        mechadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> itadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.itsubarray, R.layout.spinner_item);
        itadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> eeadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.eesubarray, R.layout.spinner_item);
        eeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                departmentstring = "" + department.getItemAtPosition(position).toString();

                if(department.getItemAtPosition(position).equals("FIRST YEAR"))
                {

                    sem.setAdapter(adapter5);
                    subid.setAdapter(firstyearadapter);
                }
                else
                {
                    sem.setAdapter(adapter4);
                }

                if(department.getItemAtPosition(position).equals("CE"))
                {
                    subid.setAdapter(ceadapter);
                }
                if(department.getItemAtPosition(position).equals("ME - A") || department.getItemAtPosition(position).equals("ME - B") )
                {
                    subid.setAdapter(mechadapter);
                }
                if(department.getItemAtPosition(position).equals("ETC - A") || department.getItemAtPosition(position).equals("ETC - B") )
                {
                    subid.setAdapter(etcadapter);
                }
                if(department.getItemAtPosition(position).equals("IT"))
                {
                    subid.setAdapter(itadapter);
                }
                if(department.getItemAtPosition(position).equals("EE"))
                {
                    subid.setAdapter(eeadapter);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        subid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subidstring = ""+subid.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                semstring = "" + sem.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /* Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                output.setText(urlfunction().replace("[","").replace("]",""));

                            }
                        });
                    }
                } catch (InterruptedException e) { }
            }
        };

        t.start(); */

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Snackbar snack = Snackbar.make(getActivity().findViewById(R.id.snackbarPosition2), "Attendance Submited", Snackbar.LENGTH_LONG);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.parseColor("#FFFFFF"));

                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                snack.show();

                edit.putString("btno",btno.getText().toString());
                edit.commit();

                webview.loadUrl(urlfunction().replace("[","").replace("]",""));
            }
        });


        return rootview;
    }

    String urlfunction()
    {
        if(chk1.isChecked())
        {
            if(!lectureslot.contains("1"))
            lectureslot.add("1");
        }
        else
        {
            lectureslot.remove("1");
        }

        if(chk2.isChecked())
        {
            if(!lectureslot.contains("2"))
            lectureslot .add("2");
        }
        else
        {
            lectureslot.remove("2");
        }

        if(chk3.isChecked())
        {
            if(!lectureslot.contains("3"))
            lectureslot .add("3");
        }
        else
        {
            lectureslot.remove("3");
        }

        if(chk4.isChecked())
        {
            if(!lectureslot.contains("4"))
            lectureslot .add("4");
        }
        else
        {

            lectureslot.remove("4");
        }

        if(chk5.isChecked())
        {
            if(!lectureslot.contains("5"))
            lectureslot .add("5");
        }
        else
        {
            lectureslot.remove("5");
        }

        if(chk6.isChecked())
        {
            if(!lectureslot.contains("6"))
            lectureslot .add("6");
        }
        else
        {
            lectureslot.remove("6");
        }


        return (""+serverurl
                +departmentstring+":"
                +semstring+":"+lecturetype+":"
                +subidstring +":"
                +batchno+":"
                +btno.getText().toString()+":"
                +date.getText().toString()+ ":"
                +lectureslot+":"
                + attendance);



    }
}
