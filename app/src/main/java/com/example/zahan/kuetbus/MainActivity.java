package com.example.zahan.kuetbus;


import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {


    String TITLES[] = {"From Campus", "To Campus", "Saturday", "Temporary", "Alarm List", "Notice", "Downloads", "About"};
    int ICONS[] = {R.drawable.ic_action_full_screen, R.drawable.ic_action_return_from_full_screen, R.drawable.ic_action_important, R.drawable.ic_action_import_export, R.drawable.ic_action_alarms, R.drawable.ic_action_email, R.drawable.ic_action_cloud, R.drawable.ic_action_about};


    private Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    Fragment fragment;
    FragmentManager frgManager;
    ActionBarDrawerToggle mDrawerToggle;
    SharedPreferences mypreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(TITLES, ICONS, this);


        mRecyclerView.setAdapter(mAdapter);
        frgManager = getSupportFragmentManager();


        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Drawer.closeDrawers();

                    int position = recyclerView.getChildPosition(child);
                    switch (position) {
                        case 0:
                            fragment = new FragmentMain();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();

                            break;
                        case 1:
                            fragment = new FromCampus();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 2:
                            fragment = new ToCampus();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 3:
                            fragment = new Saturday();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 4:
                            fragment = new Fragment_temp();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 5:
                            fragment = new AlarmListActivity();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 6:
                            fragment = new FragmentNotice();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 7:
                            fragment = new Fragment_down();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;
                        case 8:
                            fragment = new FragmentAbout();
                            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                                    .commit();
                            break;

                    }

                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }


        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        fragment = new FragmentMain();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .addToBackStack(null).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_help) {

            Dialog dialog = new Dialog(this);
            dialog.setTitle("Help");
            dialog.setContentView(R.layout.dialog_frame);
            dialog.show();
            return true;
        }
        if (id == R.id.action_setting) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Settings");
            dialog.setContentView(R.layout.settings_frame);
            final NumberPicker alarmtext = (NumberPicker) dialog.findViewById(R.id.number);
            mypreference = PreferenceManager.getDefaultSharedPreferences(this);
            int alarmbefore = mypreference.getInt("CheckBox_Value", 5);
            alarmtext.setMaxValue(30);
            alarmtext.setMinValue(0);
            alarmtext.setValue(alarmbefore);
            Button b = (Button) dialog.findViewById(R.id.save);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int myvalue = alarmtext.getValue();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    Editor editor = sharedPreferences.edit();
                    editor.putInt("CheckBox_Value", myvalue);
                    editor.commit();
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }
}

