package com.example.evoca.evocaforandroidlearning.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.evoca.evocaforandroidlearning.R;


public class ScreenFragment extends Fragment {


    public ScreenFragment() {
        // Required empty public constructor
    }


    public static ScreenFragment newInstance() {
        ScreenFragment fragment = new ScreenFragment();
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
        // Inflate the layout for this fragment

        View rootView  = inflater.inflate(R.layout.fragment_screen, container, false);
        ImageView img = (ImageView)rootView.findViewById(R.id.animationView);
        img.setImageResource(R.drawable.logo);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.common_animation);
        img.startAnimation(animation);
        return rootView;
    }
}
