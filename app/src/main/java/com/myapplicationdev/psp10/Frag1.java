package com.myapplicationdev.psp10;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment implements View.OnClickListener {

    Button change;

    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        return view;

    }

    @Override
    public void onClick(View view) {
        RandomColor randomColor = new RandomColor();
        change = view.findViewById(R.id.btnChange);
        change.setBackgroundColor(randomColor.randomColor());
    }

}
