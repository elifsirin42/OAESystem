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

import com.example.oeasystem.R;

public class OpenEndedDF extends DialogFragment {

    private onFragmentBtnSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.df_openended, container, false);

        Button save = view.findViewById(R.id.oesavebtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOESaveButtonSelected();
            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof onFragmentBtnSelected) {
            listener = (onFragmentBtnSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onFragmentBtnSelected {
        public void onOESaveButtonSelected();




    }
}
