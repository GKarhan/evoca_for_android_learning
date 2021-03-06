package com.example.evoca.evocaforandroidlearning.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.adapter.ExpandableListAdapter;
import com.example.evoca.evocaforandroidlearning.Model.Group;
import com.example.evoca.evocaforandroidlearning.api.ApiManager;
import com.example.evoca.evocaforandroidlearning.fragments.LessonFragment;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
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
    private MenuItem buttonLogOut;
    private Toolbar toolbar;
    private Exercise child ;
    //ArrayList<Child> childrens=new ArrayList<Child>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
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
      //  buttonBack.setVisible(true);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), ListActivity.class);
                startActivity(in);
                finish();
            }
        });

        expandableListView.removeAllViewsInLayout();
        Child childText =  expandableListAdapter.getChild(groupPosition, childPosition);


//        Toast.makeText(ListActivity.this, "" + childText.getHas_exam(), Toast.LENGTH_SHORT).show();
        LessonFragment lessonFragment = new LessonFragment();
            //Get Fragment Instance
            Bundle data = new Bundle();//Use bundle to pass data
            data.putString("data", childText.getText());//put string, int, etc in bundle with a key value
            data.putString("title", childText.getTitle());
            data.putInt("exam", childText.getHas_exam());
            if (childText.getHas_exam() == 1) {
                child = expandableListAdapter.getChild(groupPosition, childPosition).getExam_questions().get(childPosition);
//                for(Exercise examquestion : childText.getExam_questions()) {
//                    System.out.println("-----" + examquestion.getType());
//                    System.out.println("-----"+childText.getText());
//                }
//                System.out.println("++++" + childText.getExam_questions().size());
                data.putString("question", child.getQuestion());
                data.putString("answer1",child.getAns1());
                data.putString("answer2",child.getAns2());
                data.putString("answer3",child.getAns3());
                data.putString("answer4",child.getAns4());
                data.putString("tar1",child.getTa1());
                data.putString("tar2",child.getTa2());
                data.putString("type", child.getType());
            } else {

            }
            lessonFragment.setArguments(data);
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.list_frame, lessonFragment).commit();

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        buttonLogOut = menu.findItem(R.id.log_out_item);
        buttonLogOut.setVisible(false);
        if(PrefUtil.isAuthanticated){
            buttonLogOut.setVisible(true);
        }else {
            buttonLogOut.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_item: {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle(getResources().getString(R.string.exit_dialog_title))
                        .setMessage(getResources().getString(R.string.exit_dialog_message))
                        .setIcon(R.drawable.exit)
                        .setCancelable(false)
                        .setNegativeButton(getResources().getString(R.string.exit_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(AccessToken.getCurrentAccessToken() != null){
                                    LoginManager.getInstance().logOut();
                                }
                                PrefUtil.removeToPrefs(ListActivity.this, PrefUtil.PREFS_LOGIN_USERNAME_KEY, PrefUtil.PREFS_LOGIN_PASSWORD_KEY);
                                PrefUtil.isAuthanticated = false;
                                Intent intent = new Intent(ListActivity.this, ChooseActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setPositiveButton(getResources().getString(R.string.exit_cancel), null);
                AlertDialog alert = builder.create();
                alert.show();

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
