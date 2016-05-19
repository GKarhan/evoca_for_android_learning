package com.example.evoca.evocaforandroidlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        children3.add("Child_4");
        groups.add(children3);
        //Создаем адаптер и передаем context и список с данными
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);
    }
}
