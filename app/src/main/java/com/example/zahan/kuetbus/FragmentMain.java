package com.example.zahan.kuetbus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class FragmentMain extends Fragment {


    ListView list;
    CustomList adapter;
    int myflag = 0;

    String[] time = {
            "06:30 AM",
            "06:45 AM",
            "07:00 AM",
            "07:15 AM",
            "07:15 AM",
            "07:30 AM",

            "01:15 PM",
            "01:15 PM",
            "01:15 PM",
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


    String[] place = {
            "Rupsha | 7:20AM",
            "Moylapota | 7:25AM",
            "Rupsha | 8:05AM",
            "Shivbari | 8:10AM",
            "Rupsha | 8:10AM",
            "Moylapota | 8:10AM",


            "Dakbangla | 2:00PM",
            "Sonadangha | 2:05AM",
            "Rupsha | 2:00AM",
            "Rupsha | 2:20AM",

            "Dakbangla | 4:40 PM",
            "Dakbangla | 6:30 PM",
            "Dakbangla | 7:00 PM",
            "Dakbangla | 6:30 PM",
            "Dakbangla | 7:40 PM",
            "Dakbangla | 7:30 PM",

            "Dakbangla | 7:30 PM",
            "Dakbangla | 8:45 PM",
            "Dakbangla | 9:15 PM"


    };


    String[] remark = {

            "Will be back via Royalmore & Ferighat",
            "Will be back via Sonadangha",
            "Will be back via Royalmore, Moylapota, Shivbari and round residential area",
            "Will be back via Sonadangha",
            "Special bus",
            "Will round through residential area",


            "Will be back to campus directly",
            "",
            "Will be back to campus directly",
            "MinaBazar 2:30/ Will round residential area",

            "",
            "Round through residential area",
            "Direct Dakbangla",
            "Goes to Sonadanga",
            "Special Bus",
            "Direct Dakbangla",

            "Round through Sonadanga to Dakbangla",
            "Round through residential area",
            "Round through residential area"


    };

    public FragmentMain() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container,
                false);

        adapter = new CustomList(getActivity(), time, place, remark);
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "You cannot set alarm here. Go to from campus or to campus view", Toast.LENGTH_LONG).show();


                return false;
            }
        });


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Kuet Bus");
        return view;
    }


}