package com.example.zahan.kuetbus;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ToCampus extends Fragment {


    CustomList adapter;
    int myflag = 0;
    int hour, minute;
    ListView list;
    SharedPreferences mypreference;
    String[] time = {
            "07:20 AM",
            "07:25 AM",
            "08:05 AM",
            "08:10 AM",
            "08:10 AM\n" +
                    "(2)",
            "08:10 AM\n" +
                    "(3)",

            "02:00 PM",
            "02:00 PM\n" +
                    "(2)",
            "02:00 PM\n" +
                    "(3)",
            "02:05 PM",

            "04:40 PM",
            "06:30 PM",
            "06:30 PM\n" +
                    "(2)",
            "07:00 PM",
            "07:30 PM",
            "07:30 PM\n" +
                    "(2)",


            "07:40 PM",
            "08:45 PM",
            "09:15 PM"


    };


    String[] place = {
            "From: Rupsha",
            "From: Moylapota",
            "From: Rupsha",
            "From: Shivbari",
            "From: Moylapota",
            "From: Rupsha",

            "From: Dakbangla",
            "From: Rupsha",
            "From: Rupsha",
            "From: Sonadanga",

            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",

            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla"


    };

    String[] time1 = {
            "07:20 AM",
            "08:05 AM",
            "08:10 AM",
            "02:00 PM",
            "02:20 PM",


            "07:25 AM",
            "08:10 AM",


            "08:10 AM",
            "02:05 PM",


            "02:00 PM",
            "04:40 PM",
            "06:30 PM",
            "06:30 PM",
            "07:00 PM",
            "07:30 PM",
            "07:30 PM",
            "07:40 PM",
            "08:45 PM",
            "09:15 PM"


    };
    String[] place1 = {
            "From: Rupsha",
            "From: Rupsha",
            "From: Rupsha",
            "From: Rupsha",
            "From: Rupsha",


            "From: Moylapota",
            "From: Moylapota",

            "From: Sonadanga",
            "From: Sonadanga",


            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla",
            "From: Dakbangla"


    };


    String[] remark1 = {

            "Will be back via Royalmore & Ferighat",
            "Will back via Royalmore, Moylapota, Shivbari and round residential area",
            "Special Bus",
            "Will come back to campus directly",
            "MinaBazar 2:30/ Will round residential area",
            "Special direct bus",


            "Will back via Sonadangha",
            "Will round residential area",


            "Starts from Shivbari at 8:10AM, goes through Sonadnga",
            "Back to campus directly",

            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly"


    };

    String[] remark = {

            "Will be back via Royalmore & Ferighat",
            "Will be back via Sonadangha",
            "Will be back via Royalmore, Moylapota, Shivbari and round residential area",
            "Goes to Shivbari at 8:10 and comes back via Sonadanga",
            "Will round residential area",
            "Special direct bus",


            "Back to campus directly",
            "Back to campus directly",
            "MinaBazar 2:30/ Will round residential area",
            "Back to campus directly",

            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",
            "Back to campus directly",


            "Special bus.",
            "Back to campus directly",
            "Special bus for round through residential area"


    };

    public ToCampus() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_from_campus, container,
                false);

        list = (ListView) view.findViewById(R.id.list);
        adapter = new CustomList(getActivity(), time, place, remark);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                if (myflag == 0) {
                    mypreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    final int alarmbefore = mypreference.getInt("CheckBox_Value", 5);

                    switch (position) {
                        case 0:
                            hour = 7;
                            minute = 20;

                            break;
                        case 1:
                            hour = 7;
                            minute = 25;


                            break;
                        case 2:
                            hour = 8;
                            minute = 05;

                            break;
                        case 3:
                            hour = 8;
                            minute = 10;


                            break;
                        case 4:
                            hour = 8;
                            minute = 10;


                            break;
                        case 5:
                            hour = 8;
                            minute = 10;


                            break;
                        case 6:
                            hour = 14;
                            minute = 00;


                            break;
                        case 7:
                            hour = 14;
                            minute = 00;


                            break;
                        case 8:
                            hour = 14;
                            minute = 00;


                            break;
                        case 9:
                            hour = 14;
                            minute = 05;


                            break;
                        case 10:
                            hour = 16;
                            minute = 40;


                            break;
                        case 11:
                            hour = 18;
                            minute = 30;


                            break;
                        case 12:
                            hour = 18;
                            minute = 30;


                            break;
                        case 13:
                            hour = 19;
                            minute = 00;


                            break;
                        case 14:
                            hour = 19;
                            minute = 30;


                            break;
                        case 15:
                            hour = 19;
                            minute = 30;


                            break;
                        case 16:
                            hour = 19;
                            minute = 40;


                            break;
                        case 17:
                            hour = 20;
                            minute = 45;


                            break;
                        case 18:
                            hour = 21;
                            minute = 15;

                            break;
                    }
                    if (alarmbefore > minute) {
                        hour = hour - 1;
                        minute = 60 + minute - alarmbefore;
                    } else {
                        minute = minute - alarmbefore;
                    }

                    startAlarmDetailsActivity(-2);


                } else {
                    Toast.makeText(getActivity(), "Please click floating button first to set alarm", Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myflag == 0) {
                    adapter = new
                            CustomList(getActivity(), time1, place1, remark1);

                    list.setAdapter(adapter);
                    myflag = 1;
                } else {
                    adapter = new
                            CustomList(getActivity(), time, place, remark);

                    list.setAdapter(adapter);
                    myflag = 0;

                }
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("To Campus");


        return view;
    }

    public void startAlarmDetailsActivity(long id) {

        AlarmDetailsActivity ldf = new AlarmDetailsActivity();
        Bundle args = new Bundle();
        args.putLong("id", id);
        args.putInt("hour", hour);
        args.putInt("minute", minute);
        ldf.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, ldf).commit();

    }


}