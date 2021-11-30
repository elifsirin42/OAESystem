package com.example.oeasystem.questiontypes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.oeasystem.MyDialogFragment;
import com.example.oeasystem.R;

public class MultipleChoicesDF extends DialogFragment {

    private onFragmentBtnSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.df_multiplechoices,container,false);

        Button four = view.findViewById(R.id.fourbtn);
        Button five = view.findViewById(R.id.fivebtn);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.fourchoices();
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.fivechoices();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof onFragmentBtnSelected){
            listener = (onFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onFragmentBtnSelected{
        public void onDialogButtonSelected();
        public void fourchoices();
        public void fivechoices();



    }
}
