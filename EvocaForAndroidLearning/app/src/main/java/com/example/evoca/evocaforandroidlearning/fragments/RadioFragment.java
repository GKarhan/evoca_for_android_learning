package com.example.evoca.evocaforandroidlearning.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evoca.evocaforandroidlearning.Model.Child;
import com.example.evoca.evocaforandroidlearning.Model.Exercise;
import com.example.evoca.evocaforandroidlearning.R;
import com.example.evoca.evocaforandroidlearning.activity.ListActivity;
import com.example.evoca.evocaforandroidlearning.dialogs.Custom;
import com.example.evoca.evocaforandroidlearning.dialogs.CustomDialog;


public class RadioFragment extends Fragment implements View.OnClickListener {
    private MenuItem buttonBack;
    private TextView textViewQuestion;
    private RadioButton textViewRadioAnswer1;
    private RadioButton textViewRadioAnswer2;
    private RadioButton textViewRadioAnswer3;
    private RadioButton textViewRadioAnswer4;
    private String tarberak1;
    private ImageButton buttonAnswer;
    private TextView textViewAnswer;
    private boolean correctAnswer = false;
    private Button checkButton;
    private static Exercise exercise;
    private TextView textViewTitle;
    public static MediaPlayer mediaPlayerWrong;
    public static MediaPlayer mediaPlayerRight;



