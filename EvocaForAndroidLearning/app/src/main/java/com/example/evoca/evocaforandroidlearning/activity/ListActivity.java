package com.example.evoca.evocaforandroidlearning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.evoca.evocaforandroidlearning.adapter.CustomAdapter;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.fragments.DetailFragment;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv_list);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> children1 = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
        ArrayList<String> children3 = new ArrayList<String>();
        ArrayList<String> children4 = new ArrayList<String>();
        ArrayList<String> children5 = new ArrayList<String>();
        children1.add("Child_1");
        children1.add("Child_2");




        groups.add(children1);
        children2.add("Child_1");
        children2.add("Child_2");
        children2.add("Child_3");
        groups.add(children2);
        children3.add("Child_1");
        children3.add("Child_2");
        children3.add("Child_3");
        children3.add("Child___4");
        groups.add(children3);
        //Создаем адаптер и передаем context и список с данными
//
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(groupPosition == 0&&childPosition==0  )
                {
                transaction.replace(R.id.container,DetailFragment.newInstance());
                }
                transaction.commit();
                return false;
            }
        });


        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);
    }
}
