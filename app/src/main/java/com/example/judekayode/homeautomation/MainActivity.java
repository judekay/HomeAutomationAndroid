package com.example.judekayode.homeautomation;

import android.app.Application;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ListActivity {

    List<Devices> listitem;

    ArrayList<HashMap<String, String>> grouplist;

    private static final String TAG_GROUP_NAME = "groupname";
    private static final String TAG_PREV_STATUS = "prev";


    String[] groups = null;
    String[] prevss = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        grouplist = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);


        HashMap<String, String> datas = new HashMap<String, String>();

        groups = new String[]{"Sitting Room", "Dinning  Room", "Kitchen", "Walkway", "BedRoom 1", "BedRoom 2", "BedRoom 3", "Security Lights"};

        prevss = new String[]{"2 ON", "1 ON", "4 ON", "5 ON", "6 ON", "2 ON", "1 ON", "0 ON",};


        for (int i = 0; i < groups.length; i++) {


            datas.put(TAG_GROUP_NAME, groups[i]);
            datas.put(TAG_PREV_STATUS, prevss[i]);

            grouplist.add(datas);


        }
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, grouplist,
                R.layout.listitem_layout, new String[]{TAG_GROUP_NAME, TAG_PREV_STATUS,
        }, new int[]{
                R.id.roomnametextView, R.id.prevtextView});

        setListAdapter(adapter);


    }
}
