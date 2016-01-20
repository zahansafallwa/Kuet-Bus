package com.example.zahan.kuetbus;

import android.app.Activity;

import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmDetailsActivity extends Fragment {

    private AlarmDBHelper dbHelper;
    private AlarmModel alarmDetails;
    private TimePicker timePicker;
    private EditText edtName;
    private CustomSwitch chkWeekly;
    private CustomSwitch chkSunday;
    private CustomSwitch chkMonday;
    private CustomSwitch chkTuesday;
    private CustomSwitch chkWednesday;
    private CustomSwitch chkThursday;
    private CustomSwitch chkFriday;
    private CustomSwitch chkSaturday;
    private TextView txtToneSelection;

    public AlarmDetailsActivity() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container,
                false);
        dbHelper = new AlarmDBHelper(getActivity());

        timePicker = (TimePicker) view.findViewById(R.id.alarm_details_time_picker);
        edtName = (EditText) view.findViewById(R.id.alarm_details_name);
        chkWeekly = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_weekly);
        chkSunday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_sunday);
        chkMonday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_monday);
        chkTuesday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_tuesday);
        chkWednesday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_wednesday);
        chkThursday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_thursday);
        chkFriday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_friday);
        chkSaturday = (CustomSwitch) view.findViewById(R.id.alarm_details_repeat_saturday);
        txtToneSelection = (TextView) view.findViewById(R.id.alarm_label_tone_selection);
        long id = getArguments().getLong("id");

        if (id == -1) {
            alarmDetails = new AlarmModel();
        } else if (id == -3) {
            alarmDetails = new AlarmModel();
            int hour = getArguments().getInt("hour");
            int minute = getArguments().getInt("minute");
            timePicker.setCurrentMinute(minute);
            timePicker.setCurrentHour(hour);
            chkSaturday.setChecked(true);

        } else if (id == -2) {
            int hour = getArguments().getInt("hour");
            int minute = getArguments().getInt("minute");
            timePicker.setCurrentMinute(minute);
            timePicker.setCurrentHour(hour);
            Calendar onlycalendar = Calendar.getInstance();
            int day = onlycalendar.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case Calendar.SUNDAY:
                    chkSunday.setChecked(true);
                    break;
                case Calendar.MONDAY:
                    chkMonday.setChecked(true);
                    break;
                case Calendar.TUESDAY:
                    chkTuesday.setChecked(true);
                    break;
                case Calendar.WEDNESDAY:
                    chkWednesday.setChecked(true);
                    break;
                case Calendar.THURSDAY:
                    chkThursday.setChecked(true);
                    break;
                case Calendar.FRIDAY:
                    chkFriday.setChecked(true);
                    edtName.setHint("Remember bus is off on Friday");
                    break;
                case Calendar.SATURDAY:
                    chkSaturday.setChecked(true);
                    edtName.setText("Remember bus schedule is different on Saturday");
                    break;
            }

            alarmDetails = new AlarmModel();
        } else {
            alarmDetails = dbHelper.getAlarm(id);

            timePicker.setCurrentMinute(alarmDetails.timeMinute);
            timePicker.setCurrentHour(alarmDetails.timeHour);
            edtName.setText(alarmDetails.name);
            chkWeekly.setChecked(alarmDetails.repeatWeekly);
            chkSunday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SUNDAY));
            chkMonday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.MONDAY));
            chkTuesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.TUESDAY));
            chkWednesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.WEDNESDAY));
            chkThursday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.THURSDAY));
            chkFriday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.FRDIAY));
            chkSaturday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SATURDAY));
            txtToneSelection.setText(RingtoneManager.getRingtone(getActivity(), alarmDetails.alarmTone).getTitle(getActivity()));
        }

        final LinearLayout ringToneContainer = (LinearLayout) view.findViewById(R.id.alarm_ringtone_container);
        ringToneContainer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, 1);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                updateModelFromLayout();

                AlarmManagerHelper.cancelAlarms(getActivity());

                if (alarmDetails.id < 0) {
                    dbHelper.createAlarm(alarmDetails);
                    Toast.makeText(getActivity(), "Alarm is set successfully", Toast.LENGTH_LONG).show();
                } else {
                    dbHelper.updateAlarm(alarmDetails);
                    Toast.makeText(getActivity(), "Alarm is updated successfully", Toast.LENGTH_LONG).show();
                }

                AlarmManagerHelper.setAlarms(getActivity());

                AlarmListActivity ldf = new AlarmListActivity();
                getFragmentManager().beginTransaction().replace(R.id.content_frame, ldf).commit();


            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Set Alarm");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    txtToneSelection.setText(RingtoneManager.getRingtone(getActivity(), alarmDetails.alarmTone).getTitle(getActivity()));
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }


    private void updateModelFromLayout() {
        alarmDetails.timeMinute = timePicker.getCurrentMinute().intValue();
        alarmDetails.timeHour = timePicker.getCurrentHour().intValue();
        alarmDetails.name = edtName.getText().toString();
        alarmDetails.repeatWeekly = chkWeekly.isChecked();
        alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, chkSunday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.MONDAY, chkMonday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, chkTuesday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, chkWednesday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, chkThursday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, chkFriday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, chkSaturday.isChecked());
        alarmDetails.isEnabled = true;
    }

}
