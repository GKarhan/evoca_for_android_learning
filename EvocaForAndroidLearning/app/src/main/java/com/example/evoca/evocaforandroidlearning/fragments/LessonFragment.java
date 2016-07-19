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

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;
import com.example.evoca.evocaforandroidlearning.util.PrefUtil;


public class LessonFragment extends Fragment {

    private Button exerciseButton;
    private TextView textViewText;
    private TextView textViewTitle;
    private static Child lesson;

    public LessonFragment() {

    }

    public static LessonFragment newInstance(Child lesson) {
        LessonFragment fragment = new LessonFragment();
        ListActivity.lesson = lesson;
        LessonFragment.lesson = lesson;///????????????
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
        String title = lesson.getTitle();

        textViewTitle.setTypeface(null, Typeface.BOLD_ITALIC);
        textViewTitle.setText(title);

        if (lesson.getHas_exam() != 1){
            exerciseButton.setText("Վարժություն not found");
            exerciseButton.setEnabled(false);
        } else if(ListActivity.exerciseIndex >= lesson.getExam_questions().size()){
            exerciseButton.setText("Ստուգումն ավարտված է");
            ListActivity.exerciseIndex = 0;
        }

        String text = lesson.getText();
        textViewText.setText(text);

        if(!PrefUtil.isAuthanticated) {
            exerciseButton.setVisibility(View.GONE);
        }
        
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            @Override
            public void onClick(View v) {

                System.out.println(lesson.getExam_questions().get(ListActivity.exerciseIndex));

                if(lesson.getExam_questions() != null && lesson.getExam_questions().size() > 0) {
                    switch (lesson.getExam_questions().get(ListActivity.exerciseIndex).getType()) {
                        case "radio": {
                            RadioFragment radioFragment = RadioFragment.newInstance();
                            transaction.replace(R.id.list_frame, radioFragment).commit();
                            break;
                        }
                        case "checkbox": {
                            CheckBoxFragment checkBoxFragment = CheckBoxFragment.newInstance();
                            transaction.replace(R.id.list_frame, checkBoxFragment).commit();
                            break;
                        }
                        case "edittext": {
                            EditTextFragment editTextFragment = EditTextFragment.newInstance();
                            transaction.replace(R.id.list_frame,editTextFragment).commit();
                            break;
                        }
                    }
                }
            }
        });

        return rootView;
    }
}
