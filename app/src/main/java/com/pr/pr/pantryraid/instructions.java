package com.pr.pr.pantryraid;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class instructions extends Fragment {

    String instructionString;

    @SuppressLint("ValidFragment")
    public instructions(String instructionString)
    {
        this.instructionString = instructionString;
    }

    public instructions() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.instructions, container, false);
        TextView instr = view.findViewById(R.id.instr);
        instr.setText(instructionString);
        instr.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
