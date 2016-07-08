package com.example.evoca.evocaforandroidlearning.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.api.Api;
import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.adapter.ExpandableListAdapter;
import com.example.evoca.evocaforandroidlearning.Model.Group;
import com.example.evoca.evocaforandroidlearning.api.ApiManager;
import com.example.evoca.evocaforandroidlearning.fragments.LessonFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListActivity extends AppCompatActivity implements  ExpandableListView.OnChildClickListener {

    private ProgressBar progressBar;
    private TextView textView;


    /*our expandable adapter */
    ExpandableListAdapter expandableListAdapter;

    /*expandable list*/
    ExpandableListView expandableListView;

    /*list items*/
    ArrayList<Group> groupArrayList =new ArrayList<Group>();
    //ArrayList<Child> childrens=new ArrayList<Child>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.aaaaa);


     /*genarate data for list view*/
        genarateData();

     /*instantiate adapter with our item list*/
        expandableListAdapter=new ExpandableListAdapter(this, groupArrayList);

     /*we get list view*/
        expandableListView=(ExpandableListView) findViewById(R.id.expandableListView);

        /*set adapter to list view*/
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //   getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    //Generate dummy data for list view
    public void genarateData() {

        ApiManager.getInstance().getClient().postContentGroup("testContent", new Callback<List<Group>>() {
            @Override
            public void success(List<Group> groups, Response response) {
                progressBar.setVisibility(View.GONE);
                groupArrayList.clear();
                groupArrayList.addAll(groups);
                expandableListAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(ListActivity.this, "sasasas", Toast.LENGTH_SHORT).show();
        expandableListView.removeAllViewsInLayout();
        Child childText =  expandableListAdapter.getChild(groupPosition, childPosition);

        System.out.println("*******" + childText.getText());

        LessonFragment argumentFragment = new LessonFragment();//Get Fragment Instance
        Bundle data = new Bundle();//Use bundle to pass data
        data.putString("data", childText.getText());//put string, int, etc in bundle with a key value
        data.putString("title", childText.getTitle());
        argumentFragment.setArguments(data);

        getSupportFragmentManager().beginTransaction().replace(R.id.list_frame, argumentFragment).commit();
        
        return true;
    }
}
