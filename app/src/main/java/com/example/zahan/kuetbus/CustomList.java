package com.example.zahan.kuetbus;

/**
 * Created by Zahan on 1/7/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] mytime;
    private final String[] myplace;
    private final String[] myremark;

    public CustomList(Activity context,
                      String[] mytime, String[] myplace, String[] myremark) {
        super(context, R.layout.single_list, mytime);
        this.context = context;
        this.mytime = mytime;
        this.myplace = myplace;
        this.myremark = myremark;


    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_list, null, true);
        TextView time = (TextView) rowView.findViewById(R.id.textView);
        TextView place = (TextView) rowView.findViewById(R.id.textView2);
        TextView remark = (TextView) rowView.findViewById(R.id.textView3);
        time.setText(mytime[position]);
        place.setText(myplace[position]);
        remark.setText(myremark[position]);

        return rowView;
    }
}
