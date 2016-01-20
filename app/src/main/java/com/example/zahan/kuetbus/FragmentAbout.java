package com.example.zahan.kuetbus;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentAbout extends Fragment {


    public FragmentAbout() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_about, container,
                false);


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("About");
        return view;
    }


}