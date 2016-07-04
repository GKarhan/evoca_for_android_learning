package com.example.evoca.evocaforandroidlearning.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.ServerResponse;
import com.example.evoca.evocaforandroidlearning.adapter.CustomAdapter;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.fragments.LessonFragment;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity  {

    private FragmentTransaction transaction;
    List<ServerResponse> serverResponses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv_list);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> children1 = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
//        ArrayList<String> children3 = new ArrayList<String>();
//        ArrayList<String> children4 = new ArrayList<String>();
//        ArrayList<String> children5 = new ArrayList<String>();




        children1.add("Child_1");
        children1.add("Child_2");

        groups.add(children1);
        children2.add("Child_1");
        children2.add("Child_2");
        children2.add("Child_3");
        groups.add(children2);
//        children3.add("Child_1");
//        children3.add("Child_2");
//        children3.add("Child_3");
//        children3.add("Child___4");
//        groups.add(children3);
        //Создаем адаптер и передаем context и список с данными
//

       if (listView != null) {
           listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
               @Override
               public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                   transaction = getSupportFragmentManager().beginTransaction();
                   if (groupPosition == 0 && childPosition == 0) {
                       listView.removeAllViewsInLayout();
                       transaction.replace(R.id.listframe, LessonFragment.newInstance());
                       transaction.commit();
                       Toast.makeText(ListActivity.this, "position_1", Toast.LENGTH_SHORT).show();
                   }
                   if (groupPosition == 0 && childPosition == 1) {
                       //transaction.replace(R.id.container, LessonFragment.newInstance()).commit();
                       Toast.makeText(ListActivity.this, "position2_2", Toast.LENGTH_SHORT).show();
                   }
                   return false;
               }
           });
       }
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);
    }
}
