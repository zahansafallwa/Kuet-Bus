package com.example.zahan.kuetbus;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class FromCampus extends Fragment {

    int hour, minute;
    boolean pm;
    ListView list;
    CustomList adapter;
    int myflag = 0;
    SharedPreferences mypreference;
    // taking all info in array's
    String[] time = {
            "06:30 AM",
            "06:45 AM",
            "07:00 AM",
            "07:15 AM",
            "07:15 AM" + "\n(2)",
            "07:30 AM",

            "01:15 PM",
            "01:15 PM\n" +
                    "(2)",
            "01:15 PM\n" +
                    "(3)",
            "01:15 PM\n" +
                    "(4)",

            "04:00 PM",
            "04:00 PM\n" +
                    "(2)",
            "05:05 PM",
            "05:05 PM\n" +
                    "(2)",
            "05:05 PM\n" +
                    "(3)",
            "05:15 PM",


            "07:00 PM",
            "07:00 PM\n" +
                    "(2)",
            "08:10 PM"


    };


    String[] time1 = {
            "06:30 AM",
            "07:00 AM",
            "07:15 AM",
            "01:15 PM",
            "01:15 PM",


            "06:45 AM",
            "07:30 AM",

            "07:15 AM",
            "01:15 PM",
            "05:05 PM",
            "07:00 PM",


            "04:00 PM",
            "07:00 PM",
            "08:10 AM",


            "01:15 PM",
            "04:00 PM",
            "04:00 PM",
            "05:05 PM",
            "05:05 PM",
            "05:05 PM",
            "05:15 PM",
            "07:00 PM",
            "07:00 PM",
            "08:10 PM"


    };


    String[] place1 = {

            "To: Rupsha",
            "To: Rupsha",
            "To: Rupsha",
            "To: Rupsha",
            "To: Rupsha",

            "To: Moylapota",
            "To: Moylapota",

            "To: Sonadanga",
            "To: Sonadanga",
            "To: Sonadanga",
            "To: Sonadanga",

            "To: Residential Area",
            "To: Residential Area",
            "To: Residential Area",

            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Sonadanga",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla"


    };

    String[] place = {
            "To: Rupsha",
            "To: Moylapota",
            "To: Rupsha",
            "To: Shivbari",
            "To: Rupsha",
            "To: Moylapota",


            "To: Dakbangla",
            "To: Sonadanga",
            "To: Rupsha",
            "To: Rupsha",

            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla",
            "To: Sonadanga",
            "To: Dakbangla",

            "To: Dakbangla",
            "To: Dakbangla",
            "To: Dakbangla"


    };
    String[] remark1 = {

            "Takes regular route to Rupsha",
            "Takes regular route to Rupsha",
            "Special Bus.Takes regular route to Rupsha",
            "Takes regular route to Rupsha",
            "Special bus for round through residential area",


            "Direct Bus. Regular route",
            "Direct Bus. Regular route",


            "To Sonadanga via Shivbari",
            "Takes regular route to Sonadanga",
            "Takes regular route to Sonadanga",
            "Round through Sonadanga to Dakbangla",


            "Round through residential area",
            "Round through residential area",
            "Round through residential area",


            "Direct Bus. Regular route",
            "Direct Bus. Regular route",
            "Round through residential area",
            "Direct Bus. Regular route",
            "Direct Bus. Regular route",
            "Special Bus. Regular route",
            "Direct Bus. Regular route",
            "Round through Sonadanga to Dakbangla",
            "Round through residential area",
            "Round through residential area"


    };


    String[] remark = {

            "Takes regular route to Rupsha",
            "Takes regular route to Moylapota",
            "Takes regular route to Rupsha",
            "Goes to Shivbari at 8:10 and back via Sonadanga",
            "Special bus for round through residential area",
            "Takes regular route to Moylapota",


            "Direct Bus. Regular route ",
            "Direct Bus. Regular route",
            "Direct Bus. Regular route ",
            "Special bus for round through residential area",

            "Direct Dakbangla",
            "Direct Dakbangla",
            "Direct Dakbangla",
            "Direct Dakbangla",
            "To Sonadanga",
            "Special bus for round through residential area",

            "Round through Sonadanga to Dakbangla",
            "Round through residential area",
            "Round through residential area"


    };

    public FromCampus() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_from_campus, container, false);

        adapter = new CustomList(getActivity(), time, place, remark);
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (myflag == 0) {

                    mypreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    final int alarmbefore = mypreference.getInt("CheckBox_Value", 5);
                    switch (position) {
                        case 0:
                            hour = 6;
                            minute = 30;
                            break;
                        case 1:
                            hour = 6;
                            minute = 45;
                            break;
                        case 2:
                            hour = 7;
                            minute = 00;
                            break;
                        case 3:
                            hour = 7;
                            minute = 15;
                            break;
                        case 4:
                            hour = 7;
                            minute = 15;
                            break;
                        case 5:
                            hour = 7;
                            minute = 30;
                            break;
                        case 6:
                            hour = 13;
                            minute = 15;
                            break;
                        case 7:
                            hour = 13;
                            minute = 15;
                            break;
                        case 8:
                            hour = 13;
                            minute = 15;
                            break;
                        case 9:
                            hour = 13;
                            minute = 15;
                            break;
                        case 10:
                            hour = 16;
                            minute = 00;
                            break;
                        case 11:
                            hour = 16;
                            minute = 00;
                            break;
                        case 12:
                            hour = 17;
                            minute = 5;
                            break;
                        case 13:
                            hour = 17;
                            minute = 5;
                            break;
                        case 14:
                            hour = 17;
                            minute = 5;
                            break;
                        case 15:
                            hour = 17;
                            minute = 15;
                            break;
                        case 16:
                            hour = 7;
                            minute = 00;
                            break;
                        case 17:
                            hour = 19;
                            minute = 00;
                            break;
                        case 18:
                            hour = 20;
                            minute = 10;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("From Campus");
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