    public RadioFragment() {
        // Required empty public constructor
    }

//
    public static RadioFragment newInstance() {
        RadioFragment fragment = new RadioFragment();
        Bundle args = new Bundle();
        System.out.println(ListActivity.exerciseIndex);
        System.out.println(ListActivity.lesson.getExam_questions());
        exercise = ListActivity.lesson.getExam_questions().get(ListActivity.exerciseIndex);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_radio, container, false);
        textViewQuestion = (TextView) rootView.findViewById(R.id.tv_radio_question);
        textViewRadioAnswer1 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer1);
        textViewRadioAnswer2 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer2);
        textViewRadioAnswer3 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer3);
        textViewRadioAnswer4 = (RadioButton) rootView.findViewById(R.id.tv_radio_answer4);
        textViewTitle = (TextView) rootView.findViewById(R.id.tv_title);
        final Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        buttonAnswer = (ImageButton) rootView.findViewById(R.id.btn_answer);
        textViewAnswer = (TextView) rootView.findViewById(R.id.tv_answer);
        checkButton = (Button) rootView.findViewById(R.id.btn_radio_check);

        mediaPlayerWrong = MediaPlayer.create(getContext(),R.raw.wrong);
        mediaPlayerRight = MediaPlayer.create(getContext(),R.raw.correct);


        textViewTitle.setText(ListActivity.lesson.getTitle());

        textViewQuestion.setText(exercise.getQuestion());
        textViewRadioAnswer1.setText(exercise.getAns1());
        textViewRadioAnswer2.setText(exercise.getAns2());
        textViewRadioAnswer3.setText(exercise.getAns3());
        textViewRadioAnswer4.setText(exercise.getAns4());
        tarberak1 = exercise.getTa1();

        textViewRadioAnswer1.setOnClickListener(this);
        textViewRadioAnswer2.setOnClickListener(this);
        textViewRadioAnswer3.setOnClickListener(this);
        textViewRadioAnswer4.setOnClickListener(this);

    /*    if (textViewRadioAnswer1.isChecked()) {
            textViewRadioAnswer2.setChecked(false);
            textViewRadioAnswer3.setChecked(false);
            textViewRadioAnswer4.setChecked(false);
        }
        if (textViewRadioAnswer2.isChecked()) {
            textViewRadioAnswer1.setChecked(false);
            textViewRadioAnswer3.setChecked(false);
            textViewRadioAnswer4.setChecked(false);
        }
        if (textViewRadioAnswer3.isChecked()) {
            textViewRadioAnswer1.setChecked(false);
            textViewRadioAnswer2.setChecked(false);
            textViewRadioAnswer4.setChecked(false);
        }
        if (textViewRadioAnswer4.isChecked()) {
            textViewRadioAnswer1.setChecked(false);
            textViewRadioAnswer2.setChecked(false);
            textViewRadioAnswer3.setChecked(false);
        }*/

      buttonAnswer.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      FragmentTransaction transaction = getFragmentManager().beginTransaction();
                      if (correctAnswer) {
                          Toast.makeText(getContext(), "Հալալ ա Քեզ", Toast.LENGTH_SHORT).show();
                          getNextExercise();
                      }
                      else {
                          Toast.makeText(getContext(), "Սխալ պատասխան, փորձիր կրկին պատասխանել:", Toast.LENGTH_SHORT).show();
                          LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.lesson);
                          transaction.replace(R.id.list_frame, lessonFragment).commit();

                      }
          }
      });
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctAnswer){
                    mediaPlayerRight.start();
                    textViewAnswer.setText(getResources().getString(R.string.correct_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.correct);
                    buttonAnswer.setVisibility(View.VISIBLE);

                }else {
                    mediaPlayerWrong.start();
                    textViewAnswer.setText(getResources().getString(R.string.incorrect_answer));
                    textViewAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setImageResource(R.drawable.incorrect);
                    buttonAnswer.setVisibility(View.VISIBLE);
                    vibrator.vibrate(1000);
                    textViewRadioAnswer1.setClickable(false);
                    textViewRadioAnswer2.setClickable(false);
                    textViewRadioAnswer3.setClickable(false);
                    textViewRadioAnswer4.setClickable(false);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.tv_radio_answer1:
                textViewRadioAnswer2.setChecked(false);
                textViewRadioAnswer3.setChecked(false);
                textViewRadioAnswer4.setChecked(false);
                //System.out.println(list.size());
                if (checked && textViewRadioAnswer1.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer2:
                textViewRadioAnswer1.setChecked(false);
                textViewRadioAnswer3.setChecked(false);
                textViewRadioAnswer4.setChecked(false);
                if (checked && textViewRadioAnswer2.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer3:
                textViewRadioAnswer2.setChecked(false);
                textViewRadioAnswer1.setChecked(false);
                textViewRadioAnswer4.setChecked(false);
                if (checked && textViewRadioAnswer3.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
            case R.id.tv_radio_answer4:
                textViewRadioAnswer2.setChecked(false);
                textViewRadioAnswer3.setChecked(false);
                textViewRadioAnswer1.setChecked(false);
                if (checked && textViewRadioAnswer4.getText().equals(tarberak1)){
                    correctAnswer = true;
                } else {
                    correctAnswer = false;
                }
                    break;
        }
    }

    private void getNextExercise(){
        ListActivity.exerciseIndex++;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(ListActivity.exerciseIndex < ListActivity.lesson.getExam_questions().size()) {
            Child lesson = ListActivity.lesson;
            switch (lesson.getExam_questions().get(ListActivity.exerciseIndex).getType()) {
                case "radio": {
                    RadioFragment radioFragment = RadioFragment.newInstance();
                    transaction.replace(R.id.list_frame, radioFragment).commit();
                    break;
                }
                case "checkbox": {
                    CheckBoxFragment checkBoxFragment = CheckBoxFragment.newInstance();
                    transaction.replace(R.id.list_frame,checkBoxFragment).commit();
                    break;
                }
                case "edittext": {
                    EditTextFragment editTextFragment = EditTextFragment.newInstance();
                    transaction.replace(R.id.list_frame,editTextFragment).commit();
                    break;
                }
            }
        } else {
            //((ListActivity)getActivity()).onBackPressed();
            ListActivity.exerciseIndex = 0;
           // transaction.replace(R.id.list_frame, LessonFragment.newInstance(ListActivity.lesson)).commit();
//            final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.queen);
//            mp.start();
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("   ").setMessage("Դուք հաջողությամբ ավարտեցիք ․․․․․․․․․․․․․․")
//                    .setIcon(R.drawable.congratulations)
//                    .setCancelable(false)
//                    .setNegativeButton("Շարունակել", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            Intent i = new Intent(getContext(), ListActivity.class);
////                            startActivity(i);
////                            getActivity().finish();
//                            mp.pause();
//                            LessonFragment lessonFragment = LessonFragment.newInstance(ListActivity.nextLesson);
//                            getFragmentManager().beginTransaction().replace(R.id.list_frame, lessonFragment).commit();
//
//                        }
//                    });
//
//            AlertDialog alert = builder.create();
//            alert.show();
            Custom custom = new Custom();
            custom.setCancelable(false);
            custom.show(getFragmentManager(), null);

        }

    }

}
