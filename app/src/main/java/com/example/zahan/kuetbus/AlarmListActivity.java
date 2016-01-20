package com.example.zahan.kuetbus;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


public class AlarmListActivity extends Fragment {

    private AlarmListAdapter mAdapter;
    private AlarmDBHelper dbHelper;
    private Context mContext;
    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getContext();
        View view = inflater.inflate(R.layout.activity_alarm_list, container,
                false);
        list = (ListView) view.findViewById(R.id.listview);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAlarmDetailsActivity(-1);

            }
        });
        dbHelper = new AlarmDBHelper(getActivity());
        mAdapter = new AlarmListAdapter(getActivity(), dbHelper.getAlarms(), AlarmListActivity.this);
        list.setAdapter(mAdapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Alarm List");

        return view;
    }


    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmManagerHelper.cancelAlarms(getActivity());

        AlarmModel model = dbHelper.getAlarm(id);
        model.isEnabled = isEnabled;
        dbHelper.updateAlarm(model);

        AlarmManagerHelper.setAlarms(getActivity());
    }

    public void startAlarmDetailsActivity(long id) {

        AlarmDetailsActivity ldf = new AlarmDetailsActivity();
        Bundle args = new Bundle();
        args.putLong("id", id);
        ldf.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, ldf).commit();

    }

    public void deleteAlarm(long id) {
        final long alarmId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete this alarm?")
                .setTitle("Please Confirm")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlarmManagerHelper.cancelAlarms(mContext);
                        dbHelper.deleteAlarm(alarmId);
                        mAdapter.setAlarms(dbHelper.getAlarms());
                        mAdapter.notifyDataSetChanged();
                        if (dbHelper.getAlarms() != null) {
                            AlarmManagerHelper.setAlarms(mContext);
                        }
                    }
                }).show();
    }


}
