package com.example.evoca.evocaforandroidlearning.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;


public class LessonFragment extends Fragment {

    private Button exerciseButton;
    TextView textViewText;
    TextView textViewTitle;

    public LessonFragment() {
        // Required empty public constructor
    }

    public static LessonFragment newInstance() {
        LessonFragment fragment = new LessonFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lesson, container, false);

        exerciseButton = (Button) rootView.findViewById(R.id.btn_chack);
        textViewText = (TextView) rootView.findViewById(R.id.aaaaa);
        textViewTitle = (TextView) rootView.findViewById(R.id.tv_lesson);
        String getArgumentTitle = getArguments().getString("title");


        textViewTitle.setTypeface(null, Typeface.BOLD_ITALIC);
        textViewTitle.setText(getArgumentTitle);

        if (getArguments().getInt("exam") !=1){
            exerciseButton.setText("Վարժություն not found");
            exerciseButton.setEnabled(false);
        }

        String getArgument = getArguments().getString("data");
        textViewText.setText(getArgument);

        if(!PrefUtil.isAuthanticated) {
            exerciseButton.setVisibility(View.GONE);
        }
        
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            @Override
            public void onClick(View v) {
                CheckBoxFragment checkBoxFragment = new CheckBoxFragment();
                RadioFragment radioFragment = new RadioFragment();
                EditTextFragment editTextFragment = new EditTextFragment();


                String question = getArguments().getString("question");
                String type = getArguments().getString("type");
                String answer1 = getArguments().getString("answer1");
                String answer2 = getArguments().getString("answer2");
                String answer3 = getArguments().getString("answer3");
                String answer4 = getArguments().getString("answer4");
                String tar1 = getArguments().getString("tar1");
                String tar2 = getArguments().getString("tar2");
                Bundle bundle = new Bundle();
                bundle.putString("question", question);
                bundle.putString("answer1",answer1);
                bundle.putString("answer2",answer2);
                bundle.putString("answer3",answer3);
                bundle.putString("answer4",answer4);
                bundle.putString("tar1",tar1);
                bundle.putString("tar2",tar2);


                System.out.println(type);

                switch (type) {
                    case "radio": {

                        radioFragment.setArguments(bundle);
                        transaction.replace(R.id.list_frame, radioFragment).commit();
                        break;
                    }
                    case "checkbox": {
                        checkBoxFragment.setArguments(bundle);
                        transaction.replace(R.id.list_frame,checkBoxFragment).commit();
                        break;
                    }
                    case "edittext": {
                        editTextFragment.setArguments(bundle);
                        transaction.replace(R.id.list_frame,editTextFragment).commit();
                        break;
                    }
                }
            }
        });


        return rootView;
    }


}
