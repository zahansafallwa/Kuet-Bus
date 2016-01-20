package com.example.zahan.kuetbus;

/**
 * Created by Zahan on 1/7/2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class Saturday extends Fragment {

    int hour, minute;
    ListView list;
    SharedPreferences mypreference;
    String[] time = {
            "09:30 AM",
            "09:30 AM",
            "09:30 AM",
            "12:30 PM",
            "12:30 PM",
            "12:30 PM"

    };
    String[] place = {
            "From: Campus  || To: Dakbangla",
            "From: Campus  || To: Dakbangla",
            "From: Campus  || To: Dakbangla",
            "From: Dakbangla  || To: Campus",
            "From: Dakbangla  || To: Campus",
            "From: Dakbangla  || To: Campus"

    };
    String[] remark = {
            "Goes to Dakbangla via Sonadanga",
            "Goes to Dakbangla directly",
            "Rounds thorugh residential area",
            "Back to campus via Sonadangha",
            "Directly to campus",
            "Back to campus after round through residential area"


    };

    public Saturday() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saturday, container,
                false);


        CustomList adapter = new
                CustomList(getActivity(), time, place, remark);
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mypreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                final int alarmbefore = mypreference.getInt("CheckBox_Value", 5);

                switch (position) {
                    case 0:
                        hour = 9;
                        minute = 30;

                        break;
                    case 1:
                        hour = 9;
                        minute = 30;
                        break;
                    case 2:
                        hour = 9;
                        minute = 30;
                        break;
                    case 3:
                        hour = 12;
                        minute = 30;

                        break;
                    case 4:
                        hour = 12;
                        minute = 30;


                        break;
                    case 5:
                        hour = 12;
                        minute = 30;
                        break;

                }
                if (alarmbefore > minute) {
                    hour = hour - 1;
                    minute = 60 + minute - alarmbefore;
                } else {
                    minute = minute - alarmbefore;
                }

                startAlarmDetailsActivity(-3);
                return false;
            }
        });


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Saturday");
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
